package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

public class CodeValidity {

    @JsonProperty("schema_number")
    protected Integer _schemaNumber;
    @JsonProperty("table_number")
    protected Integer _tableNumber;
    @JsonProperty("code")
    protected String _code;
    @JsonProperty("is_valid")
    protected Boolean _isValid;
    @JsonProperty("is_obsolete")
    protected Boolean _isObsolete;

    public Integer getSchemaNumber() {
        return _schemaNumber;
    }

    public Integer getTableNumber() {
        return _tableNumber;
    }

    public String getCode() {
        return _code;
    }

    public Boolean getIsValid() {
        return _isValid;
    }

    public Boolean getIsObsolete() {
        return _isObsolete;
    }
}
