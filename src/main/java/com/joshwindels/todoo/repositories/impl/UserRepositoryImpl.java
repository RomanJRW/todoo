package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public Integer getCurrentUserIdForUserName(String username) {
        String sql = "SELECT id "
                + "   FROM user_details "
                + "   WHERE username = :username ";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        try {
            return npjt.queryForObject(sql, params, Integer.class);
        } catch (DataAccessException ex) {
            //temporary shite error handling
            System.out.println("Error getting user");
            return null;
        }
    }

    @Override
    public String getStoredPasswordForUsername(String username) {
        String sql = "SELECT password "
                + "   FROM user_details "
                + "   WHERE username = :username ";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        try {
            return npjt.queryForObject(sql, params, String.class);
        } catch (DataAccessException ex) {
            //temporary shite error handling
            System.out.println("Couldn't find any users with username: " + username);
            return null;
        }
    }

    @Override
    public Integer createNewUser(String username, String encryptedPassword) {
        String sql = " INSERT INTO user_details "
                + " (username, password) "
                + " VALUES ( :username, :password ) "
                + " RETURNING id ";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", encryptedPassword);
        return npjt.queryForObject(sql, params, Integer.class);
    }

}
