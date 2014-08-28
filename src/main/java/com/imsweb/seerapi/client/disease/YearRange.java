/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import org.codehaus.jackson.annotate.JsonProperty;

public class YearRange {

    @JsonProperty("start")
    protected Integer _startYear;
    @JsonProperty("end")
    protected Integer _endYear;

    public YearRange() {
    }

    public YearRange(Integer startYear, Integer endYear) {
        _startYear = startYear;
        _endYear = endYear;
    }

    public Integer getStartYear() {
        return _startYear;
    }

    public Integer getEndYear() {
        return _endYear;
    }
}
