package com.joshwindels.todoo.repositories;

import com.joshwindels.todoo.dos.Task;

public interface TaskRepository {

    Task getTaskById(int taskListId);

    Task saveTask(Task task);

    void deleteTask(int taskId);
}
