package com.limmerlaboratories.scriptrunner.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Objects;

@Service
@Log4j2
public class UpdateService {
    private String version;
    private String latestVersion;

    private FTPClient ftpClient;
    private String domain;
    private int port;
    private String user;
    private String password;

    public UpdateService(Environment environment) {
        ftpClient = new FTPClient();
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        domain = environment.getProperty("scriptrunner.update.domain");
        port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("scriptrunner.update.port")));
        user = environment.getProperty("scriptrunner.update.username");
        password = environment.getProperty("scriptrunner.update.password");

        version = environment.getProperty("app.version");
    }

    public boolean checkForUpdate() {
        boolean ret = false;

        try {
            connect();

            FTPFile[] directories = ftpClient.listDirectories();
            latestVersion = directories[directories.length - 1].getName();
            if (!latestVersion.equals(version)) {
                ret = true;
            }

            disconnect();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return ret;
    }

    public boolean updateToLatestVersion() {
        try {
            String pathToCurrentJar = UpdateService.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            pathToCurrentJar = pathToCurrentJar.replace("file:", "");
            pathToCurrentJar = pathToCurrentJar.split("!")[0];

            connect();

            File jarFile = new File(pathToCurrentJar + "new");
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(jarFile));
            boolean status = ftpClient.retrieveFile(latestVersion + "/scriptrunner.jar", outputStream);
            outputStream.close();

            disconnect();
            return status;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    private void connect() throws IOException {
        ftpClient.connect(domain, port);
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.login(user, password);
            return;
        }

        ftpClient.disconnect();
        log.error("Could not connect to update server");
    }

    private void disconnect() throws IOException {
        ftpClient.disconnect();
    }
}
