package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToTaskList(int taskId, int taskListId);

    void removeTaskFromTaskList(int taskId, int taskListId);

    void deleteTaskList(int taskListId);

    Integer saveNewTaskList(TaskList taskList);

    TaskList getTaskListById(int taskListId);

    List<TaskList> getTaskListsForUser(int userId);

}
