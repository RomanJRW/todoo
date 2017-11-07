package com.joshwindels.todoo.repositories;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    TaskList getTaskListById(int taskListId);

    void saveTaskList(TaskList taskList);

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

}
