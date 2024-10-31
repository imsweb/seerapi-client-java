/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import java.io.IOException;
import java.util.List;

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

public class SiteRecodeTest {

    private static SiteRecodeService _SITE_RECODE;

    @BeforeAll
    public static void setup() {
        _SITE_RECODE = new SeerApi.Builder().connect().siteRecode();
    }

    @Test
    void testBadRequestException() {
        Call<SiteRecode> call = _SITE_RECODE.siteGroup("seer", "C379", null, null);
        assertThrows(BadRequestException.class, call::execute);
    }

    @Test
    void testSiteRecodeAlgorithms() throws IOException {
        List<SiteGroupAlgorithm> algorithms = _SITE_RECODE.algorithms().execute().body();

        assertNotNull(algorithms);
        assertEquals(3, algorithms.size());
    }

    @Test
    void testExceptionMessages() throws IOException {
        String message = "";

        try {
            _SITE_RECODE.siteGroup("seer", "C379", null, null).execute();
        }
        catch (BadRequestException e) {
            message = e.getMessage();
        }

        // the API call works out to:
        //     https://api.seer.cancer.gov/rest/recode/sitegroup/seer?site=C379
        assertEquals("Required parameter 'hist' is not present.", message);
    }

    @Test
    void testSiteRecode() throws IOException {
        Call<SiteRecode> call = _SITE_RECODE.siteGroup("seer", "C379", "9650", null);
        SiteRecode recode = call.execute().body();

        assertNotNull(recode);
        assertEquals("C379", recode.getSite());
        assertEquals("9650", recode.getHist());
        assertEquals("33011", recode.getSiteGroup());

        call = _SITE_RECODE.siteGroup("iccc", "C379", "9650", "3");
        recode = call.execute().body();
        assertNotNull(recode);
        assertEquals("C379", recode.getSite());
        assertEquals("9650", recode.getHist());
        assertEquals("021", recode.getSiteGroup());

        call = _SITE_RECODE.siteGroup("aya", "C379", "9650", "3");
        recode = call.execute().body();
        assertNotNull(recode);
        assertEquals("C379", recode.getSite());
        assertEquals("9650", recode.getHist());
        assertEquals("06", recode.getSiteGroup());
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(Version.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(SiteRecode.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

}
