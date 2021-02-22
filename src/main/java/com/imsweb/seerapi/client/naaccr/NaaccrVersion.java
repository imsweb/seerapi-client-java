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

    public String getName() {
        return _name;
    }

    public Integer getLength() {
        return _length;
    }

    public String getDescription() {
        return _description;
    }

    public String getStyle() {
        return _style;
    }

    public String getDictionaryUri() {
        return dictionaryUri;
    }

    public String getDictionaryDescription() {
        return dictionaryDescription;
    }
    public String getSpecificationVersion() {
        return specificationVersion;
    }
}
