package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.repositories.UserValidationRepository;
import com.joshwindels.todoo.services.PasswordService;
import com.joshwindels.todoo.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    @Autowired
    PasswordService passwordService;
    @Autowired
    UserValidationRepository userValidationRepository;

    @Override
    public Integer getUserIdForLoginDetails(String username, String password) {
        if (username != null || password != null) {
            String encryptedPassword = passwordService.getEncryptedPassword(password);
            if (encryptedPassword != null) {
                return userValidationRepository.getCurrentUserForUserNameAndPassword(username, password);
            }
        }
        return null;
    }

    @Override
    public Integer createNewUser(String username, String encryptedPassword) {
        return userValidationRepository.createNewUser(username, encryptedPassword);
    }

}
