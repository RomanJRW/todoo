package com.joshwindels.todoo.services;

import java.util.Set;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;

public interface TaskListService {

    void addTaskToLists(Task task, Set<TaskList> taskLists);

    void removeTaskFromList(Task task, TaskList taskList);

    void updateTask(Task task);

}
