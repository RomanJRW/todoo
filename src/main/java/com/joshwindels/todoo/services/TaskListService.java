package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.ToDoList;

public interface TaskListService {

    void addTaskToList(String task, ToDoList taskList);

    void removeTaskFromList(String task, ToDoList taskList);

    ToDoList getExistingTaskListFromFromFile(String fileName);
}
