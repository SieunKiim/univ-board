package com.sieun.univboard.login;

import com.sieun.univboard.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginServiceImpl loginServiceImpl;

    public LoginController(LoginServiceImpl loginServiceImpl) {
        this.loginServiceImpl = loginServiceImpl;
    }

    @GetMapping("")
    public String login() {
        return "login/sign-in";
    }

//    @PostMapping("/sign-in")
//    public String signIn(LoginSigninDTO loginSigninDTO) {
//
//    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "login/sign-up";
    }

    @PostMapping("/sign-up")
    public String userSave(LoginSignUpDTO loginSignUpDTO){
        String msg = "";
        log.info(loginSignUpDTO.toString());
        String newPwd = loginServiceImpl.makeHash(loginSignUpDTO.getPwd());
        User user = new User(loginSignUpDTO.getUserId(), loginSignUpDTO.getNickName(), newPwd, loginSignUpDTO.getEmail());

        if(!loginServiceImpl.noEmptyInstance(user)){
            log.info("비어있는 정보 있음" + user);
            return "login/sign-up";
        }
        if (loginServiceImpl.save(user)) { // 저장이 된 경우
            log.info("가입 성공 - DB 저장");
            return "login/sign-in";
        } else {
            log.info("중복된 사용자");
            return "login/sign-up";
        }
    }
}
