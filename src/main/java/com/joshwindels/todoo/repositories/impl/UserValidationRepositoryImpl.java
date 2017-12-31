package com.joshwindels.todoo.repositories.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.exception.BadOperationException;
import com.amdelamar.jhash.exception.InvalidHashException;
import com.joshwindels.todoo.repositories.UserValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserValidationRepositoryImpl implements UserValidationRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public Integer getCurrentUserForUserNameAndPassword(String username, String password) {
        String sql = "SELECT id "
                + "   FROM user_details "
                + "   WHERE username = :username "
                + "   AND password = :password ";
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        try {
            return npjt.queryForObject(sql, params, Integer.class);
        } catch (DataAccessException ex) {
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

    @Override
    public boolean isValidCredentials(String username, String password) {
        String passwordSql = "SELECT password " +
                "FROM user_details WHERE username = :username";
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        String storedPassword = npjt.queryForObject(passwordSql, params, String.class);
        boolean matchedPassword = false;
        try {
            matchedPassword = Hash.verify(password, storedPassword);
        } catch (BadOperationException | NoSuchAlgorithmException | InvalidHashException ex) {
            System.out.println("invalid credentials");
        }
        return matchedPassword;
    }

}
