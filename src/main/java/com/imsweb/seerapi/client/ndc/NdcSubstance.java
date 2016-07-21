/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "strength", "unit"})
public class NdcSubstance {

    @JsonProperty("name")
    private String _name;
    @JsonProperty("strength")
    private String _strengthNumber;
    @JsonProperty("unit")
    private String _strengthUnit;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getStrengthNumber() {
        return _strengthNumber;
    }

    public void setStrengthNumber(String strengthNumber) {
        _strengthNumber = strengthNumber;
    }

    public String getStrengthUnit() {
        return _strengthUnit;
    }

    public void setStrengthUnit(String strengthUnit) {
        _strengthUnit = strengthUnit;
    }
}
