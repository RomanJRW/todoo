package com.joshwindels.todoo.services.impl;

import java.util.List;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public void addTaskToTaskList(int taskId, int taskListId) {
        taskListRepository.addTaskToTaskList(taskId, taskListId);
    }

    @Override
    public void removeTaskFromTaskList(int taskId, int taskListId) {
        taskListRepository.removeTaskFromTaskList(taskId, taskListId);
    }

    @Override
    public void deleteTaskList(int taskListId) {
        taskListRepository.deleteTaskList(taskListId);
    }

    @Override
    public Integer saveNewTaskList(TaskList taskList) {
        return taskListRepository.saveNewTaskList(taskList);
    }

    @Override
    public TaskList getTaskListById(int taskListId) {
        return taskListRepository.getTaskListById(taskListId);
    }

    @Override
    public List<TaskList> getTaskListsForUser(int userId) {
        return taskListRepository.getTaskListsForUser(userId);
    }

}
