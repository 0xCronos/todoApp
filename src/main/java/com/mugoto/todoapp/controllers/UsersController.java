package com.mugoto.todoapp.controllers;

import com.mugoto.todoapp.shared.auth.Authentication;
import com.mugoto.todoapp.user.domain.User;
import com.mugoto.todoapp.user.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;
    private final Authentication auth;

    @Autowired
    public UsersController(UserService userService, Authentication auth) {
        this.userService = userService;
        this.auth = auth;
    }

    @PostMapping()
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, Object> userMap) {
        String username = (String) userMap.get("username");
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");
        String repeatPassword = (String) userMap.get("repeatPassword");

        User user = userService.createUser(username, email, password, repeatPassword);

        return new ResponseEntity<>(auth.generateToken(user), HttpStatus.CREATED);
    }
}
