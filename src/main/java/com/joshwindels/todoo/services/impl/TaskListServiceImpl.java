package com.joshwindels.todoo.services.impl;

import java.util.List;

import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.TaskRepository;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private CurrentUser currentUser;
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
    public void deleteTaskList(int taskListId) {
        taskListRepository.deleteTaskList(taskListId);
    }

    @Override
    public TaskList saveNewTaskList(TaskList taskList) {
        TaskList savedTaskList = taskListRepository.saveNewTaskList(taskList);
        taskListRepository.addTaskListForUser(currentUser.getId(), savedTaskList.getId());
        return savedTaskList;
    }

    @Override
    public TaskList getTaskListById(int taskListId) {
        TaskList taskList = taskListRepository.getTaskListById(taskListId);
        taskList.setTasks(taskRepository.getTasksByTaskListId(taskListId));
        return taskList;
    }

    @Override
    public List<Integer> getTaskListIdsForUser(int userId) {
        return taskListRepository.getTaskListIdsForUser(userId);
    }

}
