package com.joshwindels.todoo.controllers;

import java.util.List;

import com.joshwindels.todoo.converters.TaskConverter;
import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dtos.TaskDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskConverter taskConverter;
    @Autowired
    TaskListService taskListService;
    @Autowired
    TaskService taskService;

    @PostMapping("/delete")
    public String deleteTask(@RequestParam int taskId) {
        taskService.deleteTask(taskId);
        return "redirect:tasklist/show";
    }

    @PostMapping("/add")
    public String addTask(@RequestBody TaskDTO taskDTO, List<Integer> taskListIds) {
        Task task = taskConverter.convertToTask(taskDTO);
        taskService.saveTask(task);
        taskListService.addTaskToTaskLists(task.getId(), taskListIds);
        return "redirect::tasklist/show";
    }

    @PostMapping("/update")
    public String updateTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskConverter.convertToTask(taskDTO);
        taskService.saveTask(task);
        return "redirect:tasklist/show";
    }

}
