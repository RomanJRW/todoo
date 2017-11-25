package com.joshwindels.todoo.services;

public interface UserValidationService {

    Integer getUserIdForLoginDetails(String username, String password);

}
