/*
 * Copyright (C) 2018 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"seer_rx_id", "categories", "major_drug_class", "minor_drug_class", "orphan_drug", "exclusive_oncologic_agent", "date_modified"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class NdcSeerInfo {

    @JsonProperty("seer_rx_id")
    private String seerRxId;
    @JsonProperty("categories")
    private List<Category> categories;
    @JsonProperty("major_drug_class")
    private String majorDrugClass;
    @JsonProperty("minor_drug_class")
    private String minorDrugClass;
    @JsonProperty("orphan_drug")
    private Boolean orphanDrug;
    @JsonProperty("exclusive_oncologic_agent")
    private Boolean exclusiveAgent;
    @JsonProperty("date_modified")
    private Date dateModified;

    public String getSeerRxId() {
        return seerRxId;
    }

    public void setSeerRxId(String seerRxId) {
        this.seerRxId = seerRxId;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getMajorDrugClass() {
        return majorDrugClass;
    }

    public void setMajorDrugClass(String majorDrugClass) {
        this.majorDrugClass = majorDrugClass;
    }

    public String getMinorDrugClass() {
        return minorDrugClass;
    }

    public void setMinorDrugClass(String minorDrugClass) {
        this.minorDrugClass = minorDrugClass;
    }

    public Boolean getOrphanDrug() {
        return orphanDrug;
    }

    public void setOrphanDrug(Boolean orphanDrug) {
        this.orphanDrug = orphanDrug;
    }

    public Boolean getExclusiveAgent() {
        return exclusiveAgent;
    }

    public void setExclusiveAgent(Boolean exclusiveAgent) {
        this.exclusiveAgent = exclusiveAgent;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
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
