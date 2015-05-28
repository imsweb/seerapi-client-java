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
}
