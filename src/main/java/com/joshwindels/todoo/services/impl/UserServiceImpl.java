package com.joshwindels.todoo.services.impl;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.joshwindels.todoo.dos.User;
import com.joshwindels.todoo.repositories.UserRepository;
import com.joshwindels.todoo.services.PasswordService;
import com.joshwindels.todoo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordService passwordService;

    @Autowired UserRepository userRepository;

    @Override
    public Integer getUserIdForLoginDetails(String username, String password) {
        if (credentialsAreValid(username, password)) {
            boolean isCorrectPassword =
                    passwordService.isCorrectPasswordForUsername(username, password);
            if (isCorrectPassword) {
                return userRepository.getUserIdForUserName(username);
            }
        }
        return null;
    }

    @Override
    public Integer getUserIdForUsername(String username) {
        return userRepository.getUserIdForUserName(username);
    }

    @Override
    public Integer createNewUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (credentialsAreValid(username, password)
                && usernameIsAvailable(username)) {
            String encryptedPassword = passwordService.getEncryptedPassword(password);
            user.setPassword(encryptedPassword);
            return userRepository.createNewUser(user);
        } else {
            return null;
        }
    }

    private boolean credentialsAreValid(String username, String password) {
        return usernameIsValid(username) && passwordIsValid(password);
    }

    private boolean usernameIsValid(String username) {
        if (username == null || StringUtils.isEmpty(username)) {
            return false;
        }

        boolean isValid = true;
        try {
            InternetAddress emailAddr = new InternetAddress(username);
            emailAddr.validate();
        } catch (AddressException ex) {
            isValid = false;
        }
        return isValid;
    }

    private boolean passwordIsValid(String password) {
        if (password != null && !StringUtils.isEmpty(password)) {
            return true;
        }
        return false;
    }

    private boolean usernameIsAvailable(String username) {
        return userRepository.getUserIdForUserName(username) == null;
    }

}
