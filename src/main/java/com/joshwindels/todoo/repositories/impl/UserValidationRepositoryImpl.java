package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.repositories.UserValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserValidationRepositoryImpl implements UserValidationRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public Integer getUserIdForUserNameAndPassword(String username, String encryptedPassword) {
        String sql = "SELECT user_id "
                + "   FROM user_details "
                + "   WHERE username = :username "
                + "   AND password = :password ";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", encryptedPassword);
        return npjt.queryForObject(sql, params, Integer.class);
    }

}
