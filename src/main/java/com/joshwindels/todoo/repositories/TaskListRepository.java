package com.joshwindels.todoo.repositories;

import java.util.List;
import java.util.Set;

import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    TaskList getTaskListById(int taskListId);

    TaskList saveTaskList(TaskList taskList);

    List<TaskList> addTaskToTaskLists(int taskId, Set<Integer> taskListIds);

    List<TaskList> removeTaskFromTaskLists(int taskId, Set<Integer> taskListIds);

}
