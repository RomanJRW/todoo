package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToTaskLists(int taskId, List<Integer> taskListIds);

    void removeTaskFromTaskLists(int taskId, List<Integer> taskListId);

    void saveTaskList(TaskList taskList);

    TaskList getTaskListById(int taskListId);

}
