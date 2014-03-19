/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.naaccr.NaaccrField;
import com.imsweb.seerapi.client.naaccr.NaaccrFieldName;
import com.imsweb.seerapi.client.naaccr.NaaccrVersion;
import com.imsweb.seerapi.client.siterecode.SiteRecode;

public class SeerApiTest {

    @Test(expected = BadRequestException.class)
    public void testBadParameterExceptiion() throws IOException {
        SeerApi.connect().siteRecode("C379", null);
    }

    @Test
    public void testSiteRecordVersion() throws IOException {
        String version = SeerApi.connect().siteRecodeVersion();
        Assert.assertNotNull(version);
        Assert.assertTrue(version.length() > 0);
    }

    @Test
    public void testSiteRecode() throws IOException {
        SiteRecode recode = SeerApi.connect().siteRecode("C379", "9650");

        Assert.assertEquals("C379", recode.getSite());
        Assert.assertEquals("9650", recode.getHist());
        Assert.assertEquals("33011", recode.getSiteGroup());
    }

    @Test
    public void testNaaccrVersions() throws IOException {
        List<NaaccrVersion> versions = SeerApi.connect().naaccrVersions();

        Assert.assertTrue(versions.size() > 0);
        for (NaaccrVersion version : versions) {
            Assert.assertTrue(version.getName().length() > 0);
            Assert.assertEquals(22824, version.getLength().longValue());
            Assert.assertTrue(version.getDescription().length() > 0);
            Assert.assertTrue(version.getStyle().length() > 0);
        }
    }

    @Test
    public void testNaaccrFieldNames() throws IOException {
        List<NaaccrFieldName> names = SeerApi.connect().naaccrFieldNames("latest");

        Assert.assertTrue(names.size() > 0);
        for (NaaccrFieldName name : names) {
            Assert.assertTrue(name.getItem() > 0);
            Assert.assertTrue(name.getName().length() > 0);
        }
    }

    @Test
    public void testNaaccrField() throws IOException {
        NaaccrField name = SeerApi.connect().naaccrField("latest", 521);

        Assert.assertNotNull(name);
        Assert.assertEquals(521, name.getItem().longValue());
        Assert.assertEquals(550, name.getStart().longValue());
        Assert.assertEquals(554, name.getEnd().longValue());

        Assert.assertEquals(2, name.getSubFields().size());
        Assert.assertEquals(522, name.getSubFields().get(0).getItem().longValue());
        Assert.assertEquals(523, name.getSubFields().get(1).getItem().longValue());
    }
}
