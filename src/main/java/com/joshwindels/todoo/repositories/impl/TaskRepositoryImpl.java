package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.repositories.TaskRepository;
import com.joshwindels.todoo.repositories.rowmappers.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public List<Task> getTaskById(int taskId) {
        String sql = "SELECT * FROM tasks WHERE id = :taskId";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskId", taskId);
        return npjt.query(sql, params, new TaskRowMapper());
    }

    @Override
    public Task addTask(Task task) {
        String sql = " INSERT INTO tasks (description) "
                + " VALUES (:description) "
                + " RETURNING * ";
        Map<String, String> params = new HashMap<>();
        params.put("description", task.getDescription());
        return npjt.queryForObject(sql, params, new TaskRowMapper());
    }

}
