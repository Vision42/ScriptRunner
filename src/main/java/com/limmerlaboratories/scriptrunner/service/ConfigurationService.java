package com.limmerlaboratories.scriptrunner.service;

import com.limmerlaboratories.scriptrunner.model.Project;
import com.limmerlaboratories.scriptrunner.model.Script;
import com.limmerlaboratories.scriptrunner.repository.ProjectRepository;
import com.limmerlaboratories.scriptrunner.repository.ScriptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ConfigurationService {

    Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
    @Autowired
    private ScriptRepository scriptRepository;
    @Autowired
    private ProjectRepository projectRepository;

    private File mainConfigFile = null;

    public ConfigurationService() {

    }

    public void init() {
        String pathToHomeConfig = System.getProperty("user.home") + "/opt/LlScriptRunner/config.cnf";

        try {
            mainConfigFile = new File(pathToHomeConfig);

            if (!mainConfigFile.getParentFile().isDirectory()) {
                mainConfigFile.getParentFile().mkdirs();
            }

            if (!mainConfigFile.exists()) {
                mainConfigFile.createNewFile();
            }

            this.loadConfiguration();

        } catch (IOException exception) {
            exception.printStackTrace();
            logger.error("Could not create configuration file at " + pathToHomeConfig);
        }
    }

    private void loadConfiguration() {
        try {
            scriptRepository.deleteAll();
            projectRepository.deleteAll();

            BufferedReader reader = new BufferedReader(new FileReader(mainConfigFile));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("[PROJECTS]")) {
                    while (!(line = reader.readLine()).equals("[END]")) {
                        if (line.matches("\\[.*\\]")) {
                            String projectName = line.replace("[", "").replace("]", "");
                            Project project = new Project();
                            project.setName(projectName);

                            projectRepository.save(project);

                            while (!(line = reader.readLine()).equals("[END]")) {
                                Script script = new Script();
                                script.setPath(line);
                                script.setRunning(false);
                                script.setExecutionStarted(0);

                                script.setProject(project);

                                scriptRepository.save(script);
                            }

                        }
                    }
                }
            }

            projectRepository.flush();
            scriptRepository.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
            logger.error("An error occurred while loading configuration file");
        }
    }

}
