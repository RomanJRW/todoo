package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    TaskRowMapper taskRowMapper;

    @Override
    public Task getTaskById(int taskId) {
        String sql = "SELECT * FROM tasks WHERE id = :taskId";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskId", taskId);
        return npjt.queryForObject(sql, params, taskRowMapper);
    }

    @Override
    public List<Task> getTasksByTaskListId(int taskListId) {
        String sql = "SELECT * FROM tasks t, task_list_task_map tlm "
                + "   WHERE t.id = tlm.task_id AND "
                + "     tlm.task_list_id = :taskListId AND t.completed = false ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskListId);
        return npjt.query(sql, params, taskRowMapper);
    }

    @Override
    public Task saveNewTask(Task task) {
        String sql = " INSERT INTO tasks "
                + " (description, completed) "
                + " VALUES ( :description, :completed ) "
                + " RETURNING * ";
        Map<String, Object> params = new HashMap<>();
        params.put("description", task.getDescription());
        params.put("completed", task.isCompleted());
        return npjt.queryForObject(sql, params, taskRowMapper);
    }

    @Override
    public void deleteTask(int taskId) {
        String sql = " DELETE "
                + "   FROM tasks "
                + "   WHERE id = :taskId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskId", taskId);
        npjt.update(sql, params);
    }

    @Override
    public void completeTask(int taskId) {
        String sql = " UPDATE tasks "
                + "   SET completed = TRUE "
                + "   WHERE id = :taskId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskId", taskId);
        npjt.update(sql, params);
    }

}
