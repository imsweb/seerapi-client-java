/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"from", "to"})
public class StagingKeyMapping {

    String _from;
    String _to;

    public StagingKeyMapping() {
    }

    public StagingKeyMapping(String from, String to) {
        _from = from;
        _to = to;
    }

    @JsonProperty("from")
    public String getFrom() {
        return _from;
    }

    public void setFrom(String from) {
        _from = from;
    }

    @JsonProperty("to")
    public String getTo() {
        return _to;
    }

    public void setTo(String to) {
        _to = to;
    }
}
