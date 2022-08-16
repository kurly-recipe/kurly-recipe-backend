package com.example.demo.controller;

import com.example.demo.model.Header;
import com.example.demo.model.dto.UserCreateRequest;
import com.example.demo.model.dto.UserResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public Header<UserResponse> createUser(@RequestBody UserCreateRequest user) {
        return Header.OK(userService.createUser(user));
    }
}
