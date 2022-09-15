/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import retrofit2.Call;

import com.imsweb.seerapi.client.shared.Version;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SeerApiTest {

    @Test
    void testBadApiKeyAndURL() {
        Call<Version> call = new SeerApi.Builder().url("https://api.seer.cancer.gov/rest/").apiKey("BAD KEY").connect().siteRecode().version();
        assertThrows(NotAuthorizedException.class, call::execute);
    }

    @Test
    void testBadApiKey() throws IOException {
        Call<Version> call = new SeerApi.Builder().apiKey("BAD KEY").connect().siteRecode().version();
        assertThrows(NotAuthorizedException.class, call::execute);
    }

}
