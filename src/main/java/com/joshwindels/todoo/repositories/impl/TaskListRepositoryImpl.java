package com.joshwindels.todoo.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.rowmappers.TaskListRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class TaskListRepositoryImpl implements TaskListRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override
    public List<Task> getTaskListById(int taskListId) {

        String sql = "SELECT * FROM tasks WHERE id = :taskListID";
        Map<String, Integer> params = new HashMap<>();
        params.put("taskListId", taskListId);

        return npjt.query(sql, params, new TaskListRowMapper());

    }

}
