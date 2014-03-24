/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class DiseaseHistoryEvent {

    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("old")
    private Disease _old;
    @JsonProperty("new")
    private Disease _new;

    public String getUser() {
        return _user;
    }

    public Date getDate() {
        return _date;
    }

    public Disease getOld() {
        return _old;
    }

    public Disease getNew() {
        return _new;
    }
}
