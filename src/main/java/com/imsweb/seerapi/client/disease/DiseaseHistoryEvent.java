/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public void setUser(String user) {
        _user = user;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public void setOld(Disease old) {
        _old = old;
    }

    public void setNew(Disease aNew) {
        _new = aNew;
    }
}
