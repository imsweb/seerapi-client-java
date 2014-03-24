/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DiseaseChangelog implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    protected String _displayId;
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

    public String getId() {
        return _displayId;
    }

    public List<DiseaseChangelogEntry> getAdds() {
        return _adds;
    }

    public List<DiseaseChangelogEntry> getDeletes() {
        return _deletes;
    }

    public List<DiseaseChangelogEntry> getMods() {
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
}
