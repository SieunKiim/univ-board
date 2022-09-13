package com.sieun.univboard.login;

import com.sieun.univboard.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("")
    public String login() {
        return "/login/sign-in";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "/login/sign-up";
    }

    @PostMapping("/sign-up")
    public String userSave(LoginSignUpDTO loginSignUpDTO){
        log.info(loginSignUpDTO.toString());
        String newPwd = loginService.makeHash(loginSignUpDTO.getPwd());
        User user = new User(loginSignUpDTO.getUserId(), loginSignUpDTO.getNickName(), newPwd, loginSignUpDTO.getEmail());
        if (loginService.save(user)) { // 저장이 된 경우
            return "/login/sign-in";
        } else {
            return "";
        }
    }
}
