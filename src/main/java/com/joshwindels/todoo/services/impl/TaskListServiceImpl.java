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
    public void addTaskToTaskLists(int taskId, List<Integer> taskListIds) {
        taskListRepository.addTaskToTaskLists(taskId, taskListIds);
    }

    @Override
    public void removeTaskFromTaskLists(int taskId, List<Integer> taskListIds) {
        taskListRepository.removeTaskFromTaskLists(taskId, taskListIds);
    }

    @Override
    public void saveTaskList(TaskList taskList) {
        taskListRepository.saveTaskList(taskList);
    }

    @Override
    public TaskList getTaskListById(int taskListId) {
        return taskListRepository.getTaskListById(taskListId);
    }

}
