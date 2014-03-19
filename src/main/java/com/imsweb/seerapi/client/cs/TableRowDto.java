/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Simple Java object that contains all of the values for a CollaborativeStageTableRow.
 */
public class TableRowDto {

    @JsonProperty("cell")
    protected List<String> _cells;

    public List<String> getCells() {
        return _cells;
    }
}