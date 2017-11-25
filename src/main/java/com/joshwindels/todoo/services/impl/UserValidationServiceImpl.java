package com.joshwindels.todoo.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.services.UserValidationService;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    private static final Map<Map<String, String>, Integer> userAuthMap = new HashMap() {
        {
            put(new HashMap<>().put("User1Test", "PW1"), 1);
            put(new HashMap<>().put("User2Test", "PW2"), 2);
        }
    };

    @Override
    public Integer getUserIdForLoginDetails(String username, String password) {
        return userAuthMap.get(Collections.singletonMap(username, password));
    }

}
