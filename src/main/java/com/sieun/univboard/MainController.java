package com.sieun.univboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class MainController {
    @GetMapping("")
    public String getAricleList(Model model) {
        return "/index";
    }


    @GetMapping("/article")
    public String writeArticle() { // 가기 전에 로그인 했는지 확인
        return "/article";
    }
}
