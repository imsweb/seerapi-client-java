/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaaccrSubField {

    @JsonProperty("item")
    protected Integer _item;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("start_col")
    protected Integer _start;
    @JsonProperty("end_col")
    protected Integer _end;
    @JsonProperty("alignment")
    protected String _align;
    @JsonProperty("padding_char")
    protected String _padChar;

    public Integer getItem() {
        return _item;
    }

    public String getName() {
        return _name;
    }

    public Integer getStart() {
        return _start;
    }

    public Integer getEnd() {
        return _end;
    }

    public String getAlign() {
        return _align;
    }

    public String getPadChar() {
        return _padChar;
    }
}
