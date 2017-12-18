package com.joshwindels.todoo.repositories;

public interface UserValidationRepository {

    Integer getCurrentUserForUserNameAndPassword(String username, String password);

    Integer createNewUser(String username, String encryptedPassword);

}
