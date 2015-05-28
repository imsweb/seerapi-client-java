/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrimarySite {

    @JsonProperty("value")
    private String _value;
    @JsonProperty("label")
    private String _label;

    public String getValue() {
        return _value;
    }

    public String getLabel() {
        return _label;
    }
}
