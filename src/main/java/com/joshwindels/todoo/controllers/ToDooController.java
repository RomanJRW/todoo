package com.joshwindels.todoo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
import com.joshwindels.todoo.services.UserTaskListSharingService;
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
    @Autowired
    UserTaskListSharingService userTaskListSharingService;

    @GetMapping("/lists")
    public String getTaskLists(Model model) {
        model.addAttribute("toDoLists", populateTaskLists());
        model.addAttribute("shareableUsers", populateShareableUsers());
        return "taskList";
    }

    private Object populateShareableUsers() {
        return null;

    }

    private List<TaskListDTO> populateTaskLists() {
        List<TaskListDTO> taskLists = new ArrayList<>();
        int userId = currentUser.getId();
        taskListService.getTaskListIdsForUser(userId).stream()
                .forEach(tl -> taskLists.add(convertToDTO(tl)));
        return taskLists;
    }

    private TaskListDTO convertToDTO(Integer taskListId) {
        return taskListConverter.convertToTaskListDTO(
                taskListService.getTaskListById(taskListId));
    }

}
