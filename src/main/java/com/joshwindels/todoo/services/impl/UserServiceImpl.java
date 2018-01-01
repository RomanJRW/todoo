package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.repositories.UserRepository;
import com.joshwindels.todoo.services.PasswordService;
import com.joshwindels.todoo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordService passwordService;

    @Autowired UserRepository userRepository;

    @Override
    public Integer getUserIdForLoginDetails(String username, String password) {
        if (username != null || password != null) {
            boolean isCorrectPassword =
                    passwordService.isCorrectPasswordForUsername(username, password);
            if (isCorrectPassword) {
                return userRepository.getCurrentUserIdForUserName(username);
            }
        }
        return null;
    }

    @Override
    public Integer createNewUser(String username, String password) {
        if (username != null || password != null) {
            String encryptedPassword = passwordService.getEncryptedPassword(password);
            return userRepository.createNewUser(username, encryptedPassword);
        } else {
            return null;
        }
    }

}
