package com.limmerlaboratories.scriptrunner.controller;

import com.limmerlaboratories.scriptrunner.model.Script;
import com.limmerlaboratories.scriptrunner.repository.ScriptRepository;
import com.limmerlaboratories.scriptrunner.service.ScriptRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

}
