package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

    void deleteTaskList(int taskListId);

    void removeTaskListForUser(int taskListId, int userId);

    void addTaskListForUser(int taskListId, int userId);

    TaskList saveNewTaskList(TaskList taskList);

    TaskList getTaskListById(int taskListId);

    List<Integer> getTaskListIdsForUser(int userId);

}
