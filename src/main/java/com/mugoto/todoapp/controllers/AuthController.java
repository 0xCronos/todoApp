package com.mugoto.todoapp.controllers;

import com.mugoto.todoapp.shared.auth.Authentication;
import com.mugoto.todoapp.user.application.UserService;
import com.mugoto.todoapp.user.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final Authentication auth;

    public AuthController(UserService userService, Authentication auth) {
        this.userService = userService;
        this.auth = auth;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User user = userService.validateUser(email, password);

        return new ResponseEntity<>(auth.generateToken(user), HttpStatus.OK);
    }
}
