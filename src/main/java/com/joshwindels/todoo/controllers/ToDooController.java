package com.joshwindels.todoo.controllers;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.services.TaskListService;
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

    @GetMapping("/")
    public String begin() {
        return "redirect:lists";
    }

    @GetMapping("/lists")
    public String getTaskLists(Model model) {
        // TO DO: Have some way to identify user. Temporarily hard coding for testing purposes
        int taskListId = 2;
        TaskList taskList = taskListService.getTaskListById(taskListId);
        model.addAttribute("toDoList", taskList);
        return "taskList";
    }

}
