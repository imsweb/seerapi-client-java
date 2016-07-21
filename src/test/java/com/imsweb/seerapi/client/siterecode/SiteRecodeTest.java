/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import retrofit2.Call;

import com.imsweb.seerapi.client.BadRequestException;
import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.shared.Version;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SiteRecodeTest {

    private static SiteRecodeService _SITE_RECODE;

    @BeforeClass
    public static void setup() {
        _SITE_RECODE = new SeerApi.Builder().connect().siteRecode();
    }

    @Test(expected = BadRequestException.class)
    public void testBadRequestExceptiion() throws IOException {
        _SITE_RECODE.siteGroup("C379", null).execute();
    }

    @Test
    public void testExceptionMessages() throws IOException {
        String message = "";

        try {
            _SITE_RECODE.siteGroup("C379", null).execute();
        }
        catch (BadRequestException e) {
            message = e.getMessage();
        }

        // the API call works out to:
        //     https://api.seer.cancer.gov/rest/recode/sitegroup?site=C379
        // and the full message returned should be
        //     {"code":400,"message":"Site and histology must be supplied"}
        assertEquals("Site and histology must be supplied", message);
    }

    @Test
    public void testSiteRecordVersion() throws IOException {
        Call<Version> call = _SITE_RECODE.version();
        String version = call.execute().body().getVersion();

        assertNotNull(version);
        assertTrue(version.length() > 0);
    }

    @Test
    public void testSiteRecode() throws IOException {
        Call<SiteRecode> call = _SITE_RECODE.siteGroup("C379", "9650");
        SiteRecode recode = call.execute().body();

        assertEquals("C379", recode.getSite());
        assertEquals("9650", recode.getHist());
        assertEquals("33011", recode.getSiteGroup());
    }

}
