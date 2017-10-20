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
    @Autowired
    private TaskList taskList;

    @GetMapping("/")
    public String begin() {
        return "redirect:show";
    }

    @GetMapping("/show")
    public String getTasks(Model model) {
        //TaskList taskList = taskListService.getTaskListById();
        model.addAttribute("toDoList", taskList);
        return "taskList";
    }

    @PostMapping("/add")
    public String addTaskToTaskLists(int taskId, List<Integer> taskListIds) {
        taskListService.addTaskToTaskLists(taskId, taskListIds);
        return "redirect:show";
    }

    @PostMapping("/remove")
    public String removeTaskFromList(@RequestParam int taskId, List<Integer> taskListIds) {
        taskListService.removeTaskFromTaskLists(taskId, taskListIds);
        return "redirect:show";
    }

    @GetMapping("/csv")
    @ResponseBody
    public String downloadTaskList() {
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
