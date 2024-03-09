package com.example.shopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthPageController {
    @RequestMapping("/display-access-denied")
    public String displayAccessDenied() {
        return "auth/accessDenial";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/loginForm";
    }

    @GetMapping(value = "/login", params = "failure")
    public String loginFail(Model model) {
        model.addAttribute("failureMessage", "IDまたはパスワードが違います。");
        return "auth/loginForm";
    }

    @GetMapping(value = "/login", params = "logout")
    public String logout(Model model) {
        model.addAttribute("successMessage", "ログアウトしました。");
        return "auth/loginForm";
    }

}
