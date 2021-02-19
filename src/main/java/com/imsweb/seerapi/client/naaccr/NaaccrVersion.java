package com.imsweb.seerapi.client.naaccr;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaaccrVersion {

    @JsonProperty("version")
    protected String _version;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("length")
    protected Integer _length;
    @JsonProperty("description")
    protected String _description;
    @JsonProperty("style")
    protected String _style;
    @JsonProperty("dictionary_uri")
    protected String dictionaryUri;
    @JsonProperty("dictionary_description")
    protected String dictionaryDescription;
    @JsonProperty("specification_version")
    protected String specificationVersion;

    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public Integer getLength() {
        return _length;
    }

    public void setLength(Integer length) {
        _length = length;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public String getStyle() {
        return _style;
    }

    public void setStyle(String style) {
        _style = style;
    }

    public String getDictionaryUri() {
        return dictionaryUri;
    }

    public void setDictionaryUri(String dictionaryUri) {
        this.dictionaryUri = dictionaryUri;
    }

    public String getDictionaryDescription() {
        return dictionaryDescription;
    }

    public void setDictionaryDescription(String dictionaryDescription) {
        this.dictionaryDescription = dictionaryDescription;
    }

    public String getSpecificationVersion() {
        return specificationVersion;
    }

    public void setSpecificationVersion(String specificationVersion) {
        this.specificationVersion = specificationVersion;
    }
}
