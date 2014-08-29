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
        SeerApi.connect("https://api.seer.cancer.gov/rest/", "BAD KEY").csVersions();
    }

    @Test(expected = NotAuthorizedException.class)
    public void testBadApiKey() throws IOException {
        SeerApi.connect("BAD KEY").csVersions();
    }

}
