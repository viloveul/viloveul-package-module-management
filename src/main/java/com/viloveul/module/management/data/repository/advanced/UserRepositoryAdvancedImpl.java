package com.viloveul.module.management.data.repository.advanced;

import com.viloveul.module.management.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
@Transactional(
    readOnly = true
)
public class UserRepositoryAdvancedImpl implements UserRepositoryAdvanced {

    @Value("${viloveul.user.dsbean:dataSource}")
    private String dsbeanProperty;

    @Value("${viloveul.user.extra}")
    private String extraProperty;

    @Value("${viloveul.user.relation}")
    private String relationProperty;

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext context;

    private final Map<String, String> sqlUserRelations = new HashMap<>();

    private NamedParameterJdbcOperations jdbcTemplate;

    public Map<String, Serializable> getExtra(@Valid User user) {
        Map<String, Serializable> columns = new HashMap<>();
        if (!this.extraProperty.isEmpty()) {
            Map<String, Object> tmps = this.jdbcTemplate.queryForMap(
                this.extraProperty,
                Collections.singletonMap("id_user", user.getId())
            );
            for (Map.Entry<String, Object> tmp : tmps.entrySet()) {
                if (tmp.getValue() instanceof Serializable) {
                    columns.put(tmp.getKey().toLowerCase(Locale.getDefault()), (Serializable) tmp.getValue());
                }
            }
        }
        return columns;
    }

    public Map<String, List<Map<String, Serializable>>> getRelations(@Valid User user) {
        Map<String, List<Map<String, Serializable>>> relations = new HashMap<>();
        for (Map.Entry<String, String> query: this.sqlUserRelations.entrySet()) {
            List<Map<String, Object>> rlist = this.jdbcTemplate.queryForList(
                query.getValue(),
                Collections.singletonMap("id_user", user.getId())
            );
            if (!rlist.isEmpty()) {
                List<Map<String, Serializable>> inner = new ArrayList<>();
                for (Map<String, Object> tmps : rlist) {
                    Map<String, Serializable> columns = new HashMap<>();
                    for (Map.Entry<String, Object> tmp : tmps.entrySet()) {
                        if (tmp.getValue() instanceof Serializable) {
                            columns.put(tmp.getKey().toLowerCase(Locale.getDefault()), (Serializable) tmp.getValue());
                        }
                    }
                    inner.add(columns);
                }
                relations.put(query.getKey(), inner);
            }
        }
        return relations;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(
            this.context.getBean(this.dsbeanProperty, DataSource.class)
        );
        String[] cqids = environment.getProperty(this.relationProperty + ".keys", String[].class, new String[]{});
        for (String cqid: cqids) {
            String q = environment.getProperty(this.relationProperty + ".query-" + (cqid.trim()), String.class,"");
            if (!q.isEmpty()) {
                this.sqlUserRelations.put(cqid.trim().toLowerCase(Locale.getDefault()), q);
            }
        }
    }
}
