package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.Task;

public interface TaskService {

    void deleteTask(int taskId);

    void saveTask(Task task);

    List<Task> getTasksForTaskListId(int taskListId);

}
