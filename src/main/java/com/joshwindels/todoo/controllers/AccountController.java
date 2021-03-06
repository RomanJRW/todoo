package com.joshwindels.todoo.controllers;

import java.util.Arrays;

import com.joshwindels.todoo.converters.UserConverter;
import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.dos.User;
import com.joshwindels.todoo.dtos.UserDTO;
import com.joshwindels.todoo.services.PasswordService;
import com.joshwindels.todoo.services.TaskListService;
import com.joshwindels.todoo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todoo")
public class AccountController {

    @Autowired
    CurrentUser currentUser;
    @Autowired
    UserService userService;
    @Autowired
    TaskListService taskListService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    UserConverter userConverter;

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
        Integer userId = userService.getUserIdForLoginDetails(username, password);
        if (userId != null) {
            currentUser.setId(userId);
            return "redirect:/todoo/lists";
        } else {
            model.addAttribute("failedLogin", "incorrectPw");
            return "login";
        }
    }

    @PostMapping("/logout")
    public String submitLogout(Model model, String username, String password) {
        currentUser.setId(null);
        currentUser.setTaskListIds(null);
        model.addAttribute("logout", "successful");
        return "login";
    }

    @PostMapping("/register")
    public String submitRegistration(Model model, UserDTO userDTO) {
        User user = userConverter.convertToUser(userDTO);
        Integer userId = userService.createNewUser(user);
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
