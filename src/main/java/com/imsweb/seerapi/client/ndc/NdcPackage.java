/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"package", "description"})
public class NdcPackage {

    @JsonProperty("package")
    private String _code;
    @JsonProperty("description")
    private String _description;
    @JsonProperty("start_marketing_date")
    private String _startMarketingDate;
    @JsonProperty("end_marketing_date")
    private String _endMarketingDate;

    public String getCode() {
        return _code;
    }

    public String getDescription() {
        return _description;
    }

    public String getStartMarketingDate() {
        return _startMarketingDate;
    }

    public String getEndMarketingDate() {
        return _endMarketingDate;
    }

}
