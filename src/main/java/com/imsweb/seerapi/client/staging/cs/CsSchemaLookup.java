/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging.cs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.imsweb.seerapi.client.staging.SchemaLookup;
import com.imsweb.seerapi.client.staging.StagingData;

public class CsSchemaLookup extends SchemaLookup {

    private static final Set<String> _ALLOWED_KEYS = new HashSet<>(Arrays.asList(StagingData.PRIMARY_SITE_KEY, StagingData.HISTOLOGY_KEY, CsStagingData.SSF25_KEY));

    /**
     * Default constructor
     */
    public CsSchemaLookup() {
    }

    /**
     * Constructor
     * @param site primary site
     * @param histology histology
     */
    public CsSchemaLookup(String site, String histology) {
        super(site, histology);
    }

    /**
     * Constructor
     * @param site primary site
     * @param histology histology
     * @param discriminator ssf25
     */
    public CsSchemaLookup(String site, String histology, String discriminator) {
        super(site, histology);

        setInput(CsStagingData.SSF25_KEY, discriminator);
    }

    @Override
    public Set<String> getAllowedKeys() {
        return _ALLOWED_KEYS;
    }

}
