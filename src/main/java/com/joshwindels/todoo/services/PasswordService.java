package com.joshwindels.todoo.services;

public interface PasswordService {

    String getEncryptedPassword(String password);

    boolean isCorrectPasswordForUsername(String username, String password);

}
