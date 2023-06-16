package com.limmerlaboratories.scriptrunner.service;

import com.limmerlaboratories.scriptrunner.model.json.github.Release;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Service
@Log4j2
public class UpdateService {
    private String version;
    private String latestVersion;
    private String updateUri;
    private URL versionDownloadUrl;

    public UpdateService(Environment environment) {
        version = environment.getProperty("app.version");
        updateUri = environment.getProperty("app.update.uri");
    }

    public boolean checkForUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        Release[] results = restTemplate.getForObject(updateUri, Release[].class);

        try {
            if (results != null && results.length > 0) {
                Release latestRelease = results[0];
                if (latestRelease.tag_name().equals(version)) {
                    return false;
                }

                latestVersion = latestRelease.tag_name();
                versionDownloadUrl = new URL(latestRelease.assets()[0].browser_download_url());
                return true;
            }
        } catch (MalformedURLException ex) {
            log.error(ex);
        }

        return false;
    }

    public boolean updateToLatestVersion() {
        try {
            String pathToCurrentJar = UpdateService.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            pathToCurrentJar = pathToCurrentJar.replace("file:", "");
            pathToCurrentJar = pathToCurrentJar.split("!")[0];

            ReadableByteChannel readableByteChannel = Channels.newChannel(versionDownloadUrl.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(pathToCurrentJar);
            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            return true;
        } catch (IOException ex) {
            log.error(ex);
        }

        return false;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public String getCurrentVersion() {
        return version;
    }
}
