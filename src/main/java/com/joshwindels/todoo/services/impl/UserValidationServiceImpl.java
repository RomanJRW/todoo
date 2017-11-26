package com.joshwindels.todoo.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.joshwindels.todoo.services.UserValidationService;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    private static Map<Map<String, String>, Integer> userAuthMap;

    UserValidationServiceImpl() {
        userAuthMap = new HashMap<>();
        userAuthMap.put(Collections.singletonMap("User1Test", "PW1"), 1);
        userAuthMap.put(Collections.singletonMap("User2Test", "PW2"), 2);
    }

    @Override
    public Integer getUserIdForLoginDetails(String username, String password) {
        return userAuthMap.get(Collections.singletonMap(username, password));
    }

}
