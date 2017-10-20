package com.joshwindels.todoo.converters;

import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dtos.TaskDTO;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public Task convertToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        return task;
    }

    public TaskDTO convertToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setDescription(task.getDescription());
        return taskDTO;
    }

}
