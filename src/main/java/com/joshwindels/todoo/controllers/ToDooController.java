package com.joshwindels.todoo.controllers;

import com.joshwindels.todoo.CurrentUser;
import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todoo")
public class ToDooController {

    @Autowired
    TaskListService taskListService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskListConverter taskListConverter;
    @Autowired
    CurrentUser currentUser;

    @GetMapping("/lists")
    public String getTaskLists(Model model) {
        currentUser.getId();
        TaskListDTO taskListDTO = taskListConverter.convertToTaskDTO(
                taskListService.getTaskListById(currentUser.getId()));
        model.addAttribute("toDoList", taskListDTO);
        return "taskList";
    }

}
