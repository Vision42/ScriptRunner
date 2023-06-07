package com.limmerlaboratories.scriptrunner.thread;

import com.limmerlaboratories.scriptrunner.model.Script;
import com.limmerlaboratories.scriptrunner.repository.ScriptRepository;
import com.limmerlaboratories.scriptrunner.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.Instant;

public class ScriptRunner implements Runnable {
    ScriptRepository scriptRepository;
    Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
    Script script;

    public ScriptRunner(Script script, ScriptRepository scriptRepository) {
        this.script = script;
        this.scriptRepository = scriptRepository;
    }

    @Override
    public void run() {
        try {
            script.setRunning(true);
            script.setExitCode(0);
            script.setExecutionStarted(Instant.now().getEpochSecond());
            script.setExecutionEnded(0);
            scriptRepository.saveAndFlush(script);

            ProcessBuilder processBuilder = new ProcessBuilder("bash", script.getPath());
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            script.setRunning(false);
            script.setExitCode(exitCode);
            script.setExecutionEnded(Instant.now().getEpochSecond());
            scriptRepository.saveAndFlush(script);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            logger.error("ERROR while executing script: " + script.getPath());

            script.setRunning(false);
            scriptRepository.saveAndFlush(script);
        }
    }
}
