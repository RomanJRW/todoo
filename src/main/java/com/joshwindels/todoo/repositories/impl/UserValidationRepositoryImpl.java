package com.joshwindels.todoo.repositories.impl;

import com.joshwindels.todoo.repositories.UserValidationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserValidationRepositoryImpl implements UserValidationRepository {

    @Override
    public int getUserIdForUserNameAndPassword(String username, String password) {


        return 0;
    }

}
