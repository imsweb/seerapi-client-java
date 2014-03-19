/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Simple Java object that represents a single site-specific-factor (SSF1 to SSF25).
 * @author may
 */
public class SiteSpecificFactorDto {

    @JsonProperty("number")
    protected int _ssfNumber;
    @JsonProperty("name")
    protected String _name;
    @JsonProperty("already_collected")
    protected boolean _alreadyCollected;
    @JsonProperty("needed_for_staging")
    protected boolean _neededForStaging;
    @JsonProperty("clinically_significant")
    protected boolean _clinicallySignificant;
    @JsonProperty("required_pre_2010")
    protected boolean _requiredPre2010;
    @JsonProperty("default_value")
    protected String _defaultValue;

    public int getSsfNumber() {
        return _ssfNumber;
    }

    public String getName() {
        return _name;
    }

    public boolean isAlreadyCollected() {
        return _alreadyCollected;
    }

    public boolean isNeededForStaging() {
        return _neededForStaging;
    }

    public boolean isClinicallySignificant() {
        return _clinicallySignificant;
    }

    public boolean isRequiredPre2010() {
        return _requiredPre2010;
    }

    public String getDefaultValue() {
        return _defaultValue;
    }
}
