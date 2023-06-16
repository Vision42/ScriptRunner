package com.limmerlaboratories.scriptrunner.model.json.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Asset(String browser_download_url) { }
