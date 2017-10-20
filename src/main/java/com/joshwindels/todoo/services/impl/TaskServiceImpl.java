package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.repositories.TaskRepository;
import com.joshwindels.todoo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void deleteTask(int taskId) {
        //taskRepository.deleteTask(taskId);
    }

    @Override
    public void saveTask(Task task) {
        taskRepository.saveTask(task);
    }
}
