package com.joshwindels.todoo.services;

import com.joshwindels.todoo.dos.CurrentUser;

public interface UserValidationService {

    Integer getUserIdForLoginDetails(String username, String password);

    Integer createNewUser(String username, String password);

}
