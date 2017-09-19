package com.joshwindels.todoo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.joshwindels.todoo.dos.ToDoList;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tasks")
public class TaskListController {

    @Autowired
    TaskListService taskListService;

    private final String COOKIE_IDENTIFIER = "toDoCookie";
    private final String NO_COOKIE_FOUND = "noCookie";

    private ToDoList taskList;

    @GetMapping("/show")
    public String getTasks(@CookieValue(value = COOKIE_IDENTIFIER, defaultValue = NO_COOKIE_FOUND) String cookieValue,
            HttpServletResponse response, Model model) {
        if (cookieValue.equals(NO_COOKIE_FOUND)) {
            String listIdentifier = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            System.out.println("No cookie found, add identifier: " + listIdentifier);
            response.addCookie(new Cookie(COOKIE_IDENTIFIER, listIdentifier));
            taskList = new ToDoList(listIdentifier);
        }
        else {
            System.out.println("Cookie found, retrieving  list for identifier: " + cookieValue);
            taskList = taskListService.getExistingTaskListFromFromFile(cookieValue);
        }
        model.addAttribute("toDoList", taskList);
        return "taskList";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        taskListService.addTaskToList(task, taskList);
        return "redirect:show";
    }

    @PostMapping("/remove")
    public String removeTask(@RequestParam String task) {
        taskListService.removeTaskFromList(task, taskList);
        return "redirect:show";
    }

}
