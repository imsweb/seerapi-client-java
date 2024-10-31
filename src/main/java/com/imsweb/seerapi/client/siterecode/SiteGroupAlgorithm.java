/*
 * Copyright (C) 2024 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteGroupAlgorithm {

    private String id;
    private String name;
    private String version;
    @JsonProperty("required_input")
    private List<String> requiredInput;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<String> getRequiredInput() {
        return requiredInput;
    }

    public void setRequiredInput(List<String> requiredInput) {
        this.requiredInput = requiredInput;
    }

}
