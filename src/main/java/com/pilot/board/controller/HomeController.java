package com.pilot.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String index() {
        System.out.println("HomeController.index");
        return "redirect:/boards";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/error-page")
    public String errorPage(@RequestParam("error") String error, Model model) {
        model.addAttribute("error", error);

        return "error";
    }
}
