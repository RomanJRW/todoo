package com.joshwindels.todoo.repositories.rowmappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.joshwindels.todoo.dos.TaskList;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskListRowMapper implements RowMapper<TaskList> {

    @Override
    public TaskList mapRow(ResultSet rs, int rowNumber) throws SQLException {
        TaskList taskList = new TaskList();
        taskList.setName(rs.getString("name"));
        taskList.setId(rs.getInt("id"));
        return taskList;
    }

}
