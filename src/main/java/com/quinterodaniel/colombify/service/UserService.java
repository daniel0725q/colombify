package com.quinterodaniel.colombify.service;

import com.quinterodaniel.colombify.dto.UserDto;
import com.quinterodaniel.colombify.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
