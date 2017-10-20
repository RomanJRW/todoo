package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    TaskList getTaskListById(int taskListId);

    TaskList saveTaskList(TaskList taskList);

    List<TaskList> addTaskToTaskLists(int taskId, List<Integer> taskListIds);

    List<TaskList> removeTaskFromTaskLists(int taskId, List<Integer> taskListIds);

}
