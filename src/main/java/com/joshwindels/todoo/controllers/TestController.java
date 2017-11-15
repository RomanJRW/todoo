package com.joshwindels.todoo.controllers;

import com.joshwindels.todoo.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private CurrentUser currentUser;

    @GetMapping("/set")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setId(@RequestParam int id) {
        currentUser.setId(id);
    }

    @GetMapping("/get")
    @ResponseBody
    public String getId() {
        return currentUser.getId().toString();
    }

}
