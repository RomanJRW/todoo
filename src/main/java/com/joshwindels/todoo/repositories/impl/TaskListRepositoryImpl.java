package com.joshwindels.todoo.repositories.impl;

import java.util.List;

import com.joshwindels.todoo.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class TaskListRepositoryImpl implements TaskListRepository {

    @Autowired
    NamedParameterJdbcTemplate npjt;

    @Override public List<String> getAllTasks() {

        String sql = "SELECT * FROM tasks";

        return null;

    }

}
