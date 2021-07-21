package com.mugoto.todoapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/api/todolists")
public class TodoListController {

    @GetMapping("")
    public String getAllTodoLists(HttpServletRequest request){
        String user = (String) request.getAttribute("userId");
        return "Authenticated! UserId: " + user;
    }
}
