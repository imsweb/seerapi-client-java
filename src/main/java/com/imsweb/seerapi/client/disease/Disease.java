package com.imsweb.seerapi.client.disease;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.publishable.Publishable;

@SuppressWarnings("WeakerAccess")
public class Disease extends Publishable {

    @JsonProperty("history")
    private List<DiseaseHistoryEvent> _history;

    /**
     * Identifying information
     */
    @JsonProperty("icdO3_morphology")
    private String _icdO3Morphology;
    @JsonProperty("primary_site")
    private List<SiteRange> _primarySite;
    @JsonProperty("primary_site_text")
    private String _primarySiteText;
    @JsonProperty("site_category")
    private String _siteCategory;
    @JsonProperty("type")
    private Type _type;

    /**
     * Obsolete ranges
     */
    @JsonProperty("valid")
    private YearRange _valid;
    @JsonProperty("obsolete_new_code")
    private List<String> _obsoleteNewCode;

    /**
     * Shared disease properties
     */
    @JsonProperty("reportable")
    private List<YearRange> _reportable;
    @JsonProperty("abstractor_note")
    private List<YearRangeString> _abstractorNote;
    @JsonProperty("treatment")
    private List<YearRangeString> _treatment;
    @JsonProperty("genetics")
    private List<YearRangeString> _genetics;
    @JsonProperty("alternate_name")
    private List<YearRangeString> _alternateName;
    @JsonProperty("definition")
    private List<YearRangeString> _definition;
    @JsonProperty("icdO2_morphology")
    private List<String> _icdO2Morphology;
    @JsonProperty("icdO1_morphology")
    private List<String> _icdO1Morphology;
    @JsonProperty("icd_10cm_code")
    private List<DateRangeString> _icd10CmCode;
    @JsonProperty("icd_10_code")
    private List<String> _icd10Code;
    @JsonProperty("icd_9_code")
    private List<String> _icd9Code;
    @JsonProperty("signs")
    private List<YearRangeString> _signs;
    @JsonProperty("exams")
    private List<YearRangeString> _exams;
    @JsonProperty("mortality")
    private List<YearRangeString> _mortality;
    @JsonProperty("source")
    private List<DiseaseSource> _source;

    /**
     * Morphology ranges
     */
    @JsonProperty("icdO3_effective")
    private YearRange _icdO3Effective;
    @JsonProperty("icdO2_effective")
    private YearRange _icdO2Effective;
    @JsonProperty("icdO1_effective")
    private YearRange _icdO1Effective;

    /**
     * Hemato specific
     */
    @JsonProperty("missing_primary_site_message")
    private List<YearRangeString> _missingPrimarySiteMessage;
    @JsonProperty("grade")
    private List<YearRangeInteger> _grade;
    @JsonProperty("transform_to")
    private List<YearRangeString> _transformTo;
    @JsonProperty("transform_to_text")
    private List<YearRangeString> _transformToText;
    @JsonProperty("transform_from")
    private List<YearRangeString> _transformFrom;
    @JsonProperty("transform_from_text")
    private List<YearRangeString> _transformFromText;
    @JsonProperty("immunophenotype")
    private List<YearRangeString> _immunophenotype;
    @JsonProperty("diagnosis_method")
    private List<YearRangeString> _diagnosisMethod;
    @JsonProperty("module_id")
    private List<YearRangeString> _moduleId;
    @JsonProperty("same_primary")
    private List<YearRangeString> _samePrimaries;
    @JsonProperty("same_primaries_text")
    private List<YearRangeString> _samePrimariesText;
    @JsonProperty("progression")
    private List<YearRangeString> _progression;

    /**
     * Solid tumor specific
     */
    @JsonProperty("biomarkers")
    private List<YearRangeString> _biomarkers;
    @JsonProperty("treatment_text")
    private List<YearRangeString> _treatmentText;
    @JsonProperty("recurrence")
    private List<YearRangeString> _recurrence;

