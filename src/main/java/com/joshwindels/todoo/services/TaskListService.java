package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToList(Task task, TaskList taskList);

    void removeTaskFromList(Task task, TaskList taskList);

    void updateTask(Task task);

    TaskList getExistingTaskListFromFromFile(String fileName);
}
