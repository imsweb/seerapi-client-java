package com.imsweb.seerapi.client.mph;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MphInput {

    @JsonProperty("primary_site")
    private String _primarySite;
    @JsonProperty("histology_icd_o3")
    private String _histologyIcdO3;
    @JsonProperty("histology_icd_o2")
    private String _histologyIcdO2;
    @JsonProperty("behavior_icd_o3")
    private String _behaviorIcdO3;
    @JsonProperty("behavior_icd_o2")
    private String _behaviorIcdO2;
    @JsonProperty("laterality")
    private String _laterality;
    @JsonProperty("date_of_diagnosis_year")
    private String _dateOfDiagnosisYear;
    @JsonProperty("date_of_diagnosis_month")
    private String _dateOfDiagnosisMonth;
    @JsonProperty("date_of_diagnosis_day")
    private String _dateOfDiagnosisDay;

    public String getPrimarySite() {
        return _primarySite;
    }

    public void setPrimarySite(String primarySite) {
        _primarySite = primarySite;
    }

    public String getHistologyIcdO3() {
        return _histologyIcdO3;
    }

    public void setHistologyIcdO3(String histologyIcdO3) {
        _histologyIcdO3 = histologyIcdO3;
    }

    public String getHistologyIcdO2() {
        return _histologyIcdO2;
    }

    public void setHistologyIcdO2(String histologyIcdO2) {
        _histologyIcdO2 = histologyIcdO2;
    }

    public String getBehaviorIcdO3() {
        return _behaviorIcdO3;
    }

    public void setBehaviorIcdO3(String behaviorIcdO3) {
        _behaviorIcdO3 = behaviorIcdO3;
    }

    public String getBehaviorIcdO2() {
        return _behaviorIcdO2;
    }

    public void setBehaviorIcdO2(String behaviorIcdO2) {
        _behaviorIcdO2 = behaviorIcdO2;
    }

    public String getLaterality() {
        return _laterality;
    }

    public void setLaterality(String laterality) {
        _laterality = laterality;
    }

    public String getDateOfDiagnosisYear() {
        return _dateOfDiagnosisYear;
    }

    public void setDateOfDiagnosisYear(String dateOfDiagnosisYear) {
        _dateOfDiagnosisYear = dateOfDiagnosisYear;
    }

    public String getDateOfDiagnosisMonth() {
        return _dateOfDiagnosisMonth;
    }

    public void setDateOfDiagnosisMonth(String dateOfDiagnosisMonth) {
        _dateOfDiagnosisMonth = dateOfDiagnosisMonth;
    }

    public String getDateOfDiagnosisDay() {
        return _dateOfDiagnosisDay;
    }

    public void setDateOfDiagnosisDay(String dateOfDiagnosisDay) {
        _dateOfDiagnosisDay = dateOfDiagnosisDay;
    }

}
