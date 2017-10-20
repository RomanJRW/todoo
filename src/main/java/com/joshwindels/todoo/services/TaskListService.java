package com.joshwindels.todoo.services;

import java.util.Set;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToTaskLists(int taskId, Set<Integer> taskListIds);

    void removeTaskFromTaskLists(int taskId, Set<Integer> taskListId);

    void saveTaskList(TaskList taskList);

    TaskList getTaskListById(int taskListId);

}
