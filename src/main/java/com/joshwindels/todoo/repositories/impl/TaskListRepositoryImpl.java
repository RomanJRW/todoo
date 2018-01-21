package com.joshwindels.todoo.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.rowmappers.TaskListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListRepositoryImpl implements TaskListRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;
    @Autowired
    TaskListRowMapper taskListRowMapper;

    @Override
    public List<Integer> getTaskListIdsForUser(int userId, boolean ownedOnly) {
        String sql = "SELECT task_list_id "
                + "   FROM user_task_list_map "
                + "   WHERE user_id = :userId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("userId", userId);
        return npjt.queryForList(sql, params, Integer.class);
    }

    @Override
    public List<TaskList> getTaskListsForUser(int userId) {
        String sql = " SELECT task_lists.id AS id, task_lists.name AS name, tasks.id AS task_id, tasks.description AS task_description, tasks.completed AS task_completed, "
                + "  user_task_list_map.user_id AS owner_id, user_task_list_map.is_owner "
                + "FROM task_lists "
                + "  LEFT JOIN task_list_task_map ON task_lists.id = task_list_task_map.task_list_id "
                + "  LEFT JOIN tasks ON task_list_task_map.task_id = tasks.id "
                + "  LEFT JOIN user_task_list_map ON task_lists.id = user_task_list_map.task_list_id "
                + "WHERE user_task_list_map.user_id = :userId";

        Map<String, Integer> params = new HashMap<>();
        params.put("userId", userId);

        return npjt.query(sql, new ResultSetExtractor<List<TaskList>>() {

            @Override
            public List<TaskList> extractData(ResultSet resultSet)
                    throws SQLException, DataAccessException {
                List<TaskList> taskLists = new ArrayList<>();
                while(resultSet.next()) {
                    Task task = new Task();
                    task.setId(resultSet.getInt("task_id"));
                    task.setDescription(resultSet.getString("task_description"));
                    task.setCompleted(resultSet.getBoolean("task_completed"));

                    TaskList taskList;
                    int taskListId = resultSet.getInt("id");
                    Optional<TaskList> otl = taskLists.stream()
                            .filter(tl -> tl.getId() == taskListId)
                            .findFirst();
                    if (otl.isPresent()) {
                        taskList = otl.get();
                        taskList.getOwnerIds().add(resultSet.getInt("owner_id"));
                    } else {
                        taskList = new TaskList();
                        taskList.setId(resultSet.getInt("id"));
                        taskList.setName(resultSet.getString("name"));
                        taskList.setOwnerIds(Collections.singleton(resultSet.getInt("owner_id")));
                    }
                    taskList.addTask(task);
                }

                return taskLists;
            }
        });
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
        String sql = " DELETE "
                + " FROM task_lists "
                + " WHERE id = :taskListId ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }

    @Override
    public void addTaskListForUser(int userId, int taskListId, boolean isOwner) {
        String sql = " INSERT INTO user_task_list_map "
                + " (user_id, task_list_id, isAdmin) "
                + " VALUES (:userId, :taskListId, :isOwner) ";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("taskListId", taskListId);
        params.put("isOwner", isOwner);
        npjt.update(sql, params);
    }

    @Override
    public void removeTaskListForUser(int taskListId, int userId) {
        String sql = " DELETE FROM user_task_list_map "
                + " WHERE user_id = :userId AND "
                + "       task_list_id = :taskListId ";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }

}
