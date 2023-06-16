package com.limmerlaboratories.scriptrunner;

import com.limmerlaboratories.scriptrunner.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ScriptRunnerApplication {

	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(ScriptRunnerApplication.class, args);

		if (args.length >= 1 && args[0].equals("update")) {
			UpdateService updateService = new UpdateService(context.getEnvironment());
			if (!updateService.checkForUpdate()) {
				System.out.println("Nothing to update");
			}

			context.close();

			System.out.println("Updating to latest Version... " + updateService.getCurrentVersion());
			if (updateService.updateToLatestVersion()) {
				System.out.println("Updated ScriptRunner to version: " + updateService.getLatestVersion());
				System.exit(0);
			}

			System.out.println("An error occurred while updating ScriptRunner");
			System.exit(-1);
		}
	}

}
