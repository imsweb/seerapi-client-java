package com.imsweb.seerapi.client.rx;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.Publishable;

public class Rx extends Publishable {

    // values for the type
    public enum Type {
        DRUG,
        REGIMEN
    }

    public enum DoNoCodeValue {
        YES,
        NO,
        SEE_REMARKS
    }

    @JsonProperty("history")
    protected List<RxHistoryEvent> _history;

    // RX shared properties
    @JsonProperty("type")
    protected Type _type;
    @JsonProperty("alternate_name")
    protected List<String> _alternateName;
    @JsonProperty("primary_site")
    protected List<String> _primarySite;
    @JsonProperty("histology")
    protected String _histology;
    @JsonProperty("remarks")
    protected String _remarks;
    @JsonProperty("evs_id")
    protected String _evsId;

    // drug only
    @JsonProperty("abbreviation")
    protected List<String> _abbreviation;
    @JsonProperty("category")
    protected List<String> _category;
    @JsonProperty("subcategory")
    protected List<String> _subcategory;
    @JsonProperty("nsc_number")
    protected List<String> _nscNumber;
    @JsonProperty("do_not_code")
    protected DoNoCodeValue _doNotCode;

    // regimen only
    @JsonProperty("drugs")
    protected List<String> _drugs;
    @JsonProperty("radiation")
    protected String _radiation;

    /**
     * Default constructor
     */
    public Rx() {
    }

    public List<RxHistoryEvent> getHistory() {
        return _history;
    }

    public Type getType() {
        return _type;
    }

    public List<String> getAlternateName() {
        return _alternateName;
    }

    public List<String> getPrimarySite() {
        return _primarySite;
    }

    public String getHistology() {
        return _histology;
    }

    public String getRemarks() {
        return _remarks;
    }

    public String getEvsId() {
        return _evsId;
    }

    public List<String> getAbbreviation() {
        return _abbreviation;
    }

    public List<String> getCategory() {
        return _category;
    }

    public List<String> getSubcategory() {
        return _subcategory;
    }

    public List<String> getNscNumber() {
        return _nscNumber;
    }

    public DoNoCodeValue getDoNotCode() {
        return _doNotCode;
    }

    public List<String> getDrugs() {
        return _drugs;
    }

    public String getRadiation() {
        return _radiation;
    }

    public void setHistory(List<RxHistoryEvent> history) {
        _history = history;
    }

    public void setType(Type type) {
        _type = type;
    }

    public void setAlternateName(List<String> alternateName) {
        _alternateName = alternateName;
    }

    public void setPrimarySite(List<String> primarySite) {
        _primarySite = primarySite;
    }

    public void setHistology(String histology) {
        _histology = histology;
    }

    public void setRemarks(String remarks) {
        _remarks = remarks;
    }

    public void setEvsId(String evsId) {
        _evsId = evsId;
    }

    public void setAbbreviation(List<String> abbreviation) {
        _abbreviation = abbreviation;
    }

    public void setCategory(List<String> category) {
        _category = category;
    }

    public void setSubcategory(List<String> subcategory) {
        _subcategory = subcategory;
    }

    public void setNscNumber(List<String> nscNumber) {
        _nscNumber = nscNumber;
    }

    public void setDoNotCode(DoNoCodeValue doNotCode) {
        _doNotCode = doNotCode;
    }

    public void setDrugs(List<String> drugs) {
        _drugs = drugs;
    }

    public void setRadiation(String radiation) {
        _radiation = radiation;
    }
}
