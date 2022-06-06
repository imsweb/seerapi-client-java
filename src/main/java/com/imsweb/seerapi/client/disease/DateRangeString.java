package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateRangeString extends DateRange {

    @JsonProperty("value")
    protected String _value;

    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        _value = value;
    }

}
