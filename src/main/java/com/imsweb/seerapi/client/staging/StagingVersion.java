/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

public class StagingVersion {

    private String _algorithm;
    private String _version;
    private Type _type;
    private Integer _yearFrom;
    private Integer _yearTo;
    private Date _lastModified;
    /**
     * Default constructor is required by Morphia
     */
    public StagingVersion() {
    }
    public StagingVersion(String algorithm, String version) {
        setAlgorithm(algorithm);
        setVersion(version);
    }

    @JsonProperty("algorithm")
    public String getAlgorithm() {
        return _algorithm;
    }

    public void setAlgorithm(String algorithm) {
        _algorithm = algorithm;
    }

    @JsonProperty("version")
    public String getVersion() {
        return _version;
    }

    public void setVersion(String version) {
        _version = version;
    }

    @JsonProperty("type")
    public Type getType() {
        return _type;
    }

    public void setType(Type type) {
        _type = type;
    }

    @JsonProperty("year_from")
    public Integer getYearFrom() {
        return _yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        _yearFrom = yearFrom;
    }

    @JsonProperty("year_to")
    public Integer getYearTo() {
        return _yearTo;
    }

    public void setYearTo(Integer yearTo) {
        _yearTo = yearTo;
    }

    @JsonProperty("last_modified")
    public Date getLastModified() {
        return _lastModified;
    }

    public void setLastModified(Date lastModified) {
        _lastModified = lastModified;
    }

    @JsonIgnore
    public boolean isProduction() {
        return Type.PRODUCTION.equals(getType());
    }

    @JsonIgnore
    public boolean isBeta() {
        return Type.BETA.equals(getType());
    }

    @JsonIgnore
    public boolean isDevelopment() {
        return Type.DEVELOPMENT.equals(getType());
    }

    public enum Type {
        PRODUCTION,
        BETA,
        DEVELOPMENT
    }

}
