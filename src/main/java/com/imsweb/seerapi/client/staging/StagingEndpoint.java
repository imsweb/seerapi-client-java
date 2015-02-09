/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import org.codehaus.jackson.annotate.JsonProperty;

public class StagingEndpoint {

    private EndpointType _type;
    private String _value;
    private String _resultKey;

    enum EndpointType {
        JUMP,
        VALUE,
        MATCH,
        STOP,
        ERROR
    }

    public StagingEndpoint() {
    }

    public StagingEndpoint(EndpointType type, String value) {
        _type = type;
        _value = value;
    }

    @JsonProperty("type")
    public EndpointType getType() {
        return _type;
    }

    public void setType(EndpointType type) {
        _type = type;
    }

    @JsonProperty("value")
    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value;
    }

    @JsonProperty("result_key")
    public String getResultKey() {
        return _resultKey;
    }

    public void setResultKey(String resultKey) {
        _resultKey = resultKey;
    }

}
