package com.joshwindels.todoo.repositories;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    TaskList getTaskListById(int taskListId);

    TaskList saveNewTaskList(TaskList taskList);

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

}
