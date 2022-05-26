package com.viloveul.module.management.data.repository.advanced;

import com.viloveul.module.management.data.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Repository
public interface UserRepositoryAdvanced extends InitializingBean {

    Map<String, Serializable> getExtra(User user);

    Map<String, List<Map<String, Serializable>>> getRelations(User user);

}
