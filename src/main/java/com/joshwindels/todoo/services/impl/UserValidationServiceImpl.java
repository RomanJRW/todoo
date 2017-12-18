package com.joshwindels.todoo.services.impl;

import com.joshwindels.todoo.dos.CurrentUser;
import com.joshwindels.todoo.repositories.UserValidationRepository;
import com.joshwindels.todoo.services.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    @Autowired
    UserValidationRepository userValidationRepository;

    @Override
    public Integer getUserIdForLoginDetails(String username, String encryptedPassword) {
        if (username != null || encryptedPassword != null) {
            return null;
        } else {
            return userValidationRepository.getCurrentUserForUserNameAndPassword(username, encryptedPassword);
        }
    }

    @Override
    public Integer createNewUser(String username, String encryptedPassword) {
        return userValidationRepository.createNewUser(username, encryptedPassword);
    }

}
