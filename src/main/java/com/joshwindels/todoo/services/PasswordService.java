package com.joshwindels.todoo.services;

public interface PasswordService {

    String getEncryptedPassword(String password);

    boolean isCorrectPassword(String username, String password);
}
