/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public void setUser(String user) {
        _user = user;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public Glossary getOld() {
        return _old;
    }

    public void setOld(Glossary old) {
        _old = old;
    }

    public Glossary getNew() {
        return _new;
    }

    public void setNew(Glossary aNew) {
        _new = aNew;
    }
}
