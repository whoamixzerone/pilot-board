package com.pilot.board.controller;

import com.pilot.board.domain.user.User;
import com.pilot.board.domain.user.dto.SignUpRequest;
import com.pilot.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @GetMapping("/signUp")
    public String signUpPage() {
        return "user/signUp";
    }

    @PostMapping
    public String signUp(@Valid SignUpRequest signUpRequest) {
        User user = userService.signUp(signUpRequest.toEntity());
        log.info("user: {}", user);
        if (user == null) {
            throw new IllegalStateException("회원가입 실패");
        }

        return "redirect:/boards";
    }

    @GetMapping("/signIn")
    public String signInPage() {
        return "user/signIn";
    }
}
