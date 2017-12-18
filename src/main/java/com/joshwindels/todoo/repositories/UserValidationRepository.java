package com.joshwindels.todoo.repositories;

public interface UserValidationRepository {

    Integer getUserIdForUserNameAndPassword(String username, String password);

}
