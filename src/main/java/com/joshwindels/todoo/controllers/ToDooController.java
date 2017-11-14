package com.joshwindels.todoo.controllers;

import javax.servlet.http.HttpSession;

import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Scope("session")
@Controller
@RequestMapping("/todoo")
public class ToDooController {

    @Autowired
    TaskListService taskListService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskListConverter taskListConverter;

    static final String COOKIE_VAL = "toDooCookie";

    @GetMapping("/lists")
    public String getTaskLists(HttpSession session, Model model) {
        Object cookieVal = session.getAttribute(COOKIE_VAL);
        int taskListId = -1;
        if (cookieVal != null && (int) cookieVal > 0) {
            taskListId = (int) cookieVal;
        }
        TaskListDTO taskListDTO = taskListConverter.convertToTaskDTO(
                taskListService.getTaskListById(taskListId));
        model.addAttribute("toDoList", taskListDTO);
        return "taskList";
    }

}
