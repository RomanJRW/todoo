package com.joshwindels.todoo.services;

public interface UserService {

    Integer getUserIdForLoginDetails(String username, String password);

    Integer createNewUser(String username, String password);

}
