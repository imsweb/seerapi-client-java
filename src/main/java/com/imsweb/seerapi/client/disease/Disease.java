package com.imsweb.seerapi.client.disease;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.publishable.Publishable;

public class Disease extends Publishable {

    // values for the datasources
    public enum Type {
        SOLID_TUMOR,
        HEMATO
    }

    @JsonProperty("history")
    protected List<DiseaseHistoryEvent> _history;

    /**
     * Identifying information
     */
    @JsonProperty("icdO3_morphology")
    protected String _icdO3Morphology;
    @JsonProperty("primary_site")
    protected List<SiteRange> _primarySite;
    @JsonProperty("site_category")
    protected String _siteCategory;
    @JsonProperty("type")
    protected Type _type;

    /**
     * Obsolete ranges
     */
    @JsonProperty("valid")
    protected YearRange _valid;
    @JsonProperty("obsolete_new_code")
    protected List<String> _obsoleteNewCode;

    /**
     * Shared disease properties
     */
    @JsonProperty("reportable")
    protected List<YearRange> _reportable;
    @JsonProperty("abstractor_note")
    protected List<YearRangeString> _abstractorNote;
    @JsonProperty("treatment")
    protected List<YearRangeString> _treatment;
    @JsonProperty("genetics")
    protected List<YearRangeString> _genetics;
    @JsonProperty("alternate_name")
    protected List<YearRangeString> _alternateName;
    @JsonProperty("definition")
    protected List<YearRangeString> _definition;
    @JsonProperty("icdO2_morphology")
    protected String _icdO2Morphology;
    @JsonProperty("icdO1_morphology")
    protected String _icdO1Morphology;
    @JsonProperty("icd_10cm_code")
    protected List<String> _icd10CmCode;
    @JsonProperty("icd_10_code")
    protected List<String> _icd10Code;
    @JsonProperty("icd_9_code")
    protected List<String> _icd9Code;
    @JsonProperty("signs")
    protected List<YearRangeString> _signs;
    @JsonProperty("exams")
    protected List<YearRangeString> _exams;
    @JsonProperty("recurrence")
    protected List<YearRangeString> _recurrence;
    @JsonProperty("mortality")
    protected List<YearRangeString> _mortality;

    /**
     * Morphology ranges
     */
    @JsonProperty("icdO3_effective")
    protected YearRange _icdO3Effective;
    @JsonProperty("icdO2_effective")
    protected YearRange _icdO2Effective;
    @JsonProperty("icdO1_effective")
    protected YearRange _icdO1Effective;

    /**
     * Hemato specific
     */
    @JsonProperty("missing_primary_site_message")
    protected List<YearRangeString> _missingPrimarySiteMessage;
    @JsonProperty("grade")
    protected List<YearRangeInteger> _grade;
    @JsonProperty("transform_to")
    protected List<YearRangeString> _transformTo;
    @JsonProperty("transform_from")
    protected List<YearRangeString> _transformFrom;
    @JsonProperty("immunophenotype")
    protected List<YearRangeString> _immunophenotype;
    @JsonProperty("diagnosis_method")
    protected List<YearRangeString> _diagnosisMethod;
    @JsonProperty("module_id")
    protected List<YearRangeString> _moduleId;
    @JsonProperty("same_primary")
    protected List<YearRangeString> _samePrimaries;

    /**
     * Solid tumor specific
     */
    @JsonProperty("biomarkers")
    protected List<YearRangeString> _biomarkers;
    @JsonProperty("treatment_text")
    protected List<YearRangeString> _treatmentText;

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

    public String getIcdO2Morphology() {
        return _icdO2Morphology;
    }

    public void setIcdO2Morphology(String icdO2Morphology) {
        _icdO2Morphology = icdO2Morphology;
    }

    public String getIcdO1Morphology() {
        return _icdO1Morphology;
    }

    public void setIcdO1Morphology(String icdO1Morphology) {
        _icdO1Morphology = icdO1Morphology;
    }

    public List<String> getIcd10CmCode() {
        return _icd10CmCode;
    }

    public void setIcd10CmCode(List<String> icd10CmCode) {
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
}
