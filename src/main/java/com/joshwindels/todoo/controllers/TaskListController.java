package com.joshwindels.todoo.controllers;

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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tasks")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private CsvConverterService csvConverterService;
    @Autowired
    private TaskList taskList;
    @Autowired
    private TaskConverter taskConverter;

    @GetMapping("/")
    public String begin() {
        return "redirect:show";
    }

    @GetMapping("/show")
    public String getTasks(Model model) {
        model.addAttribute("toDoList", taskList);
        return "taskList";
    }

    @PostMapping("/add")
    public String addTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskConverter.convertToTask(taskDTO);
        taskListService.addTaskToList(task, taskList);
        return "redirect:show";
    }

    @PostMapping("/remove")
    public String removeTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskConverter.convertToTask(taskDTO);
        taskListService.removeTaskFromList(task, taskList);
        return "redirect:show";
    }

    @PostMapping("/update")
    public String updateTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskConverter.convertToTask(taskDTO);
        taskListService.updateTask(task);
        return "redirect:show";
    }

    @GetMapping("/csv")
    @ResponseBody
    public String downloadTaskList() {
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
