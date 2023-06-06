package com.limmerlaboratories.scriptrunner.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "admin")
public class FrontendController {

    @GetMapping(path = "dashboard")
    public String dashboard() {
        return "dashboard";
    }
}
