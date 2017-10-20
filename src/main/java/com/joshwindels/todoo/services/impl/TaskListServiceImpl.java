package com.joshwindels.todoo.services.impl;

import java.util.Set;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;
import com.joshwindels.todoo.repositories.TaskRepository;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskListServiceImpl implements TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTaskToLists(Task task, Set<TaskList> taskLists) {

        for (TaskList taskList : taskLists) {
            taskList.addTask(task);
        }

        // saveTaskLists(taskLists);

    }

    @Override
    public void removeTaskFromList(Task task, TaskList taskList) {

    }

    @Override
    public void updateTask(Task task) {

    }

}
