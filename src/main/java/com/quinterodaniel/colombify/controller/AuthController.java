package com.quinterodaniel.colombify.controller;

import com.quinterodaniel.colombify.dto.UserDto;
import com.quinterodaniel.colombify.entity.User;
import com.quinterodaniel.colombify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // handler method to handle user registration form submit request
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto userDto) throws Exception {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            throw new Exception("");
        }
        userService.saveUser(userDto);
        return ResponseEntity.ok("");
    }

    @GetMapping("/whoami")
    public ResponseEntity whoAmI(@Autowired Authentication authentication) throws Exception {
        User existingUser = userService.findUserByEmail(authentication.getName());

        return ResponseEntity.ok(existingUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> users() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