    public List<DiseaseHistoryEvent> getHistory() {
        return _history;
    }

    public void setHistory(List<DiseaseHistoryEvent> history) {
        _history = history;
    }

    public String getIcdO3Morphology() {
        return _icdO3Morphology;
    }

    public void setIcdO3Morphology(String icdO3Morphology) {
        _icdO3Morphology = icdO3Morphology;
    }

    public List<SiteRange> getPrimarySite() {
        return _primarySite;
    }

    public void setPrimarySite(List<SiteRange> primarySite) {
        _primarySite = primarySite;
    }

    public String getPrimarySiteText() {
        return _primarySiteText;
    }

    public void setPrimarySiteText(String primarySiteText) {
        _primarySiteText = primarySiteText;
    }

    public String getSiteCategory() {
        return _siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        _siteCategory = siteCategory;
    }

    public Type getType() {
        return _type;
    }

    public void setType(Type type) {
        _type = type;
    }

    public YearRange getValid() {
        return _valid;
    }

    public void setValid(YearRange valid) {
        _valid = valid;
    }

    public List<String> getObsoleteNewCode() {
        return _obsoleteNewCode;
    }

    public void setObsoleteNewCode(List<String> obsoleteNewCode) {
        _obsoleteNewCode = obsoleteNewCode;
    }

    public List<YearRange> getReportable() {
        return _reportable;
    }

    public void setReportable(List<YearRange> reportable) {
        _reportable = reportable;
    }

    public List<YearRangeString> getAbstractorNote() {
        return _abstractorNote;
    }

    public void setAbstractorNote(List<YearRangeString> abstractorNote) {
        _abstractorNote = abstractorNote;
    }

    public List<YearRangeString> getTreatment() {
        return _treatment;
    }

    public void setTreatment(List<YearRangeString> treatment) {
        _treatment = treatment;
    }

    public List<YearRangeString> getGenetics() {
        return _genetics;
    }

    public void setGenetics(List<YearRangeString> genetics) {
        _genetics = genetics;
    }

    public List<YearRangeString> getAlternateName() {
        return _alternateName;
    }

    public void setAlternateName(List<YearRangeString> alternateName) {
        _alternateName = alternateName;
    }

    public List<YearRangeString> getDefinition() {
        return _definition;
    }

    public void setDefinition(List<YearRangeString> definition) {
        _definition = definition;
    }

    public List<String> getIcdO2Morphology() {
        return _icdO2Morphology;
    }

    public void setIcdO2Morphology(List<String> icdO2Morphology) {
        _icdO2Morphology = icdO2Morphology;
    }

    public List<String> getIcdO1Morphology() {
        return _icdO1Morphology;
    }

    public void setIcdO1Morphology(List<String> icdO1Morphology) {
        _icdO1Morphology = icdO1Morphology;
    }

    public List<DateRangeString> getIcd10CmCode() {
        return _icd10CmCode;
    }

    public void setIcd10CmCode(List<DateRangeString> icd10CmCode) {
        _icd10CmCode = icd10CmCode;
    }

    public List<String> getIcd10Code() {
        return _icd10Code;
    }

    public void setIcd10Code(List<String> icd10Code) {
        _icd10Code = icd10Code;
    }

    public List<String> getIcd9Code() {
        return _icd9Code;
    }

    public void setIcd9Code(List<String> icd9Code) {
        _icd9Code = icd9Code;
    }

    public List<YearRangeString> getSigns() {
        return _signs;
    }

    public void setSigns(List<YearRangeString> signs) {
        _signs = signs;
    }

    public List<YearRangeString> getExams() {
        return _exams;
    }

    public void setExams(List<YearRangeString> exams) {
        _exams = exams;
    }

    public List<YearRangeString> getRecurrence() {
        return _recurrence;
    }

    public void setRecurrence(List<YearRangeString> recurrence) {
        _recurrence = recurrence;
    }

