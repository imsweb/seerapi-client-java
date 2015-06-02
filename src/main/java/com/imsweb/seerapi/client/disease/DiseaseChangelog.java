/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiseaseChangelog {

    @JsonProperty("adds")
    private List<DiseaseChangelogEntry> _adds;
    @JsonProperty("deletes")
    private List<DiseaseChangelogEntry> _deletes;
    @JsonProperty("mods")
    private List<DiseaseChangelogEntry> _mods;
    @JsonProperty("user")
    private String _user;
    @JsonProperty("date")
    private Date _date;
    @JsonProperty("version")
    private String _version;
    @JsonProperty("description")
    private String _description;

    public List<DiseaseChangelogEntry> getAdds() {
        return _adds;
    }

    public void setAdds(List<DiseaseChangelogEntry> adds) {
        _adds = adds;
    }

    public List<DiseaseChangelogEntry> getDeletes() {
        return _deletes;
    }

    public void setDeletes(List<DiseaseChangelogEntry> deletes) {
        _deletes = deletes;
    }

    public List<DiseaseChangelogEntry> getMods() {
        return _mods;
    }

    public void setMods(List<DiseaseChangelogEntry> mods) {
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
