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

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public Integer getItem() {
        return _item;
    }

    public void setItem(Integer item) {
        _item = item;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getSection() {
        return _section;
    }

    public void setSection(String section) {
        _section = section;
    }

    public Integer getStart() {
        return _start;
    }

    public void setStart(Integer start) {
        _start = start;
    }

    public Integer getEnd() {
        return _end;
    }

    public void setEnd(Integer end) {
        _end = end;
    }

    public String getAlign() {
        return _align;
    }

    public void setAlign(String align) {
        _align = align;
    }

    public String getPadChar() {
        return _padChar;
    }

    public void setPadChar(String padChar) {
        _padChar = padChar;
    }

    public String getDefaultValue() {
        return _defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        _defaultValue = defaultValue;
    }

    public String getDocumentation() {
        return _documentation;
    }

    public void setDocumentation(String documentation) {
        _documentation = documentation;
    }

    public List<NaaccrSubField> getSubFields() {
        return _subFields;
    }

    public void setSubFields(List<NaaccrSubField> subFields) {
        _subFields = subFields;
    }
}
