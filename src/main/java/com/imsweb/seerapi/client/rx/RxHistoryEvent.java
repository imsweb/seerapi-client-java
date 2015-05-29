/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RxHistoryEvent {

    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("old")
    private Rx _old;
    @JsonProperty("new")
    private Rx _new;

    public String getUser() {
        return _user;
    }

    public Date getDate() {
        return _date;
    }

    public Rx getOld() {
        return _old;
    }

    public Rx getNew() {
        return _new;
    }

    public void setUser(String user) {
        _user = user;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public void setOld(Rx old) {
        _old = old;
    }

    public void setNew(Rx aNew) {
        _new = aNew;
    }
}
