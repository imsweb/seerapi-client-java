package com.imsweb.seerapi.client.glossary;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.shared.KeywordMatch;

public class Glossary {

    // values for the datasources
    public enum Category {
        GENERAL,
        SOLID_TUMOR,
        HEMATO,
        SEERRX
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
    protected List<GlossaryHistoryEvent> _history;
    @JsonProperty("score")
    protected Integer _score;
    @JsonProperty("glossary")
    protected Set<KeywordMatch> _glossaryMatches;

    /**
     * Glossary-specific fields
     */
    @JsonProperty("definition")
    protected String _definition;
    @JsonProperty("alternate_name")
    protected List<String> _alternateName;
    @JsonProperty("abstractor_note")
    protected String _abstractorNote;
    @JsonProperty("histology")
    protected List<String> _histology;
    @JsonProperty("primary_site")
    protected List<String> _primarySite;
    @JsonProperty("category")
    protected List<Category> _categories;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public Boolean getHidden() {
        return _hidden;
    }

    public void setHidden(Boolean hidden) {
        _hidden = hidden;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public String getAssignedTo() {
        return _assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        _assignedTo = assignedTo;
    }

    public Date getFirstPublished() {
        return _firstPublished;
    }

    public void setFirstPublished(Date firstPublished) {
        _firstPublished = firstPublished;
    }

    public Date getLastModified() {
        return _lastModified;
    }

    public void setLastModified(Date lastModified) {
        _lastModified = lastModified;
    }

    public String getFingerprint() {
        return _fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        _fingerprint = fingerprint;
    }

    public String getNote() {
        return _note;
    }

    public void setNote(String note) {
        _note = note;
    }

    public Map<String, String> getFieldNotes() {
        return _fieldNotes;
    }

    public void setFieldNotes(Map<String, String> fieldNotes) {
        _fieldNotes = fieldNotes;
    }

    public List<GlossaryHistoryEvent> getHistory() {
        return _history;
    }

    public void setHistory(List<GlossaryHistoryEvent> history) {
        _history = history;
    }

    public Integer getScore() {
        return _score;
    }

    public void setScore(Integer score) {
        _score = score;
    }

    public Set<KeywordMatch> getGlossaryMatches() {
        return _glossaryMatches;
    }

    public void setGlossaryMatches(Set<KeywordMatch> glossaryMatches) {
        _glossaryMatches = glossaryMatches;
    }

    public String getDefinition() {
        return _definition;
    }

    public void setDefinition(String definition) {
        _definition = definition;
    }

    public List<String> getAlternateName() {
        return _alternateName;
    }

    public void setAlternateName(List<String> alternateName) {
        _alternateName = alternateName;
    }

    public String getAbstractorNote() {
        return _abstractorNote;
    }

    public void setAbstractorNote(String abstractorNote) {
        _abstractorNote = abstractorNote;
    }

    public List<String> getHistology() {
        return _histology;
    }

    public void setHistology(List<String> histology) {
        _histology = histology;
    }

    public List<String> getPrimarySite() {
        return _primarySite;
    }

    public void setPrimarySite(List<String> primarySite) {
        _primarySite = primarySite;
    }

    public List<Category> getCategories() {
        return _categories;
    }

    public void setCategories(List<Category> categories) {
        _categories = categories;
    }
}
