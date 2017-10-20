package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.ToDoList;

public interface TaskListService {

    void addTaskToList(Task task, ToDoList taskList);

    void removeTaskFromList(Task task, ToDoList taskList);

    ToDoList getExistingTaskListFromFromFile(String fileName);
}
