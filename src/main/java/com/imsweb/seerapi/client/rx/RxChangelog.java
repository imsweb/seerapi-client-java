/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RxChangelog {

    @JsonProperty("adds")
    private List<RxChangelogEntry> _adds;
    @JsonProperty("deletes")
    private List<RxChangelogEntry> _deletes;
    @JsonProperty("mods")
    private List<RxChangelogEntry> _mods;
    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("version")
    private String _version;
    @JsonProperty("description")
    private String _description;

    public List<RxChangelogEntry> getAdds() {
        return _adds;
    }

    public void setAdds(List<RxChangelogEntry> adds) {
        _adds = adds;
    }

    public List<RxChangelogEntry> getDeletes() {
        return _deletes;
    }

    public void setDeletes(List<RxChangelogEntry> deletes) {
        _deletes = deletes;
    }

    public List<RxChangelogEntry> getMods() {
        return _mods;
    }

    public void setMods(List<RxChangelogEntry> mods) {
        _mods = mods;
    }

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
