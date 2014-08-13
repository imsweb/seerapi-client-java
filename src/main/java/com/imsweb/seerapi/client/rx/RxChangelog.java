/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class RxChangelog {

    @JsonProperty("id")
    protected String _displayId;
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

}
