package com.joshwindels.todoo.converters;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.dtos.TaskListDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskListConverter {

    public TaskList convertToTaskList(TaskListDTO taskListDTO) {
        TaskList taskList = new TaskList();
        taskList.setId(taskListDTO.getId());
        taskList.setName(taskListDTO.getName());
        taskList.setTasks(taskListDTO.getTasks());
        return taskList;
    }

    public TaskListDTO convertToTaskDTO(TaskList taskList) {
        TaskListDTO taskListDTO = new TaskListDTO();
        taskListDTO.setId(taskList.getId());
        taskListDTO.setName(taskList.getName());
        taskListDTO.setTasks(taskList.getTasks());
        return taskListDTO;
    }

}
