package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;

public interface TaskListRepository {

    List<TaskList> addTaskToTaskLists(Task task, List<TaskList> taskLists);

    List<TaskList> removeTaskFromTaskLists(Task task, List<TaskList> taskLists);

}
