/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import org.codehaus.jackson.annotate.JsonProperty;

public class NaaccrFieldName {

    @JsonProperty("item")
    protected Integer _item;
    @JsonProperty("name")
    protected String _name;

    public Integer getItem() {
        return _item;
    }

    public String getName() {
        return _name;
    }
}
