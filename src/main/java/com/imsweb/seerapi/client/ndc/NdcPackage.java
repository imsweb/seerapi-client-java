/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"package", "description"})
public class NdcPackage {

    @JsonProperty("package")
    private String code;
    @JsonProperty("description")
    private String description;
    @JsonProperty("start_marketing_date")
    private String startMarketingDate;
    @JsonProperty("end_marketing_date")
    private String endMarketingDate;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getStartMarketingDate() {
        return startMarketingDate;
    }

    public String getEndMarketingDate() {
        return endMarketingDate;
    }

}
