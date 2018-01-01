package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.Task;

public interface TaskService {

    void deleteTask(int taskId);

    Task saveTask(Task task);

    List<Task> getTasksForTaskListId(int taskListId);

    void completeTask(int taskId);

}
