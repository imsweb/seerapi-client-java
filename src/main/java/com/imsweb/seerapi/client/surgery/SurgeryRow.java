/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SurgeryRow {

    @JsonProperty("code")
    protected String _code;
    @JsonProperty("description")
    protected String _description;
    @JsonProperty("level")
    protected Integer _level;
    @JsonProperty("line_break")
    protected Boolean _lineBreak;

    public String getCode() {
        return _code;
    }

    public String getDescription() {
        return _description;
    }

    public Integer getLevel() {
        return _level;
    }

    public Boolean getLineBreak() {
        return _lineBreak;
    }
}

