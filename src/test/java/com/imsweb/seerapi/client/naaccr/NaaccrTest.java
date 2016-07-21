/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NaaccrTest {

    private static NaaccrService _NAACCR;

    @BeforeClass
    public static void setup() {
        _NAACCR = new SeerApi.Builder().connect().naaccr();
    }

    @Test
    public void testNaaccrVersions() throws IOException {
        List<NaaccrVersion> versions = _NAACCR.versions().execute().body();

        assertTrue(versions.size() > 0);
        for (NaaccrVersion version : versions) {
            assertTrue(version.getVersion().length() > 0);
            assertTrue(version.getName().length() > 0);
            assertEquals(22824, version.getLength().longValue());
            assertTrue(version.getDescription().length() > 0);
            assertTrue(version.getStyle().length() > 0);
        }
    }

    @Test
    public void testNaaccrFieldNames() throws IOException {
        List<NaaccrFieldName> names = _NAACCR.fieldNames("latest").execute().body();

        assertTrue(names.size() > 0);
        for (NaaccrFieldName name : names) {
            assertTrue(name.getItem() > 0);
            assertTrue(name.getName().length() > 0);
        }
    }

    @Test
    public void testNaaccrField() throws IOException {
        NaaccrField name = _NAACCR.field("latest", 521).execute().body();

        assertNotNull(name);
        assertEquals("Morph--Type&Behav ICD-O-3", name.getName());
        assertEquals("LEFT", name.getAlign());
        assertEquals(" ", name.getPadChar());
        assertTrue(name.getDocumentation().startsWith("<table class=\"naaccr-summary-table naaccr-borders\">"));
        assertEquals(521, name.getItem().longValue());
        assertEquals(550, name.getStart().longValue());
        assertEquals(554, name.getEnd().longValue());

        assertEquals(2, name.getSubFields().size());
        assertEquals(522, name.getSubFields().get(0).getItem().longValue());
        assertEquals(523, name.getSubFields().get(1).getItem().longValue());

        NaaccrSubField sub = name.getSubFields().get(0);
        assertEquals("Histologic Type ICD-O-3", sub.getName());
        assertEquals(550, sub.getStart().longValue());
        assertEquals(553, sub.getEnd().longValue());
        assertEquals("LEFT", sub.getAlign());
        assertEquals(" ", sub.getPadChar());
    }

}
