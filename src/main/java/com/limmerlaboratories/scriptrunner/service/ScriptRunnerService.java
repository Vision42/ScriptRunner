package com.limmerlaboratories.scriptrunner.service;

import com.limmerlaboratories.scriptrunner.model.Script;
import com.limmerlaboratories.scriptrunner.repository.ScriptRepository;
import com.limmerlaboratories.scriptrunner.thread.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptRunnerService {
    @Autowired
    ScriptRepository scriptRepository;

    public boolean runScriptById(int id) {
        Script script = scriptRepository.findById(id).orElse(null);

        if (script != null) {
            ScriptRunner scriptRunner = new ScriptRunner(script, scriptRepository);
            Thread thread = new Thread(scriptRunner);
            thread.start();

            return true;
        }

        return false;
    }

}
