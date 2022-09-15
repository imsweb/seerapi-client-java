/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import retrofit2.Call;

import com.imsweb.seerapi.client.BadRequestException;
import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.shared.Version;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SiteRecodeTest {

    private static SiteRecodeService _SITE_RECODE;

    @BeforeAll
    public static void setup() {
        _SITE_RECODE = new SeerApi.Builder().connect().siteRecode();
    }

    @Test
    void testBadRequestException() {
        Call<SiteRecode> call = _SITE_RECODE.siteGroup("C379", null);
        assertThrows(BadRequestException.class, call::execute);
    }

    @Test
    void testExceptionMessages() throws IOException {
        String message = "";

        try {
            _SITE_RECODE.siteGroup("C379", null).execute();
        }
        catch (BadRequestException e) {
            message = e.getMessage();
        }

        // the API call works out to:
        //     https://api.seer.cancer.gov/rest/recode/sitegroup?site=C379
        assertEquals("Required request parameter 'hist' for method parameter type String is not present", message);
    }

    @Test
    void testSiteRecordVersion() throws IOException {
        String version = _SITE_RECODE.version().execute().body().getVersion();

        assertNotNull(version);
        assertTrue(version.length() > 0);
    }

    @Test
    void testSiteRecode() throws IOException {
        Call<SiteRecode> call = _SITE_RECODE.siteGroup("C379", "9650");
        SiteRecode recode = call.execute().body();

        assertNotNull(recode);
        assertEquals("C379", recode.getSite());
        assertEquals("9650", recode.getHist());
        assertEquals("33011", recode.getSiteGroup());
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(Version.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(SiteRecode.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

}
