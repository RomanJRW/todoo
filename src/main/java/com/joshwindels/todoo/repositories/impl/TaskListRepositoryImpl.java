package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.rowmappers.TaskListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListRepositoryImpl implements TaskListRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;
    @Autowired
    TaskListRowMapper taskListRowMapper;

    @Override
    public TaskList getTaskListById(int taskListId) {
        String sql = "SELECT * "
                + "   FROM task_lists "
                + "   WHERE id = :taskListId ";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskListId", taskListId);
        return npjt.queryForObject(sql, params, taskListRowMapper);
    }

    @Override
    public void saveTaskList(TaskList taskList) {
        String sql = " WITH upsert AS ("
                + "    UPDATE task_lists"
                + "        SET name = :taskListName "
                + "        WHERE id = :taskListId"
                + "      RETURNING * )"
                + "    INSERT INTO task_lists"
                + "        (name)"
                + "      VALUES "
                + "        (:taskListName)"
                + "    WHERE NOT EXISTS"
                + "      (SELECT * FROM upsert) ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskList.getIdentifier());
        params.put("taskListName", taskList.getName());
        npjt.update(sql, params);
    }

    @Override
    public void addTaskToTaskList(int taskId, int taskListId) {
        String sql = " INSERT INTO task_list_task_mapping "
                + " (task_list_id, task_id)  "
                + " VALUES (:taskListId, :taskId) ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskListId", taskListId);
        params.put("taskId", taskId);
        npjt.update(sql, params);
    }

    @Override
    public void removeTaskFromTaskList(int taskId, int taskListId) {
        String sql = " DELETE * "
                + " FROM task_list_task_mapping "
                + " WHERE task_id = :taskId AND "
                + "      task_list_id = :taskListId ";
        Map<String, Object> params = new HashMap<>();
        params.put("taskId", taskId);
        params.put("taskListId", taskListId);
        npjt.update(sql, params);
    }
}
