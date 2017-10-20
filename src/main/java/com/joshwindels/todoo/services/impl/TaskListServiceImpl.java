package com.joshwindels.todoo.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.stereotype.Repository;

@Repository
public class TaskListServiceImpl implements TaskListService {

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
