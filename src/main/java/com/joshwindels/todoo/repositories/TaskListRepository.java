package com.joshwindels.todoo.repositories;

import java.util.List;
import java.util.Set;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    List<TaskList> getTaskListsForUser(int userId);

    TaskList getTaskListById(int taskListId);

    TaskList saveNewTaskList(TaskList taskList);

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

    void deleteTaskList(int taskListId);

    void addTaskListForUser(int userId, int taskListId, boolean isAdmin);

    void removeTaskListForUser(int taskListId, int userId);

}
