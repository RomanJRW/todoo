package com.joshwindels.todoo.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.joshwindels.todoo.dos.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        return user;
    }
}
