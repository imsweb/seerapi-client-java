/*
 * Copyright (C) 2018 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"seer_rx_id", "categories", "subcategory", "major_drug_class", "minor_drug_class", "orphan_drug", "exclusive_oncologic_agent", "date_modified"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class NdcSeerInfo {

    @JsonProperty("seer_rx_id")
    private String _seerRxId;
    @JsonProperty("categories")
    private List<Category> _categories;
    @JsonProperty("major_drug_class")
    private String _majorDrugClass;
    @JsonProperty("minor_drug_class")
    private String _minorDrugClass;
    @JsonProperty("orphan_drug")
    private Boolean _orphanDrug;
    @JsonProperty("exclusive_oncologic_agent")
    private Boolean _exclusiveAgent;
    @JsonProperty("date_modified")
    private Date _dateModified;

    public String getSeerRxId() {
        return _seerRxId;
    }

    public List<Category> getCategories() {
        return _categories;
    }

    public String getMajorDrugClass() {
        return _majorDrugClass;
    }

    public String getMinorDrugClass() {
        return _minorDrugClass;
    }

    public Boolean getOrphanDrug() {
        return _orphanDrug;
    }

    public Boolean getExclusiveAgent() {
        return _exclusiveAgent;
    }

    public Date getDateModified() {
        return _dateModified;
    }

    // drug categories
    public enum Category {
        HORMONAL_THERAPY,
        ANCILLARY,
        CHEMOTHERAPY,
        IMMUNOTHERAPY,
        RADIOPHARMACEUTICAL
    }
}
