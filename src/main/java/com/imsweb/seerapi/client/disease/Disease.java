package com.imsweb.seerapi.client.disease;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.shared.KeywordMatch;

public class Disease {

    // values for the datasources
    public enum Type {
        SOLID_TUMOR,
        HEMATO
    }

    /**
     * These properties were on the Publishable bean.
     */
    @JsonProperty("id")
    protected String _id;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("version")
    protected String _version;
    @JsonProperty("hidden")
    protected Boolean _hidden;
    @JsonProperty("status")
    protected String _status;
    @JsonProperty("assigned_to")
    protected String _assignedTo;
    @JsonProperty("first_published")
    protected Date _firstPublished;
    @JsonProperty("last_modified")
    protected Date _lastModified;
    @JsonProperty("fingerprint")
    protected String _fingerprint;
    @JsonProperty("note")
    protected String _note;
    @JsonProperty("field_notes")
    protected Map<String, String> _fieldNotes;
    @JsonProperty("history")
    protected List<DiseaseHistoryEvent> _history;
    @JsonProperty("score")
    protected Integer _score;
    @JsonProperty("glossary")
    protected Set<KeywordMatch> _glossaryMatches;

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

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getVersion() {
        return _version;
    }

    public Boolean getHidden() {
        return _hidden;
    }

    public String getStatus() {
        return _status;
    }

    public String getAssignedTo() {
        return _assignedTo;
    }

    public Date getFirstPublished() {
        return _firstPublished;
    }

    public Date getLastModified() {
        return _lastModified;
    }

    public String getFingerprint() {
        return _fingerprint;
    }

    public String getNote() {
        return _note;
    }

    public Map<String, String> getFieldNotes() {
        return _fieldNotes;
    }

    public List<DiseaseHistoryEvent> getHistory() {
        return _history;
    }

    public Integer getScore() {
        return _score;
    }

    public Set<KeywordMatch> getGlossaryMatches() {
        return _glossaryMatches;
    }

    public String getIcdO3Morphology() {
        return _icdO3Morphology;
    }

    public List<SiteRange> getPrimarySite() {
        return _primarySite;
    }

    public String getSiteCategory() {
        return _siteCategory;
    }

    public Type getType() {
        return _type;
    }

    public YearRange getValid() {
        return _valid;
    }

    public List<String> getObsoleteNewCode() {
        return _obsoleteNewCode;
    }

    public List<YearRange> getReportable() {
        return _reportable;
    }

    public List<YearRangeString> getAbstractorNote() {
        return _abstractorNote;
    }

    public List<YearRangeString> getTreatment() {
        return _treatment;
    }

    public List<YearRangeString> getGenetics() {
        return _genetics;
    }

    public List<YearRangeString> getAlternateName() {
        return _alternateName;
    }

    public List<YearRangeString> getDefinition() {
        return _definition;
    }

    public String getIcdO2Morphology() {
        return _icdO2Morphology;
    }

    public String getIcdO1Morphology() {
        return _icdO1Morphology;
    }

    public List<String> getIcd10CmCode() {
        return _icd10CmCode;
    }

    public List<String> getIcd10Code() {
        return _icd10Code;
    }

    public List<String> getIcd9Code() {
        return _icd9Code;
    }

    public List<YearRangeString> getSigns() {
        return _signs;
    }

    public List<YearRangeString> getExams() {
        return _exams;
    }

    public List<YearRangeString> getRecurrence() {
        return _recurrence;
    }

    public List<YearRangeString> getMortality() {
        return _mortality;
    }

    public YearRange getIcdO3Effective() {
        return _icdO3Effective;
    }

    public YearRange getIcdO2Effective() {
        return _icdO2Effective;
    }

    public YearRange getIcdO1Effective() {
        return _icdO1Effective;
    }

    public List<YearRangeString> getMissingPrimarySiteMessage() {
        return _missingPrimarySiteMessage;
    }

    public List<YearRangeInteger> getGrade() {
        return _grade;
    }

    public List<YearRangeString> getTransformTo() {
        return _transformTo;
    }

    public List<YearRangeString> getTransformFrom() {
        return _transformFrom;
    }

    public List<YearRangeString> getImmunophenotype() {
        return _immunophenotype;
    }

    public List<YearRangeString> getDiagnosisMethod() {
        return _diagnosisMethod;
    }

    public List<YearRangeString> getModuleId() {
        return _moduleId;
    }

    public List<YearRangeString> getSamePrimaries() {
        return _samePrimaries;
    }

    public List<YearRangeString> getBiomarkers() {
        return _biomarkers;
    }

    public List<YearRangeString> getTreatmentText() {
        return _treatmentText;
    }
}
