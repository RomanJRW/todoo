package com.joshwindels.todoo.controllers;

import java.util.List;

import com.joshwindels.todoo.converters.TaskConverter;
import com.joshwindels.todoo.dos.Task;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.dtos.TaskDTO;
import com.joshwindels.todoo.services.CsvConverterService;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @Autowired
    private TaskConverter taskConverter;
    //temporary for compilation and testing

    @PostMapping("/add")
    public String addTaskToTaskLists(@RequestParam int taskId, @RequestParam List<Integer> taskListIds) {
        taskListService.addTaskToTaskLists(taskId, taskListIds);
        return "redirect:todoo/lists";
    }

    @PostMapping("/remove")
    public String removeTaskFromList(@RequestParam int taskId, @RequestParam List<Integer> taskListIds) {
        taskListService.removeTaskFromTaskLists(taskId, taskListIds);
        return "redirect:todoo/lists";
    }

    @GetMapping("/csv")
    @ResponseBody
    public String downloadTaskList(@RequestParam int taskListId) {
        TaskList taskList = taskListService.getTaskListById(taskListId);
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
