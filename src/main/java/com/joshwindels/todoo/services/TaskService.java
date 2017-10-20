package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.Task;

public interface TaskService {

    void deleteTask(int taskId);

    void saveTask(Task task);

}
