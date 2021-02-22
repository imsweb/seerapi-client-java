/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"item", "name", "section", "start_col", "end_col", "alignment", "padding_char", "default_value", "documentation", "subfield"})
public class NaaccrFlatField {

    @JsonProperty("id")
    protected String _id;
    @JsonProperty("item")
    protected Integer _item;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("section")
    protected String _section;
    @JsonProperty("start_col")
    protected Integer _start;
    @JsonProperty("end_col")
    protected Integer _end;
    @JsonProperty("alignment")
    protected String _align;
    @JsonProperty("padding_char")
    protected String _padChar;
    @JsonProperty("default_value")
    protected String _defaultValue;
    @JsonProperty("documentation")
    protected String _documentation;
    @JsonProperty("subfield")
    protected List<NaaccrSubField> _subFields;

    public String getNaaccrId() {
        return _id;
    }

    public Integer getItemNum() {
        return _item;
    }

    public String getName() {
        return _name;
    }

    public String getSection() {
        return _section;
    }

    public Integer getStart() {
        return _start;
    }

    public Integer getEnd() {
        return _end;
    }

    public String getAlign() {
        return _align;
    }

    public String getPadChar() {
        return _padChar;
    }

    public String getDefaultValue() {
        return _defaultValue;
    }

    public String getDocumentation() {
        return _documentation;
    }

    public List<NaaccrSubField> getSubFields() {
        return _subFields;
    }
}
