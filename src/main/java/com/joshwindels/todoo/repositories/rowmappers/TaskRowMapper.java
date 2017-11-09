package com.joshwindels.todoo.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.joshwindels.todoo.dos.Task;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("id"));
        task.setDescription(rs.getString("description"));
        task.setCompleted(rs.getBoolean("completed"));
        return task;
    }
}
