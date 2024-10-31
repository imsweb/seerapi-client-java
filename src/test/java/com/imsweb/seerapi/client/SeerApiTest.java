/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.util.List;

import org.junit.jupiter.api.Test;

import retrofit2.Call;

import com.imsweb.seerapi.client.siterecode.SiteGroupAlgorithm;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SeerApiTest {

    @Test
    void testBadApiKeyAndURL() {
        Call<List<SiteGroupAlgorithm>> call = new SeerApi.Builder().url("https://api.seer.cancer.gov/rest/").apiKey("BAD KEY").connect().siteRecode().algorithms();
        assertThrows(NotAuthorizedException.class, call::execute);
    }

    @Test
    void testBadApiKey() {
        Call<List<SiteGroupAlgorithm>> call = new SeerApi.Builder().apiKey("BAD KEY").connect().siteRecode().algorithms();
        assertThrows(NotAuthorizedException.class, call::execute);
    }

}
