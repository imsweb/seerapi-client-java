/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"key", "value"})
public class StagingKeyValue {

    private String _key;
    private String _value;

    public StagingKeyValue() {
    }

    public StagingKeyValue(String key, String value) {
        _key = key;
        _value = value;
    }

    @JsonProperty("key")
    public String getKey() {
        return _key;
    }

    public void setKey(String key) {
        _key = key;
    }

    @JsonProperty("value")
    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value;
    }
}
