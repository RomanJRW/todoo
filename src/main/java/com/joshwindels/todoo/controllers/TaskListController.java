package com.joshwindels.todoo.controllers;

import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.CsvConverterService;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private UserService userService;
    @Autowired
    private TaskListConverter taskListConverter;

    @PostMapping("/add")
    public String addNewTaskList(TaskListDTO taskListDTO) {
        TaskList taskList = taskListConverter.convertToTaskList(taskListDTO);
        taskListService.saveNewTaskList(taskList);
        return "redirect:/todoo/lists";
    }

    @PostMapping("/add/{taskId}/{taskListId}")
    public String addTaskToTaskList(
            @PathVariable(value = "taskId") int taskId,
            @PathVariable(value = "taskListId") int taskListId) {
        taskListService.addTaskToTaskList(taskId, taskListId);
        return "redirect:/todoo/lists";
    }

    @PostMapping("/remove/{taskId}/{taskListId}")
    public String removeTaskFromList(
            @PathVariable(value = "taskId") int taskId,
            @PathVariable(value = "taskListId") int taskListId) {
        taskListService.removeTaskFromTaskList(taskId, taskListId);
        return "redirect:/todoo/lists";
    }

    @PostMapping("/delete/{taskListId}")
    public String deleteTaskList(
            @PathVariable(value = "taskListId") int taskListId) {
        taskListService.deleteTaskList(taskListId);
        return "redirect:/todoo/lists";
    }

    @PostMapping("/remove/{taskListId}/user")
    public String removeTaskListForUser(
            @PathVariable(value = "taskListId") int taskListId,
            String username) {
        Integer userId = userService.getUserIdForUsername(username);
        if (userId != null) {
            taskListService.removeTaskListForUser(taskListId, userId);
        }
        return "redirect:/todoo/lists";
    }

    @PostMapping("/add/{taskListId}/user")
    public String addTaskListForUser(
            @PathVariable(value = "taskListId") int taskListId,
            String username) {
        Integer userId = userService.getUserIdForUsername(username);
        if (userId != null) {
            taskListService.addTaskListForUser(taskListId, userId);
        }
        return "redirect:/todoo/lists";
    }

    @GetMapping("/{taskListId}/csv")
    @ResponseBody
    public String downloadTaskList(@PathVariable(value = "taskListId") int taskListId) {
        TaskList taskList = taskListService.getTaskListById(taskListId);
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
