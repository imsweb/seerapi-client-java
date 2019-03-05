/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

import static org.assertj.core.api.Assertions.assertThat;


public class NaaccrTest {

    private static NaaccrService _NAACCR;

    @BeforeClass
    public static void setup() {
        _NAACCR = new SeerApi.Builder().connect().naaccr();
    }

    @Test
    public void testNaaccrVersions() throws IOException {
        List<NaaccrVersion> versions = _NAACCR.versions().execute().body();

        assertThat(versions).isNotNull().isNotEmpty();
        for (NaaccrVersion version : versions) {
            assertThat(version.getVersion()).isNotEmpty();
            assertThat(version.getName()).isNotEmpty();
            assertThat(version.getLength()).isGreaterThanOrEqualTo(22824);
            assertThat(version.getDescription()).isNotEmpty();
            assertThat(version.getStyle()).isNotEmpty();
        }
    }

    @Test
    public void testNaaccrFieldNames() throws IOException {
        List<NaaccrFieldName> names = _NAACCR.fieldNames("latest").execute().body();

        assertThat(names).isNotEmpty();
        for (NaaccrFieldName name : names) {
            assertThat(name.getItem()).isGreaterThan(0);
            assertThat(name.getName()).isNotEmpty();
        }
    }

    @Test
    public void testNaaccrField() throws IOException {
        NaaccrField name = _NAACCR.field("16", 521).execute().body();

        assertThat(name).isNotNull();
        assertThat(name.getName()).isEqualTo("Morph--Type&Behav ICD-O-3");
        assertThat(name.getSection()).isEqualTo("Cancer Identification");
        assertThat(name.getAlign()).isEqualTo("LEFT");
        assertThat(name.getPadChar()).isEqualTo(" ");
        assertThat(name.getDocumentation()).startsWith("<table class=\"naaccr-summary-table naaccr-borders\">");
        assertThat(name.getItem()).isEqualTo(521);
        assertThat(name.getStart()).isEqualTo(550);
        assertThat(name.getEnd()).isEqualTo(554);

        assertThat(name.getSubFields()).hasSize(2);
        assertThat(name.getSubFields()).extracting("item").contains(522, 523);

        NaaccrSubField sub = name.getSubFields().get(0);
        assertThat(sub.getName()).isEqualTo("Histologic Type ICD-O-3");
        assertThat(sub.getStart()).isEqualTo(550);
        assertThat(sub.getEnd()).isEqualTo(553);
        assertThat(sub.getAlign()).isEqualTo("LEFT");
        assertThat(sub.getPadChar()).isEqualTo(" ");

        // test one with default value
        NaaccrField recordID = _NAACCR.field("18", 10).execute().body();
        assertThat(recordID.getName()).isEqualTo("Record Type");
        assertThat(recordID.getSection()).isEqualTo("Record ID");
        assertThat(recordID.getDefaultValue()).isEqualTo("A");
    }

}
