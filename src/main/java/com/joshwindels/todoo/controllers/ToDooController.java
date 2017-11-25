package com.joshwindels.todoo.controllers;

import com.joshwindels.todoo.dos.CurrentUser;
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
        //Note the getting of the first task list at the end. Hardcoded temporarily
        TaskListDTO taskListDTO = taskListConverter.convertToTaskDTO(
                taskListService.getTaskListById(currentUser.getTaskListIds().get(0)));
        model.addAttribute("toDoList", taskListDTO);
        return "taskList";
    }

}
