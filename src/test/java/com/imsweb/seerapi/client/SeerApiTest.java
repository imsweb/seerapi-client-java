/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;

import javax.ws.rs.NotAuthorizedException;

import org.junit.Test;

public class SeerApiTest {

    @Test(expected = NotAuthorizedException.class)
    public void testBadApiKeyAndURL() throws IOException {
        new SeerApiBuilder().url("https://api.seer.cancer.gov/rest/").apiKey("BAD KEY").connect().diseaseVersions();
    }

    @Test(expected = NotAuthorizedException.class)
    public void testBadApiKey() throws IOException {
        new SeerApiBuilder().apiKey("BAD KEY").connect().diseaseVersions();
    }

}
