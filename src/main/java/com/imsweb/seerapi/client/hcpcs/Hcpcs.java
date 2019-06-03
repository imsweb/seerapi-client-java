/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.hcpcs;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"hcpcs_code", "generic_name", "brand_names", "strength", "fda_approval_year", "fda_discontinuation_year", "cms_approval_date",
        "cms_discontinuation_date", "categories", "major_drug_class", "minor_drug_class", "oral", "date_added", "date_modified", "score"})
public class Hcpcs {

    @JsonProperty("hcpcs_code")
    private String _hcpcsCode;
    @JsonProperty("generic_name")
    private String _genericName;
    @JsonProperty("brand_names")
    private List<String> _brandNames;
    @JsonProperty("strength")
    private String _strength;
    @JsonProperty("fda_approval_year")
    private String _fdaApprovalYear;
    @JsonProperty("fda_discontinuation_year")
    private String _fdaDiscontinuationYear;
    @JsonProperty("cms_approval_date")
    private String _cmsApprovalDate;
    @JsonProperty("cms_discontinuation_date")
    private String _cmsDiscontinuationDate;
    @JsonProperty("categories")
    private List<Category> _categories;
    @JsonProperty("major_drug_class")
    private String _majorDrugClass;
    @JsonProperty("minor_drug_class")
    private String _minorDrugClass;
    @JsonProperty("oral")
    private Boolean _oral;
    @JsonProperty("date_added")
    private Date _dateAdded;
    @JsonProperty("date_modified")
    private Date _dateModified;
    @JsonProperty("score")
    private Double score;

    public String getHcpcsCode() {
        return _hcpcsCode;
    }

    public String getGenericName() {
        return _genericName;
    }

    public List<String> getBrandNames() {
        return _brandNames;
    }

    public String getStrength() {
        return _strength;
    }

    public String getFdaApprovalYear() {
        return _fdaApprovalYear;
    }

    public String getFdaDiscontinuationYear() {
        return _fdaDiscontinuationYear;
    }

    public String getCmsApprovalDate() {
        return _cmsApprovalDate;
    }

    public String getCmsDiscontinuationDate() {
        return _cmsDiscontinuationDate;
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

    public Boolean getOral() {
        return _oral;
    }

    public Date getDateAdded() {
        return _dateAdded;
    }

    public Date getDateModified() {
        return _dateModified;
    }

    public Double getScore() {
        return score;
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
