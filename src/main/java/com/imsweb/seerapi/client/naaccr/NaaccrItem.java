package com.imsweb.seerapi.client.naaccr;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaaccrItem {

    private NaaccrVersion version;
    private String itemNumber;
    private String itemName;
    private String itemLength;
    private String yearImplemented;
    private String yearRetired;
    private String versionImplemented;
    private String versionRetired;
    private String xmlNaaccrId;
    private String xmlParentId;
    private List<String> recordTypes;
    private String section;
    private String sourceOfStandard;
    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private String description;
    private String rationale;
    private String generalNotes;
    private String clarification;
    private String npcrCollect;
    private String cocCollect;
    private String seerCollect;
    private String cccrCollect;
    private List<String> alternateNames;
    private String format;
    private String codeNote;
    private String codeDescription;
    private String itemDataType;
    private String allowableValues;
    private List<AllowedCode> allowedCodes;

    public NaaccrVersion getVersion() {
        return version;
    }

    public void setVersion(NaaccrVersion version) {
        this.version = version;
    }

    @JsonProperty("item_number")
    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    @JsonProperty("item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("item_length")
    public String getItemLength() {
        return itemLength;
    }

    public void setItemLength(String itemLength) {
        this.itemLength = itemLength;
    }

    @JsonProperty("year_implemented")
    public String getYearImplemented() {
        return yearImplemented;
    }

    public void setYearImplemented(String yearImplemented) {
        this.yearImplemented = yearImplemented;
    }

    @JsonProperty("year_retired")
    public String getYearRetired() {
        return yearRetired;
    }

    public void setYearRetired(String yearRetired) {
        this.yearRetired = yearRetired;
    }

    @JsonProperty("version_implemented")
    public String getVersionImplemented() {
        return versionImplemented;
    }

    public void setVersionImplemented(String versionImplemented) {
        this.versionImplemented = versionImplemented;
    }

    @JsonProperty("version_retired")
    public String getVersionRetired() {
        return versionRetired;
    }

    public void setVersionRetired(String versionRetired) {
        this.versionRetired = versionRetired;
    }

    @JsonProperty("xml_naaccr_id")
    public String getXmlNaaccrId() {
        return xmlNaaccrId;
    }

    public void setXmlNaaccrId(String xmlNaaccrId) {
        this.xmlNaaccrId = xmlNaaccrId;
    }

    @JsonProperty("xml_parent_id")
    public String getXmlParentId() {
        return xmlParentId;
    }

    public void setXmlParentId(String xmlParentId) {
        this.xmlParentId = xmlParentId;
    }

    @JsonProperty("record_types")
    public List<String> getRecordTypes() {
        return recordTypes;
    }

    public void setRecordTypes(List<String> recordTypes) {
        this.recordTypes = recordTypes;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @JsonProperty("source_of_standard")
    public String getSourceOfStandard() {
        return sourceOfStandard;
    }

    public void setSourceOfStandard(String sourceOfStandard) {
        this.sourceOfStandard = sourceOfStandard;
    }

    @JsonProperty("date_created")
    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonProperty("date_modified")
    public OffsetDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(OffsetDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRationale() {
        return rationale;
    }

    public void setRationale(String rationale) {
        this.rationale = rationale;
    }

    @JsonProperty("general_notes")
    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public String getClarification() {
        return clarification;
    }

    public void setClarification(String clarification) {
        this.clarification = clarification;
    }

    @JsonProperty("npcr_collect")
    public String getNpcrCollect() {
        return npcrCollect;
    }

    public void setNpcrCollect(String npcrCollect) {
        this.npcrCollect = npcrCollect;
    }

    @JsonProperty("coc_collect")
    public String getCocCollect() {
        return cocCollect;
    }

    public void setCocCollect(String cocCollect) {
        this.cocCollect = cocCollect;
    }

    @JsonProperty("seer_collect")
    public String getSeerCollect() {
        return seerCollect;
    }

    public void setSeerCollect(String seerCollect) {
        this.seerCollect = seerCollect;
    }

    @JsonProperty("cccr_collect")
    public String getCccrCollect() {
        return cccrCollect;
    }

    public void setCccrCollect(String cccrCollect) {
        this.cccrCollect = cccrCollect;
    }

    @JsonProperty("alternate_names")
    public List<String> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @JsonProperty("code_note")
    public String getCodeNote() {
        return codeNote;
    }

    public void setCodeNote(String codeNote) {
        this.codeNote = codeNote;
    }

    @JsonProperty("code_description")
    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    @JsonProperty("item_data_type")
    public String getItemDataType() {
        return itemDataType;
    }

    public void setItemDataType(String itemDataType) {
        this.itemDataType = itemDataType;
    }

    @JsonProperty("allowable_values")
    public String getAllowableValues() {
        return allowableValues;
    }

    public void setAllowableValues(String allowableValues) {
        this.allowableValues = allowableValues;
    }

    @JsonProperty("allowed_codes")
    public List<AllowedCode> getAllowedCodes() {
        return allowedCodes;
    }

    public void setAllowedCodes(List<AllowedCode> allowedCodes) {
        this.allowedCodes = allowedCodes;
    }

}
