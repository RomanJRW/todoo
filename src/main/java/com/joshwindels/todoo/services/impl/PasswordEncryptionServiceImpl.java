package com.joshwindels.todoo.services.impl;

import java.security.NoSuchAlgorithmException;

import com.amdelamar.jhash.Hash;
import com.amdelamar.jhash.exception.BadOperationException;
import com.joshwindels.todoo.services.PasswordEncryptionService;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncryptionServiceImpl implements PasswordEncryptionService {

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
}
