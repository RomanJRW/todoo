package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.rowmappers.TaskListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListRepositoryImpl implements TaskListRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;
    @Autowired
    TaskListRowMapper taskListRowMapper;

    @Override
    public List<Integer> getTaskListIdsForUser(int userId) {
        String sql = "SELECT task_list_id "
                + "   FROM user_task_list_map "
                + "   WHERE user_id = :userId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("userId", userId);
        return npjt.queryForList(sql, params, Integer.class);
    }

    @Override
    public TaskList getTaskListById(int taskListId) {
        String sql = "SELECT * "
                + "   FROM task_lists "
                + "   WHERE id = :taskListId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskListId", taskListId);
        try {
            return npjt.queryForObject(sql, params, taskListRowMapper);
        } catch (EmptyResultDataAccessException e) {
            return new TaskList();
        }
    }

    @Override
    public TaskList saveNewTaskList(TaskList taskList) {
        String sql = " INSERT INTO task_lists "
                + " (name) "
                + " VALUES ( :taskListName )"
                + " RETURNING * ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListName", taskList.getName());
        return npjt.queryForObject(sql, params, taskListRowMapper);
    }

    @Override
    public void addTaskToTaskList(int taskId, int taskListId) {
        String sql = " INSERT INTO task_list_task_map "
                + " (task_list_id, task_id)  "
                + " VALUES (:taskListId, :taskId) "
                + " ON CONFLICT DO NOTHING ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskListId);
        params.put("taskId", taskId);
        npjt.update(sql, params);
    }

    @Override
    public void removeTaskFromTaskList(int taskId, int taskListId) {
        String sql = " DELETE * "
                + " FROM task_list_task_map "
                + " WHERE task_id = :taskId AND "
                + "      task_list_id = :taskListId ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }

    @Override
    public void deleteTaskList(int taskListId) {
        removeAllTasksFromTaskList(taskListId);
        String sql = " DELETE * "
                + " FROM task_lists "
                + " WHERE task_list_id = :taskListId ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }

    @Override
    public void addTaskListAndUserMapping(int userId, int taskListId) {
        String sql = " INSERT INTO user_task_list_map "
                + " (user_id, task_list_id) "
                + " VALUES (:userId, :taskListId) ";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }

    private void removeAllTasksFromTaskList(int taskListId) {
        String sql = " DELETE * "
                + " FROM task_list_task_map "
                + " WHERE task_list_id = :taskListId ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }

}
