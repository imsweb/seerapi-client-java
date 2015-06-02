/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaaccrField {

    @JsonProperty("item")
    protected Integer _item;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("start_col")
    protected Integer _start;
    @JsonProperty("end_col")
    protected Integer _end;
    @JsonProperty("alignment")
    protected String _align;
    @JsonProperty("padding_char")
    protected String _padChar;
    @JsonProperty("documentation")
    protected String _documentation;
    @JsonProperty("subfield")
    protected List<NaaccrSubField> _subFields;

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
