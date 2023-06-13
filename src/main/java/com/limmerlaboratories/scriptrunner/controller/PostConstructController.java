package com.limmerlaboratories.scriptrunner.controller;

import com.limmerlaboratories.scriptrunner.service.ConfigurationService;
import com.limmerlaboratories.scriptrunner.service.UpdateService;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PostConstructController {
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private UpdateService updateService;

    @PostConstruct
    public void init() {
        configurationService.init();

        if (updateService.checkForUpdate()) {
            log.info("Update to version " + updateService.getLatestVersion() + " is available");
        }
    }
}
