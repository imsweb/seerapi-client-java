/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApiBuilder;
import com.imsweb.seerapi.client.shared.Version;

public class SurgeryTest {

    private static SurgeryService _SURGERY;

    @BeforeClass
    public static void setup() {
        _SURGERY = new SeerApiBuilder().connect().surgery();
    }

    @Test
    public void testSiteSpecificSurgeryVersions() throws IOException {
        List<Version> versions = _SURGERY.versions();

        Assert.assertTrue(versions.size() > 0);
        for (Version version : versions) {
            Assert.assertTrue(version.getVersion().length() > 0);
            Assert.assertTrue(version.getCount() > 0);
        }
    }

    @Test
    public void testSiteSpecificSurgeryTables() throws IOException {
        List<String> titles = _SURGERY.tables("2014");

        Assert.assertTrue(titles.size() > 0);
        Assert.assertTrue(titles.contains("Oral Cavity"));
    }

    @Test
    public void testSiteSpecificSurgeryTable() throws IOException {
        SurgeryTable table = _SURGERY.table("2014", "Oral Cavity", null, null);

        Assert.assertNotNull(table);
        Assert.assertEquals("Oral Cavity", table.getTitle());
        Assert.assertNotNull(table.getSiteInclusions());
        Assert.assertNotNull(table.getHistExclusions());
        Assert.assertNull(table.getHistInclusions());
        Assert.assertNotNull(table.getPreNote());
        Assert.assertNull(table.getPostNote());
        SurgeryRow row = table.getRows().get(0);
        Assert.assertEquals("00", row.getCode());
        Assert.assertNotNull(row.getDescription());
        Assert.assertEquals(Integer.valueOf(0), row.getLevel());
        Assert.assertFalse(row.getLineBreak());

        table = _SURGERY.table("2014", null, "C001", "8000");
        Assert.assertEquals("Oral Cavity", table.getTitle());
    }
}
