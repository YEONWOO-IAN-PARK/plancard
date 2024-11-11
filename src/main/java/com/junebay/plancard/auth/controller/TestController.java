package com.junebay.plancard.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Controller
public class TestController {

    @GetMapping("/my")
    @ResponseBody
    public String test() {

        return "success!";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
