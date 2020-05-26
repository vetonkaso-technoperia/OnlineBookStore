package com.example.EmT.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private List<String> allowedNames;

    @PostConstruct
    public void init() {
        this.allowedNames = new ArrayList<>();
        this.allowedNames.add("EmTUser");
    }

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

//    @PostMapping
//    public String loginUser(HttpServletRequest req) {
//        String username = req.getParameter("username");
//        if (username != null && this.allowedNames.contains(username)) {
//            req.getSession().setAttribute("username", username);
//            return "redirect:/home";
//        } else {
//            return "redirect:/login";
//        }
//    }
}
