package com.joshwindels.todoo.services.impl;

import java.security.NoSuchAlgorithmException;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.exception.BadOperationException;
import com.amdelamar.jhash.exception.InvalidHashException;
import com.joshwindels.todoo.repositories.UserRepository;
import com.joshwindels.todoo.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired UserRepository userRepository;

    @Override
    public String getEncryptedPassword(String password) {
        String encryptedPassword = null;
        try {
            encryptedPassword = Hash.create(password);
        } catch (BadOperationException | NoSuchAlgorithmException ex) {
            // add better logging later, and better exception throwing
            System.out.print("problem hashing password");
        }
        return encryptedPassword;
    }

    @Override
    public boolean isCorrectPasswordForUsername(String username, String password) {
        String storedPassword = userRepository.getStoredPasswordForUsername(username);
        if (storedPassword != null) {
            try {
                return Hash.verify(password, storedPassword);
            } catch (NoSuchAlgorithmException | InvalidHashException | BadOperationException e) {
                // add better logging later, and better exception throwing
                System.out.print("problem verifying password");
            }
        }
        return false;
    }

}
