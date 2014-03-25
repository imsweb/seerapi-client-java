/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

public class GlossaryHistoryEvent {

    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("old")
    private Glossary _old;
    @JsonProperty("new")
    private Glossary _new;

    public String getUser() {
        return _user;
    }

    public Date getDate() {
        return _date;
    }

    public Glossary getOld() {
        return _old;
    }

    public Glossary getNew() {
        return _new;
    }
}
