/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.publishable;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class HistoryEvent {

    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("old")
    private PublishableBean _old;
    @JsonProperty("new")
    private PublishableBean _new;

    public String getUser() {
        return _user;
    }

    public Date getDate() {
        return _date;
    }

    public PublishableBean getOld() {
        return _old;
    }

    public PublishableBean getNew() {
        return _new;
    }
}
