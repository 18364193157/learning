package com.lenho.learning.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author langyonghe
 * @date 2020/10/28 15:29
 */
@Controller
@RequestMapping("/learn")
public class TestController {

    @ResponseBody
    @GetMapping("/test")
    public String test(String param){
        return param;
    }
}
