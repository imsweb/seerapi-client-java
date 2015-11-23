/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;

import org.junit.Test;

public class SeerApiTest {

    @Test(expected = NotAuthorizedException.class)
    public void testBadApiKeyAndURL() throws IOException {
        new SeerApi.Builder().url("https://api.seer.cancer.gov/rest/").apiKey("BAD KEY").connect().siteRecode().version().execute();
    }

    @Test(expected = NotAuthorizedException.class)
    public void testBadApiKey() throws IOException {
        new SeerApi.Builder().apiKey("BAD KEY").connect().siteRecode().version().execute();
    }

}
