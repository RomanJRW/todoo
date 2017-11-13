package com.joshwindels.todoo.services.impl;

import java.util.List;

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
        taskRepository.deleteTask(taskId);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.saveNewTask(task);
    }

    @Override
    public List<Task> getTasksForTaskListId(int taskListId) {
        return taskRepository.getTasksByTaskListId(taskListId);
    }
}
