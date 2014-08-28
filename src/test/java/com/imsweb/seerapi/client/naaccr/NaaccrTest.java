/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class NaaccrTest {

    @Test
    public void testNaaccrVersions() throws IOException {
        List<NaaccrVersion> versions = SeerApi.connect().naaccrVersions();

        Assert.assertTrue(versions.size() > 0);
        for (NaaccrVersion version : versions) {
            Assert.assertTrue(version.getVersion().length() > 0);
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
        Assert.assertEquals("Morph--Type&Behav ICD-O-3", name.getName());
        Assert.assertEquals("LEFT", name.getAlign());
        Assert.assertEquals(" ", name.getPadChar());
        Assert.assertTrue(name.getDocumentation().startsWith("<table class=\"naaccr-summary-table naaccr-borders\">"));
        Assert.assertEquals(521, name.getItem().longValue());
        Assert.assertEquals(550, name.getStart().longValue());
        Assert.assertEquals(554, name.getEnd().longValue());

        Assert.assertEquals(2, name.getSubFields().size());
        Assert.assertEquals(522, name.getSubFields().get(0).getItem().longValue());
        Assert.assertEquals(523, name.getSubFields().get(1).getItem().longValue());

        NaaccrSubField sub = name.getSubFields().get(0);
        Assert.assertEquals("Histologic Type ICD-O-3", sub.getName());
        Assert.assertEquals(550, sub.getStart().longValue());
        Assert.assertEquals(553, sub.getEnd().longValue());
        Assert.assertEquals("LEFT", sub.getAlign());
        Assert.assertEquals(" ", sub.getPadChar());
    }

}