    public List<YearRangeString> getMortality() {
        return _mortality;
    }

    public void setMortality(List<YearRangeString> mortality) {
        _mortality = mortality;
    }

    public List<DiseaseSource> getSource() {
        return _source;
    }

    public void setSource(List<DiseaseSource> source) {
        _source = source;
    }

    public YearRange getIcdO3Effective() {
        return _icdO3Effective;
    }

    public void setIcdO3Effective(YearRange icdO3Effective) {
        _icdO3Effective = icdO3Effective;
    }

    public YearRange getIcdO2Effective() {
        return _icdO2Effective;
    }

    public void setIcdO2Effective(YearRange icdO2Effective) {
        _icdO2Effective = icdO2Effective;
    }

    public YearRange getIcdO1Effective() {
        return _icdO1Effective;
    }

    public void setIcdO1Effective(YearRange icdO1Effective) {
        _icdO1Effective = icdO1Effective;
    }

    public List<YearRangeString> getMissingPrimarySiteMessage() {
        return _missingPrimarySiteMessage;
    }

    public void setMissingPrimarySiteMessage(List<YearRangeString> missingPrimarySiteMessage) {
        _missingPrimarySiteMessage = missingPrimarySiteMessage;
    }

    public List<YearRangeInteger> getGrade() {
        return _grade;
    }

    public void setGrade(List<YearRangeInteger> grade) {
        _grade = grade;
    }

    public List<YearRangeString> getTransformTo() {
        return _transformTo;
    }

    public void setTransformTo(List<YearRangeString> transformTo) {
        _transformTo = transformTo;
    }

    public List<YearRangeString> getTransformFrom() {
        return _transformFrom;
    }

    public void setTransformFrom(List<YearRangeString> transformFrom) {
        _transformFrom = transformFrom;
    }

    public List<YearRangeString> getTransformFromText() {
        return _transformFromText;
    }

    public void setTransformFromText(List<YearRangeString> transformFromText) {
        _transformFromText = transformFromText;
    }

    public List<YearRangeString> getTransformToText() {
        return _transformToText;
    }

    public void setTransformToText(List<YearRangeString> transformToText) {
        _transformToText = transformToText;
    }

    public List<YearRangeString> getImmunophenotype() {
        return _immunophenotype;
    }

    public void setImmunophenotype(List<YearRangeString> immunophenotype) {
        _immunophenotype = immunophenotype;
    }

    public List<YearRangeString> getDiagnosisMethod() {
        return _diagnosisMethod;
    }

    public void setDiagnosisMethod(List<YearRangeString> diagnosisMethod) {
        _diagnosisMethod = diagnosisMethod;
    }

    public List<YearRangeString> getModuleId() {
        return _moduleId;
    }

    public void setModuleId(List<YearRangeString> moduleId) {
        _moduleId = moduleId;
    }

    public List<YearRangeString> getSamePrimaries() {
        return _samePrimaries;
    }

    public void setSamePrimaries(List<YearRangeString> samePrimaries) {
        _samePrimaries = samePrimaries;
    }

    public List<YearRangeString> getSamePrimariesText() {
        return _samePrimariesText;
    }

    public void setSamePrimariesText(List<YearRangeString> samePrimariesText) {
        _samePrimariesText = samePrimariesText;
    }

    public List<YearRangeString> getBiomarkers() {
        return _biomarkers;
    }

    public void setBiomarkers(List<YearRangeString> biomarkers) {
        _biomarkers = biomarkers;
    }

    public List<YearRangeString> getTreatmentText() {
        return _treatmentText;
    }

    public void setTreatmentText(List<YearRangeString> treatmentText) {
        _treatmentText = treatmentText;
    }

    public List<YearRangeString> getProgression() {
        return _progression;
    }

    public void setProgression(List<YearRangeString> progression) {
        _progression = progression;
    }

    // values for the datasources
    public enum Type {
        SOLID_TUMOR,
        HEMATO
    }
}
