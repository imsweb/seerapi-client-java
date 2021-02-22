/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaaccrFieldName {

    @JsonProperty("id")
    protected String _id;
    @JsonProperty("item")
    protected Integer _item;
    @JsonProperty("name")
    protected String _name;

    public String getNaaccrId() {
        return _id;
    }

    public Integer getItemNum() {
        return _item;
    }

    public String getName() {
        return _name;
    }
}
