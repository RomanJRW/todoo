package com.joshwindels.todoo.converters;

import com.joshwindels.todoo.dos.User;
import com.joshwindels.todoo.dtos.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

}
