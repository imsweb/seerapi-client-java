/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.shared.Version;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SurgeryTest {

    private static SurgeryService _SURGERY;

    @BeforeClass
    public static void setup() {
        _SURGERY = new SeerApi.Builder().connect().surgery();
    }

    @Test
    public void testSiteSpecificSurgeryVersions() throws IOException {
        List<Version> versions = _SURGERY.versions().execute().body();

        assertTrue(versions.size() > 0);
        for (Version version : versions) {
            assertTrue(version.getVersion().length() > 0);
            assertTrue(version.getCount() > 0);
        }
    }

    @Test
    public void testSiteSpecificSurgeryTables() throws IOException {
        List<String> titles = _SURGERY.tables("2014").execute().body();

        assertTrue(titles.size() > 0);
        assertTrue(titles.contains("Oral Cavity"));
    }

    @Test
    public void testSiteSpecificSurgeryTable() throws IOException {
        SurgeryTable table = _SURGERY.table("2014", "Oral Cavity", null, null).execute().body();

        assertNotNull(table);
        assertEquals("Oral Cavity", table.getTitle());
        assertNotNull(table.getSiteInclusions());
        assertNotNull(table.getHistExclusions());
        assertNull(table.getHistInclusions());
        assertNotNull(table.getPreNote());
        assertNull(table.getPostNote());
        SurgeryRow row = table.getRows().get(0);
        assertEquals("00", row.getCode());
        assertNotNull(row.getDescription());
        assertEquals(Integer.valueOf(0), row.getLevel());
        assertFalse(row.getLineBreak());

        table = _SURGERY.table("2014", null, "C001", "8000").execute().body();
        assertEquals("Oral Cavity", table.getTitle());
    }
}
