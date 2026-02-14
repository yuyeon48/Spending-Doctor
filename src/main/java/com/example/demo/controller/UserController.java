package com.example.demo.controller;

import com.example.demo.entity.SiteUser;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder; // 추가된 import
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup() {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(String username, String password, String email) {
        // 비밀번호를 암호화하여 저장합니다.
        SiteUser user = SiteUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        userRepository.save(user);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
}