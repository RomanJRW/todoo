package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.TaskRepository;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTaskToTaskList(int taskId, int taskListId) {
        taskListRepository.addTaskToTaskList(taskId, taskListId);
    }

    @Override
    public void removeTaskFromTaskList(int taskId, int taskListId) {
        taskListRepository.removeTaskFromTaskList(taskId, taskListId);
    }

    @Override
    public TaskList saveNewTaskList(TaskList taskList) {
        return taskListRepository.saveNewTaskList(taskList);
    }

    @Override
    public TaskList getTaskListById(int taskListId) {
        TaskList taskList = taskListRepository.getTaskListById(taskListId);
        taskList.setTasks(taskRepository.getTasksByTaskListId(taskListId));
        return taskList;
    }

}
