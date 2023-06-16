package com.limmerlaboratories.scriptrunner.model.json.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Release(String tag_name, Asset[] assets) { }