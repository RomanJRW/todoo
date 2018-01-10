package com.joshwindels.todoo.services;

public interface UserService {

    Integer getUserIdForLoginDetails(String username, String password);

    Integer getUserIdForUsername(String username);

    Integer createNewUser(String username, String password);

}
