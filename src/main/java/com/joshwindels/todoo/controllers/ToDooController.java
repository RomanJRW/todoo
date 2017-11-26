package com.joshwindels.todoo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
import com.sun.tools.corba.se.idl.InterfaceGen;
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
        List<TaskListDTO> taskLists = new ArrayList<>();
        for (Integer taskListId : currentUser.getTaskListIds()) {
            model.addAttribute(taskListConverter.convertToTaskDTO(
                    taskListService.getTaskListById(taskListId)));
        }
        model.addAttribute("toDoLists", taskLists);
        return "taskList";
    }

}
