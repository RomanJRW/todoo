package com.joshwindels.todoo.repositories;

public interface UserValidationRepository {

    int getUserIdForUserNameAndPassword(String username, String password);

}
