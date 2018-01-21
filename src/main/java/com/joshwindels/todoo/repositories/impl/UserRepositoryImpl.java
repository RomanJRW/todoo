package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.dos.User;
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
    public Integer getUserIdForUserName(String username) {
        String sql = "SELECT id "
                + "   FROM user_credentials "
                + "   WHERE username = :username ";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        try {
            return npjt.queryForObject(sql, params, Integer.class);
        } catch (DataAccessException ex) {
            //temporary shite error handling
            // Note that this should throw new error, as this is expected to be thrown
            // when checking for new username availability
            System.out.println("Error getting user");
            return null;
        }
    }

    @Override
    public String getStoredPasswordForUsername(String username) {
        String sql = "SELECT password "
                + "   FROM user_credentials "
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
    public Integer createNewUser(User user) {
        String sql = " "
                + " WITH cred AS ( "
                + "    INSERT INTO user_credentials "
                + "    (username, password) "
                + "    VALUES ( :username, :password ) "
                + "    RETURNING id AS user_id ) "
                + " INSERT INTO user_details"
                + "    ( user_id, first_name, last_name  ) "
                + "    VALUES ( (SELECT user_id FROM cred) , :firstName, :lastName )"
                + "    RETURNING user_id   ";
        Map<String, Object> params = new HashMap<>();
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        params.put("firstName", user.getFirstName());
        params.put("lastName", user.getLastName());
        return npjt.queryForObject(sql, params, Integer.class);
    }

}
