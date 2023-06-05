package com.limmerlaboratories.scriptrunner.controller;

import com.limmerlaboratories.scriptrunner.service.ConfigurationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConstructController {
    @Autowired
    private ConfigurationService configurationService;

    @PostConstruct
    public void init() {
        configurationService.init();
    }
}
