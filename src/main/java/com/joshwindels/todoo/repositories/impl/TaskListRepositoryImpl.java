package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public TaskList getTaskListById(int taskListId) {
        String sql = "SELECT * "
                + "   FROM task_lists "
                + "   WHERE id = :id ";
        Map<String, Integer> params = new HashMap<>();
        params.put("id", taskListId);
        try {
            return npjt.queryForObject(sql, params, new TaskListRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // task list doesn't exist, will handle this better later
            return new TaskList();
        }
    }

    @Override
    public TaskList saveTaskList(TaskList taskList) {
        String sql = " WITH upsert AS ("
                + "    UPDATE task_lists"
                + "        SET name = :name "
                + "        WHERE id = :id"
                + "      RETURNING * )"
                + "    INSERT INTO task_lists"
                + "        (name)"
                + "      VALUES "
                + "        (:name)"
                + "    WHERE NOT EXISTS"
                + "      (SELECT * FROM upsert) ";
        Map<String, Object> params = new HashMap<>();
        params.put("id", taskList.getIdentifier());
        params.put("name", taskList.getName());
        return npjt.queryForObject(sql, params, new TaskListRowMapper());
    }

    @Override
    public List<TaskList> addTaskToTaskLists(int taskId, List<Integer> taskListIds) {
        return null;
    }

    @Override
    public List<TaskList> removeTaskFromTaskLists(int taskId, List<Integer> taskListIds) {
        return null;
    }
}
