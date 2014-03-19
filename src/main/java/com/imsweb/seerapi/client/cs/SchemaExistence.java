package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

public class SchemaExistence {

    @JsonProperty("site")
    protected String _site;
    @JsonProperty("histology")
    protected String _histology;
    @JsonProperty("is_valid")
    protected Boolean _isValid;
    @JsonProperty("needs_descriminator")
    protected Boolean _needDescriminator;
    @JsonProperty("discriminator_table")
    protected TableDto _table;

    public String getSite() {
        return _site;
    }

    public String getHistology() {
        return _histology;
    }

    public Boolean getIsValid() {
        return _isValid;
    }

    public Boolean getNeedDescriminator() {
        return _needDescriminator;
    }

    public TableDto getTable() {
        return _table;
    }
}
