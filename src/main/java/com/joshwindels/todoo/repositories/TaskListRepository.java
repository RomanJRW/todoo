package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    TaskList getTaskListById(int taskListId);

    TaskList saveNewTaskList(TaskList taskList);

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

    void deleteTaskList(int taskListId);

    List<Integer> getTaskListIdsForUser(int userId);

    void addTaskListForUser(int userId, int taskListId);

    void removeTaskListForUser(int taskListId, int userId);

}
