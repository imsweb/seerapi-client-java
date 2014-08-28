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

    public String getDefinition() {
        return _definition;
    }

    public List<String> getAlternateName() {
        return _alternateName;
    }

    public String getAbstractorNote() {
        return _abstractorNote;
    }

    public List<String> getHistology() {
        return _histology;
    }

    public List<String> getPrimarySite() {
        return _primarySite;
    }

    public List<Category> getCategories() {
        return _categories;
    }
}
