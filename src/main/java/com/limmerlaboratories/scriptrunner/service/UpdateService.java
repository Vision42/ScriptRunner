package com.limmerlaboratories.scriptrunner.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UpdateService {
    private String version;
    private String latestVersion;

    public UpdateService(Environment environment) {
        version = environment.getProperty("app.version");
    }

    public boolean checkForUpdate() {

        return false;
    }

    public boolean updateToLatestVersion() {
        return false;
    }

    public String getLatestVersion() {
        return latestVersion;
    }
}
