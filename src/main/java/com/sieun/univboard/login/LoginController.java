package com.sieun.univboard.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping("")
    public String login() {
        return "/login/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "/login/sign-up";
    }
}
