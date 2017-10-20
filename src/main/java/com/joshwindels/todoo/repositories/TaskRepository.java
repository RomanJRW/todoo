package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.Task;

public interface TaskRepository {

    List<Task> getTaskById(int taskListId);

}
