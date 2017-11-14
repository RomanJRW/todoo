package com.joshwindels.todoo.controllers;

import javax.servlet.http.HttpSession;

import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dos.TaskList;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.CsvConverterService;
import com.joshwindels.todoo.services.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tasklist")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;
    @Autowired
    private CsvConverterService csvConverterService;
    @Autowired
    private TaskListConverter taskListConverter;

    @PostMapping("/add")
    public String addNewTaskList(HttpSession session, TaskListDTO taskListDTO) {
        TaskList taskList = taskListConverter.convertToTaskList(taskListDTO);
        taskList = taskListService.saveNewTaskList(taskList);
        session.setAttribute(ToDooController.COOKIE_VAL, taskList.getId());
        return "redirect:/todoo/lists";
    }

    @PostMapping("/add/{taskId}/{taskListId}")
    public String addTaskToTaskList(@PathVariable(value = "taskId") int taskId,
            @PathVariable(value = "taskListId") int taskListId) {
        taskListService.addTaskToTaskList(taskId, taskListId);
        return "redirect:/todoo/lists";
    }

    @PostMapping("/remove/{taskId}/{taskListId}")
    public String removeTaskFromList(@PathVariable(value = "taskId") int taskId,
            @PathVariable(value = "taskListId") int taskListId) {
        taskListService.removeTaskFromTaskList(taskId, taskListId);
        return "redirect:/todoo/lists";
    }

    @GetMapping("/{taskListId}/csv")
    @ResponseBody
    public String downloadTaskList(@PathVariable(value = "taskListId") int taskListId) {
        TaskList taskList = taskListService.getTaskListById(taskListId);
        return csvConverterService.convertTaskListToCsv(taskList);
    }
}
