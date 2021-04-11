package com.lenho.learning.controller;

import com.lenho.learning.event.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author langyonghe
 * @date 2021/4/8 10:56
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register (  String username) {
        userService.register(username);
        return "success";
    }
}
