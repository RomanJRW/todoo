package com.joshwindels.todoo.controllers;

import java.util.Arrays;
import java.util.List;

import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.services.PasswordService;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todoo")
public class LoginController {

    @Autowired
    CurrentUser currentUser;
    @Autowired
    UserValidationService userValidationService;
    @Autowired
    TaskListService taskListService;
    @Autowired PasswordService passwordService;

    @GetMapping("/login")
    public String logInUser() {
        if (currentUser.getId() == null) {
            return "login";
        } else {
            return "redirect:/todoo/lists";
        }
    }

    @PostMapping("/login")
    public String submitLogin(Model model, String username, String password) {
        Integer userId = userValidationService.getUserIdForLoginDetails(username, password);
        if (userId != null) {
            currentUser.setId(userId);
            List<Integer> taskListIds = taskListService.getTaskListIdsForUser(userId);
            currentUser.setTaskListIds(taskListIds);
            return "redirect:/todoo/lists";
        } else {
            model.addAttribute("failedLogin", "incorrectPw");
            return "login";
        }
    }

    @PostMapping("/register")
    public String submitRegistration(Model model, String username, String password) {
        String encryptedPassword = passwordService.getEncryptedPassword(password);
        Integer userId = userValidationService.createNewUser(username, encryptedPassword);
        if (userId != null) {
            currentUser.setId(userId);
            currentUser.setTaskListIds(Arrays.asList());
            return "redirect:/todoo/lists";
        } else {
            model.addAttribute("failedRegistration", "error");
            return "login";
        }
    }

}
