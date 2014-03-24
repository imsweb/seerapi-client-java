/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import org.codehaus.jackson.annotate.JsonProperty;

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
