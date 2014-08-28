package com.imsweb.seerapi.client.publishable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;

import com.imsweb.seerapi.client.shared.KeywordMatch;

public class Publishable {

    /**
     * Shared properties for all publishable beans (Disease, RX and Glossary)
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
    @JsonProperty("score")
    protected Integer _score;
    @JsonProperty("glossary")
    protected Set<KeywordMatch> _glossaryMatches;

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

    public Integer getScore() {
        return _score;
    }

    public Set<KeywordMatch> getGlossaryMatches() {
        return _glossaryMatches;
    }
}
