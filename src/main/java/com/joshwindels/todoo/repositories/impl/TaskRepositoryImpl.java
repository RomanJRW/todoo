package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.repositories.TaskRepository;
import com.joshwindels.todoo.repositories.rowmappers.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public Task getTaskById(int taskId) {
        String sql = "SELECT * FROM tasks WHERE id = :taskId";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskId", taskId);
        return npjt.queryForObject(sql, params, new TaskRowMapper());
    }

    @Override
    public Task saveTask(Task task) {
        String sql = " INSERT INTO tasks "
                + " (id, description, completed) "
                + " VALUES ( :id :description, :completed ) "
                + " ON CONFLICT id "
                + " DO UPDATE tasks"
                + " SET description = :description, "
                + "     completed = :completed "
                + "     WHERE id = :id ";
        Map<String, Object> params = new HashMap<>();
        params.put("description", task.getDescription());
        params.put("completed", task.isCompleted());
        params.put("id", task.getId());
        return npjt.queryForObject(sql, params, new TaskRowMapper());
    }

    @Override
    public void deleteTask(int taskId) {
        String sql = " DELETE * "
                + "   FROM tasks "
                + "   WHERE id = :taskId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskId", taskId);
        npjt.update(sql, params);
    }

}
