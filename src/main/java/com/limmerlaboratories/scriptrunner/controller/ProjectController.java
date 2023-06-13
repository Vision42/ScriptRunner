package com.limmerlaboratories.scriptrunner.controller;

import com.limmerlaboratories.scriptrunner.model.Project;
import com.limmerlaboratories.scriptrunner.model.Script;
import com.limmerlaboratories.scriptrunner.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping(path = "api/v1/project")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping(path = "list")
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @GetMapping(path = "{id}/scripts")
    public List<Script> getProjectScripts(@PathVariable("id") int id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project != null) {
            return project.getScripts();
        }

        return null;
    }

}
