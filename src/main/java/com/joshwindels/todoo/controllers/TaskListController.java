package com.joshwindels.todoo.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.joshwindels.todoo.dos.ToDoList;
import com.joshwindels.todoo.services.CsvConverterService;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("request")
@RequestMapping("/tasks")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private CsvConverterService csvConverterService;
    @Autowired
    private ToDoList taskList;

    private static final String COOKIE_IDENTIFIER = "toDoCookie";

    @GetMapping("/")
    public String begin(@CookieValue(value = COOKIE_IDENTIFIER, required = false) String cookieValue,
            HttpServletResponse response) {
        BeanUtils.copyProperties(taskList, taskListService.getExistingTaskListFromFromFile(cookieValue));
        if (taskList.getIdentifier() == null) {
            String identifier = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            response.addCookie(new Cookie(COOKIE_IDENTIFIER, identifier));
            taskList.setIdentifier(identifier);
        }
        return "redirect:show";
    }

    @GetMapping("/show")
    public String getTasks(Model model) {
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

    @GetMapping("/csv")
    @ResponseBody
    public String download() {
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
