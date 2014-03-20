package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

public class CsInvolvedTable {

    @JsonProperty("table_number")
    protected Integer _tableNumber;
    @JsonProperty("title")
    protected String _title;
    @JsonProperty("subtitle")
    protected String _subtitle;
    @JsonProperty("role")
    protected String _role;

    public Integer getTableNumber() {
        return _tableNumber;
    }

    public String getTitle() {
        return _title;
    }

    public String getSubtitle() {
        return _subtitle;
    }

    public String getRole() {
        return _role;
    }
}
