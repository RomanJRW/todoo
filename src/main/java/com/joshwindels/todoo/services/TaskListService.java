package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

    TaskList saveNewTaskList(TaskList taskList);

    TaskList getTaskListById(int taskListId);

}
