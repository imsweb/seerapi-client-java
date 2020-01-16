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
    private String ndc;
    @JsonProperty("type_name")
    private String typeName;
    @JsonProperty("proprietary_name")
    private String proprietaryName;
    @JsonProperty("proprietary_name_suffix")
    private String proprietaryNameSuffix;
    @JsonProperty("non_proprietary_name")
    private List<String> nonProprietaryName;
    @JsonProperty("dosage_form_name")
    private String dosageFormName;
    @JsonProperty("route_name")
    private List<String> routeName;
    @JsonProperty("start_marketing_date")
    private String startMarketingDate;
    @JsonProperty("end_marketing_date")
    private String endMarketingDate;
    @JsonProperty("marketing_category_name")
    private String marketingCategoryName;
    @JsonProperty("application_number")
    private String applicationNumber;
    @JsonProperty("labeler_name")
    private String labelerName;
    @JsonProperty("dea_schedule")
    private String deaSchedule;
    @JsonProperty("substances")
    private List<NdcSubstance> substances;
    @JsonProperty("pharm_class")
    private List<String> pharmClass;
    @JsonProperty("packages")
    private List<NdcPackage> packages;
    @JsonProperty("date_added")
    private Date dateAdded;
    @JsonProperty("date_modified")
    private Date dateModified;
    @JsonProperty("date_removed")
    private Date dateRemoved;
    @JsonProperty("seer")
    private NdcSeerInfo seerInfo;
    @JsonProperty("score")
    private Double score;

    public String getNdc() {
        return ndc;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getProprietaryName() {
        return proprietaryName;
    }

    public String getProprietaryNameSuffix() {
        return proprietaryNameSuffix;
    }

    public List<String> getNonProprietaryName() {
        return nonProprietaryName;
    }

    public String getDosageFormName() {
        return dosageFormName;
    }

    public List<String> getRouteName() {
        return routeName;
    }

    public String getStartMarketingDate() {
        return startMarketingDate;
    }

    public String getEndMarketingDate() {
        return endMarketingDate;
    }

    public String getMarketingCategoryName() {
        return marketingCategoryName;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public String getLabelerName() {
        return labelerName;
    }

    public String getDeaSchedule() {
        return deaSchedule;
    }

    public List<NdcSubstance> getSubstances() {
        return substances;
    }

    public List<String> getPharmClass() {
        return pharmClass;
    }

    public List<NdcPackage> getPackages() {
        return packages;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public Date getDateRemoved() {
        return dateRemoved;
    }

    public NdcSeerInfo getSeerInfo() {
        return seerInfo;
    }

    public Double getScore() {
        return score;
    }
}
