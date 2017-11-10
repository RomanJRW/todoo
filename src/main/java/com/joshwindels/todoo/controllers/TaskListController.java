package com.joshwindels.todoo.controllers;

import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.services.CsvConverterService;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tasklist")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private CsvConverterService csvConverterService;

    @PostMapping("/add")
    public String addTaskToTaskList(@RequestParam int taskId, @RequestParam int taskListId) {
        taskListService.addTaskToTaskList(taskId, taskListId);
        return "redirect:/todoo/lists";
    }

    @PostMapping("/remove")
    public String removeTaskFromList(@RequestParam int taskId, @RequestParam int taskListId) {
        taskListService.removeTaskFromTaskList(taskId, taskListId);
        return "redirect:/todoo/lists";
    }

    @GetMapping("/csv")
    @ResponseBody
    public String downloadTaskList(@RequestParam int taskListId) {
        TaskList taskList = taskListService.getTaskListById(taskListId);
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
