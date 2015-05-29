/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GlossaryChangelog {

    @JsonProperty("adds")
    private List<GlossaryChangelogEntry> _adds;
    @JsonProperty("deletes")
    private List<GlossaryChangelogEntry> _deletes;
    @JsonProperty("mods")
    private List<GlossaryChangelogEntry> _mods;
    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("version")
    private String _version;
    @JsonProperty("description")
    private String _description;

    public List<GlossaryChangelogEntry> getAdds() {
        return _adds;
    }

    public List<GlossaryChangelogEntry> getDeletes() {
        return _deletes;
    }

    public List<GlossaryChangelogEntry> getMods() {
        return _mods;
    }

    public String getUser() {
        return _user;
    }

    public Date getDate() {
        return _date;
    }

    public String getVersion() {
        return _version;
    }

    public String getDescription() {
        return _description;
    }

    public void setAdds(List<GlossaryChangelogEntry> adds) {
        _adds = adds;
    }

    public void setDeletes(List<GlossaryChangelogEntry> deletes) {
        _deletes = deletes;
    }

    public void setMods(List<GlossaryChangelogEntry> mods) {
        _mods = mods;
    }

    public void setUser(String user) {
        _user = user;
    }

    public void setDate(Date date) {
        _date = date;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public void setDescription(String description) {
        _description = description;
    }
}
