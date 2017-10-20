package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    TaskList getTaskListById(int taskListId);

    TaskList updateTaskListName(int taskListId, String taskListName);

    List<TaskList> addTaskToTaskLists(Task task, List<TaskList> taskLists);

    List<TaskList> removeTaskFromTaskLists(Task task, List<TaskList> taskLists);

}
