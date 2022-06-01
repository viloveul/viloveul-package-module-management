package com.viloveul.module.management.service.impl;

import com.viloveul.context.base.AbstractComponent;
import com.viloveul.context.type.SecureType;
import com.viloveul.context.type.SignerType;
import com.viloveul.module.management.data.entity.Assignment;
import com.viloveul.module.management.data.entity.Credential;
import com.viloveul.module.management.data.entity.Privilege;
import com.viloveul.module.management.data.entity.Scope;
import com.viloveul.module.management.data.entity.Session;
import com.viloveul.module.management.data.entity.User;
import com.viloveul.module.management.data.repository.AssignmentRepository;
import com.viloveul.module.management.data.repository.CredentialRepository;
import com.viloveul.module.management.data.repository.GroupRepository;
import com.viloveul.module.management.data.repository.NotificationRepository;
import com.viloveul.module.management.data.repository.PrivilegeRepository;
import com.viloveul.module.management.data.repository.ResourceRepository;
import com.viloveul.module.management.data.repository.SessionRepository;
import com.viloveul.module.management.data.repository.UserRepository;
import com.viloveul.module.management.service.AuthService;
import com.viloveul.context.auth.dto.DetailAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthServiceImpl extends AbstractComponent implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Value("${viloveul.auth.role-default:DEFAULT}")
    private String role;

    @Value("${viloveul.auth.as-administrator:false}")
    private Boolean admin;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        Session session = this.sessionRepository.save(new Session(user));
        Map<String, DetailAuthentication.GroupMapper> maps = new HashMap<>();
        List<Assignment> assignments = this.assignmentRepository.findAllActiveByUser(user.getId(), new Date());
        DetailAuthentication auth = new DetailAuthentication(
            user,
            this.userRepository.getExtra(user),
            this.userRepository.getRelations(user)
        );
        auth.setSession(session.getId());
        Set<Privilege> privileges = new HashSet<>(user.getPrivileges());
        if (!this.role.isEmpty()) {
            Privilege privilegeDefault = this.privilegeRepository.findByIdentity(this.role);
            if (privilegeDefault != null) {
                privileges.add(privilegeDefault);
            }
        }
        auth.setAccessors(this.extractAuthorities(privileges));
        for (Assignment assignment : assignments) {
            if (!maps.containsKey(assignment.getGroup().getId())) {
                maps.put(
                    assignment.getGroup().getId(),
                    new DetailAuthentication.GroupMapper(
                        assignment.getGroup()
                    )
                );
            }
            maps.get(assignment.getGroup().getId()).addAuthority(
                this.extractAuthorities(
                    Collections.singleton(assignment.getPrivilege())
                )
            );
        }
        maps.values().forEach(auth::addAbility);
        if (Boolean.TRUE.equals(admin)) {
            auth.setType(SignerType.ADMIN);
        }
        return auth;
    }

    @Override
    public Boolean authenticate(Authentication authentication, PasswordEncoder passwordEncoder) {
        List<Credential> credentials = this.credentialRepository.findAll(
            authentication.getName(),
            Collections.singletonList(Boolean.TRUE),
            Arrays.asList(SecureType.PASSWORD, SecureType.REQUESTED)
        );
        for (Credential credential : credentials) {
            if (passwordEncoder.matches(authentication.getCredentials().toString(), credential.getValue())) {
                if (credential.getType() == SecureType.REQUESTED) {
                    credential.setStatus(Boolean.FALSE);
                    this.credentialRepository.save(credential);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean validate(Authentication authentication) {
        Object detail = authentication.getDetails();
        if (detail instanceof DetailAuthentication) {
            return this.sessionRepository.existsById(((DetailAuthentication) detail).getSession());
        }
        return false;
    }

    private Collection<String> extractAuthorities(Set<Privilege> privileges) {
        Collection<String> authorities = new ArrayList<>();
        for (Privilege privilege : privileges) {
            if (!authorities.contains(privilege.getAuthority())) {
                authorities.add(privilege.getAuthority());
            }
            for (Scope ability : privilege.getScopes()) {
                if (!authorities.contains(ability.getAuthority())) {
                    authorities.add(ability.getAuthority());
                }
            }
            authorities.addAll(this.extractAuthorities(privilege.getAgregates()));
        }
        return authorities;
    }
}
