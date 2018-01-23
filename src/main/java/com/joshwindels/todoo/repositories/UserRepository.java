package com.joshwindels.todoo.repositories;

import java.util.List;

import com.joshwindels.todoo.dos.User;

public interface UserRepository {

    Integer getUserIdForUserName(String username);

    Integer createNewUser(User user);

    String getStoredPasswordForUsername(String username);

    List<User> getAllUsers();
}
