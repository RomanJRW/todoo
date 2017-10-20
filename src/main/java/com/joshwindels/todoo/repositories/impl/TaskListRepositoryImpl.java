package com.joshwindels.todoo.repositories.impl;

import java.util.List;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.repositories.TaskListRepository;

public class TaskListRepositoryImpl implements TaskListRepository {

    @Override public TaskList getTaskListById(int taskListId) {
        return null;
    }

    @Override public TaskList updateTaskListName(int taskListId, String taskListName) {
        return null;
    }

    @Override
    public List<TaskList> addTaskToTaskLists(Task task, List<TaskList> taskLists) {
        return null;
    }

    @Override
    public List<TaskList> removeTaskFromTaskLists(Task task, List<TaskList> taskLists) {
        return null;
    }
}
