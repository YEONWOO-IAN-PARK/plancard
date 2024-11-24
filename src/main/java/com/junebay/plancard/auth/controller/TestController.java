package com.junebay.plancard.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author : IAN
 * @date : 2024-11-08
 * @description :
 */
@Controller
public class TestController {

    /**
     *  테스트 요청 핸들러 메서드
     *
     * @param name 이름
     * @param id 아이디
     * @return "success" 라는 문자열을 반환한다.
     */
    @GetMapping("/my")
    @ResponseBody
    public String test(String name, String id) {

        return "success!";
    }

    /**
     * home.html 페이지를 요청하는 요청 핸들러 메서드
     * @return home.html을 화면에 보여준다.
     */
    @GetMapping("/home")
    public String index() {
        return "redirect:/view/home.html";
    }
}
