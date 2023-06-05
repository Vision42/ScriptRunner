package com.limmerlaboratories.scriptrunner;

import com.limmerlaboratories.scriptrunner.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScriptRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScriptRunnerApplication.class, args);
	}

}
