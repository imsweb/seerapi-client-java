package com.imsweb.seerapi.client.glossary;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.publishable.Publishable;

public class Glossary extends Publishable {

    // values for the datasources
    public enum Category {
        GENERAL,
        SOLID_TUMOR,
        HEMATO,
        SEERRX
    }

    @JsonProperty("history")
    protected List<GlossaryHistoryEvent> _history;

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

    public List<GlossaryHistoryEvent> getHistory() {
        return _history;
    }

    public void setHistory(List<GlossaryHistoryEvent> history) {
        _history = history;
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
