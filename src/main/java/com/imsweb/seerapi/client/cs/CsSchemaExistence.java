package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

public class CsSchemaExistence {

    @JsonProperty("site")
    protected String _site;
    @JsonProperty("histology")
    protected String _histology;
    @JsonProperty("is_valid")
    protected Boolean _isValid;
    @JsonProperty("needs_descriminator")
    protected Boolean _needDescriminator;
    @JsonProperty("discriminator_table")
    protected CsTable _table;

    public String getSite() {
        return _site;
    }

    public String getHistology() {
        return _histology;
    }

    public Boolean isValid() {
        return _isValid;
    }

    public Boolean needsDescriminator() {
        return _needDescriminator;
    }

    public CsTable getTable() {
        return _table;
    }
}
