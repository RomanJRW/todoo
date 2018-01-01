package com.joshwindels.todoo.repositories;

public interface UserRepository {

    Integer getUserIdForUserName(String username);

    Integer createNewUser(String username, String encryptedPassword);

    String getStoredPasswordForUsername(String username);
}
