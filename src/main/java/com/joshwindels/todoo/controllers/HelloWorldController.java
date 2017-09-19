package com.joshwindels.todoo.controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloResponse(@RequestParam String name, Model model) {

        String shouteyName = name.toUpperCase();
        model.addAttribute("name", shouteyName);

        model.addAttribute("now", new Date());

        return "helloWorld";
    }

}
