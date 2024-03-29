/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging.tnm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.imsweb.seerapi.client.staging.SchemaLookup;
import com.imsweb.seerapi.client.staging.StagingData;

public class TnmSchemaLookup extends SchemaLookup {

    private static final Set<String> _ALLOWED_KEYS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(StagingData.PRIMARY_SITE_KEY,
            StagingData.HISTOLOGY_KEY, TnmStagingData.SSF25_KEY, TnmStagingData.SEX_KEY)));

    /**
     * Constructor
     * @param site primary site
     * @param histology histology
     */
    public TnmSchemaLookup(String site, String histology) {
        super(site, histology);
    }

    @Override
    public Set<String> getAllowedKeys() {
        return _ALLOWED_KEYS;
    }

}
