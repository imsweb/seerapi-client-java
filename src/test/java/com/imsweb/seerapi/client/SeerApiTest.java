/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;

import javax.ws.rs.BadRequestException;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.siterecode.SiteRecode;

public class SeerApiTest {

    @Test(expected = BadRequestException.class)
    public void testBadParameterExceptiion() throws IOException {
        SeerApi.connect().siteRecode("C379", null);
    }

    @Test
    public void testSiteRecode() throws IOException {
        SiteRecode recode = SeerApi.connect().siteRecode("C379", "9650");

        Assert.assertEquals("C379", recode.getSite());
        Assert.assertEquals("9650", recode.getHist());
        Assert.assertEquals("33011", recode.getSiteGroup());
    }

}
