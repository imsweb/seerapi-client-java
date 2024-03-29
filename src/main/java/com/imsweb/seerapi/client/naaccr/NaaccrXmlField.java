/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "item", "name", "section", "parent_xml_element", "record_types", "data_type", "length", "start_col",
        "pad_type", "trim_type", "allow_unlimited_text", "documentation"})
public class NaaccrXmlField {

    @JsonProperty("id")
    protected String _naaccrId;
    @JsonProperty("item")
    protected Integer _naaccrItemNum;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("section")
    protected String _section;
    @JsonProperty("start_col")
    protected Integer _start;
    @JsonProperty("record_types")
    protected List<String> _recordTypes;
    @JsonProperty("length")
    protected Integer _length;
    @JsonProperty("pad_type")
    protected String _padType;
    @JsonProperty("data_type")
    protected String _dataType;
    @JsonProperty("trim_type")
    protected String _trimType;
    @JsonProperty("allow_unlimited_text")
    protected Boolean _allowUnlimitedText;
    @JsonProperty("parent_xml_element")
    protected String _parentXmlElement;
    @JsonProperty("documentation")
    protected String _documentation;

    public String getNaaccrId() {
        return _naaccrId;
    }

    public Integer getItemNum() {
        return _naaccrItemNum;
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

    public List<String> getRecordTypes() {
        return _recordTypes;
    }

    public Integer getLength() {
        return _length;
    }

    public String getPadType() {
        return _padType;
    }

    public String getDataType() {
        return _dataType;
    }

    public String getTrimType() {
        return _trimType;
    }

    public Boolean getAllowUnlimitedText() {
        return _allowUnlimitedText;
    }

    public String getParentXmlElement() {
        return _parentXmlElement;
    }

    public String getDocumentation() {
        return _documentation;
    }

}
