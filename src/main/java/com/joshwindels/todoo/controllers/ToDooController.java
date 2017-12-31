package com.joshwindels.todoo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.joshwindels.todoo.converters.TaskListConverter;
import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.dtos.TaskListDTO;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.TaskService;
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

    @GetMapping("/lists")
    public String getTaskLists(Model model) {
        List<TaskListDTO> taskLists = new ArrayList<>();
        List<Integer> taskListIds = taskListService.getTaskListIdsForUser(currentUser.getId());
        for (Integer taskListId : taskListIds) {
            taskLists.add(taskListConverter.convertToTaskDTO(
                    taskListService.getTaskListById(taskListId)));
        }
        model.addAttribute("toDoLists", taskLists);
        return "taskList";
    }

}
