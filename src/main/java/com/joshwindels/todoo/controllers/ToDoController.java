package com.joshwindels.todoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ToDoController {

    // This works as initial task, leave to prove that works - not a particularly useful method though
    @GetMapping("/toDo")
    public String toDo(@RequestParam String[] list, Model model) {

        int total = list.length;
        model.addAttribute("listTotal", total);

        model.addAttribute("toDoList", list);

        return "toDo";

    }

}
