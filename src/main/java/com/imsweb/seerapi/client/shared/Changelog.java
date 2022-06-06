/*
 * Copyright (C) 2022 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.shared;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Changelog {

    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("version")
    private String _version;
    @JsonProperty("description")
    private String _description;

    public String getUser() {
        return _user;
    }

    public void setUser(String user) {
        _user = user;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
