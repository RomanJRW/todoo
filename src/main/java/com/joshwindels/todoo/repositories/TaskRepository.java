package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.Task;

public interface TaskRepository {

    Task getTaskById(int taskListId);

    List<Task> getTasksByTaskListId(int taskListId);

    Task saveTask(Task task);

    void deleteTask(int taskId);

}
