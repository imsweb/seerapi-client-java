package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateRange {

    @JsonProperty("start")
    protected String _startDate;
    @JsonProperty("end")
    protected String _endDate;

    public String getStartDate() {
        return _startDate;
    }

    public void setStartDate(String startDate) {
        _startDate = startDate;
    }

    public String getEndDate() {
        return _endDate;
    }

    public void setEndDate(String endDate) {
        _endDate = endDate;
    }
}
