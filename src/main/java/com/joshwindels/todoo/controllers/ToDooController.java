package com.joshwindels.todoo.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.converters.UserConverter;
import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.dos.User;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.dtos.UserDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
import com.joshwindels.todoo.services.UserService;
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
    UserConverter userConverter;
    @Autowired
    CurrentUser currentUser;
    @Autowired
    UserTaskListSharingService userTaskListSharingService;
    @Autowired
    UserService userService;

    @GetMapping("/lists")
    public String getTaskLists(Model model) {
        model.addAttribute("toDoLists", populateTaskLists());
        model.addAttribute("shareableUsers", populateShareableUsers());
        return "taskList";
    }

    private List<UserDTO> populateShareableUsers() {
        return userService.getAllUsers().stream()
                .map(user -> userConverter.convertToUserDTO(user))
                .collect(Collectors.toList());
    }

    private List<TaskListDTO> populateTaskLists() {
        return taskListService.getTaskListsForUser(currentUser.getId()).stream()
                .map(tl -> taskListConverter.convertToTaskListDTO(tl))
                .collect(Collectors.toList());
    }

}
