package com.joshwindels.todoo.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.joshwindels.todoo.dos.Task;
import org.springframework.jdbc.core.RowMapper;

public class TaskListRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Task task = new Task();
        task.setDescription(rs.getString("task_description"));
        return task;
    }
}
