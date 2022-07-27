/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.hcpcs;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"hcpcs_code", "generic_name", "description", "brand_names", "strength", "fda_approval_year", "fda_discontinuation_year", "cms_approval_date",
        "cms_discontinuation_date", "categories", "major_drug_class", "minor_drug_class", "oral", "date_added", "date_modified", "score"})
public class Hcpcs {

    @JsonProperty("hcpcs_code")
    private String hcpcsCode;
    @JsonProperty("generic_name")
    private String genericName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("brand_names")
    private List<String> brandNames;
    @JsonProperty("strength")
    private String strength;
    @JsonProperty("fda_approval_year")
    private String fdaApprovalYear;
    @JsonProperty("fda_discontinuation_year")
    private String fdaDiscontinuationYear;
    @JsonProperty("cms_approval_date")
    private String cmsApprovalDate;
    @JsonProperty("cms_discontinuation_date")
    private String cmsDiscontinuationDate;
    @JsonProperty("categories")
    private List<Category> categories;
    @JsonProperty("major_drug_class")
    private String majorDrugClass;
    @JsonProperty("minor_drug_class")
    private String minorDrugClass;
    @JsonProperty("oral")
    private Boolean oral;
    @JsonProperty("date_added")
    private Date dateAdded;
    @JsonProperty("date_modified")
    private Date dateModified;
    @JsonProperty("score")
    private Double score;

    public String getHcpcsCode() {
        return hcpcsCode;
    }

    public void setHcpcsCode(String hcpcsCode) {
        this.hcpcsCode = hcpcsCode;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(List<String> brandNames) {
        this.brandNames = brandNames;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getFdaApprovalYear() {
        return fdaApprovalYear;
    }

    public void setFdaApprovalYear(String fdaApprovalYear) {
        this.fdaApprovalYear = fdaApprovalYear;
    }

    public String getFdaDiscontinuationYear() {
        return fdaDiscontinuationYear;
    }

    public void setFdaDiscontinuationYear(String fdaDiscontinuationYear) {
        this.fdaDiscontinuationYear = fdaDiscontinuationYear;
    }

    public String getCmsApprovalDate() {
        return cmsApprovalDate;
    }

    public void setCmsApprovalDate(String cmsApprovalDate) {
        this.cmsApprovalDate = cmsApprovalDate;
    }

    public String getCmsDiscontinuationDate() {
        return cmsDiscontinuationDate;
    }

    public void setCmsDiscontinuationDate(String cmsDiscontinuationDate) {
        this.cmsDiscontinuationDate = cmsDiscontinuationDate;
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

    public Boolean getOral() {
        return oral;
    }

    public void setOral(Boolean oral) {
        this.oral = oral;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    // drug categories
    public enum Category {
        HORMONAL_THERAPY,
        ANCILLARY,
        CHEMOTHERAPY,
        IMMUNOTHERAPY,
        RADIOPHARMACEUTICAL;
    }
}
