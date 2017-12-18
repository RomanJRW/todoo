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
    public Integer getCurrentUserForUserNameAndPassword(String username, String encryptedPassword) {
        String sql = "SELECT user_id "
                + "   FROM user_details "
                + "   WHERE username = :username "
                + "   AND password = :password ";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", encryptedPassword);
        return npjt.queryForObject(sql, params, Integer.class);
    }

    @Override
    public Integer createNewUser(String username, String encryptedPassword) {
        String sql = " INSERT INTO user_details "
                + " (username, password) "
                + " VALUES ( :username, :password ) "
                + " RETURNING user_id ";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", encryptedPassword);
        return npjt.queryForObject(sql, params, Integer.class);
    }

}
