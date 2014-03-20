/*
 * Copyright (C) 2011 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Simple Java object that contains all of the schemas relevant information.
 * @author criderp
 */
public class CsSchemaName {

    @JsonProperty("number")
    protected int _schemaNumber;
    @JsonProperty("name")
    protected String _name;

    public int getSchemaNumber() {
        return _schemaNumber;
    }

    public String getName() {
        return _name;
    }
}