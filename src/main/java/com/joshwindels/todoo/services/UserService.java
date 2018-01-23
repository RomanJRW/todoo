package com.joshwindels.todoo.services;

import java.util.List;

import com.joshwindels.todoo.dos.User;

public interface UserService {

    Integer getUserIdForLoginDetails(String username, String password);

    Integer getUserIdForUsername(String username);

    Integer createNewUser(User user);

    List<User> getAllUsers();
}
