/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"ndc", "type_name", "proprietary_name", "proprietary_name_suffix", "non_proprietary_name", "dosage_form_name", "route_name",
        "start_marketing_date", "end_marketing_date", "marketing_category_name", "application_number", "labeler_name", "substances", "pharm_class",
        "dea_schedule", "packages", "date_added", "date_modified", "date_removed"})
public class NdcProduct {

    @JsonProperty("ndc")
    private String _ndc;
    @JsonProperty("type_name")
    private String _typeName;
    @JsonProperty("proprietary_name")
    private String _proprietaryName;
    @JsonProperty("proprietary_name_suffix")
    private String _proprietaryNameSuffix;
    @JsonProperty("non_proprietary_name")
    private List<String> _nonProprietaryName;
    @JsonProperty("dosage_form_name")
    private String _dosageFormName;
    @JsonProperty("route_name")
    private List<String> _routeName;
    @JsonProperty("start_marketing_date")
    private String _startMarketingDate;
    @JsonProperty("end_marketing_date")
    private String _endMarketingDate;
    @JsonProperty("marketing_category_name")
    private String _marketingCategoryName;
    @JsonProperty("application_number")
    private String _applicationNumber;
    @JsonProperty("labeler_name")
    private String _labelerName;
    @JsonProperty("dea_schedule")
    private String _deaSchedule;
    @JsonProperty("substances")
    private List<NdcSubstance> _substances;
    @JsonProperty("pharm_class")
    private List<String> _pharmClass;
    @JsonProperty("packages")
    private List<NdcPackage> _packages;
    @JsonProperty("date_added")
    private Date _dateAdded;
    @JsonProperty("date_modified")
    private Date _dateModified;
    @JsonProperty("date_removed")
    private Date _dateRemoved;

    public String getNdc() {
        return _ndc;
    }

    public String getTypeName() {
        return _typeName;
    }

    public String getProprietaryName() {
        return _proprietaryName;
    }

    public String getProprietaryNameSuffix() {
        return _proprietaryNameSuffix;
    }

    public List<String> getNonProprietaryName() {
        return _nonProprietaryName;
    }

    public String getDosageFormName() {
        return _dosageFormName;
    }

    public List<String> getRouteName() {
        return _routeName;
    }

    public String getStartMarketingDate() {
        return _startMarketingDate;
    }

    public String getEndMarketingDate() {
        return _endMarketingDate;
    }

    public String getMarketingCategoryName() {
        return _marketingCategoryName;
    }

    public String getApplicationNumber() {
        return _applicationNumber;
    }

    public String getLabelerName() {
        return _labelerName;
    }

    public String getDeaSchedule() {
        return _deaSchedule;
    }

    public List<NdcSubstance> getSubstances() {
        return _substances;
    }

    public List<String> getPharmClass() {
        return _pharmClass;
    }

    public List<NdcPackage> getPackages() {
        return _packages;
    }

    public Date getDateAdded() {
        return _dateAdded;
    }

    public Date getDateModified() {
        return _dateModified;
    }

    public Date getDateRemoved() {
        return _dateRemoved;
    }

}
