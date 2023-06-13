package com.limmerlaboratories.scriptrunner.controller;

import com.limmerlaboratories.scriptrunner.model.Script;
import com.limmerlaboratories.scriptrunner.repository.ScriptRepository;
import com.limmerlaboratories.scriptrunner.service.ScriptRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(path = "api/v1/script")
public class ScriptController {

    @Autowired
    private ScriptRepository scriptRepository;
    @Autowired
    private ScriptRunnerService scriptRunnerService;

    @GetMapping(path = "list")
    public List<Script> getScripts() {
        return scriptRepository.findAll();
    }

    @GetMapping(path = "execute/{id}")
    public boolean executeScript(@PathVariable("id") int id) {
        return scriptRunnerService.runScriptById(id);
    }

    @GetMapping(path = "info/{id}")
    public Script getScript(@PathVariable("id") int id) {
        return scriptRepository.findById(id).orElse(null);
    }

}
