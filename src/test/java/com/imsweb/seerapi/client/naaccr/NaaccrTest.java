/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.imsweb.seerapi.client.SeerApi;

import static org.assertj.core.api.Assertions.assertThat;

class NaaccrTest {

    private static NaaccrService _NAACCR;

    @BeforeAll
    public static void setup() {
        _NAACCR = new SeerApi.Builder().connect().naaccr();
    }

    @Test
    void testNaaccrFlatVersions() throws IOException {
        List<NaaccrVersion> versions = _NAACCR.flatVersions().execute().body();

        assertThat(versions).isNotNull().isNotEmpty();
        for (NaaccrVersion version : versions) {
            assertThat(version.getVersion()).isNotEmpty();
            assertThat(version.getName()).isNotEmpty();
            assertThat(version.getLength()).isGreaterThanOrEqualTo(22824);
            assertThat(version.getDescription()).isNull();
            assertThat(version.getStyle()).isNotEmpty();
        }
    }

    @Test
    void testNaaccrXmlVersions() throws IOException {
        List<NaaccrVersion> versions = _NAACCR.xmlVersions().execute().body();

        assertThat(versions).isNotNull().isNotEmpty();
        for (NaaccrVersion version : versions) {
            assertThat(version.getVersion()).isNotEmpty();
            assertThat(version.getName()).isNotEmpty();
            assertThat(version.getLength()).isNull();
            assertThat(version.getDescription()).isNull();
            assertThat(version.getStyle()).isNotEmpty();
            assertThat(version.getDictionaryUri()).isNotEmpty();
            assertThat(version.getDictionaryDescription()).isNotEmpty();
            assertThat(version.getSpecificationVersion()).isNotEmpty();
        }
    }

    @Test
    void testNaaccrFlatFieldNames() throws IOException {
        List<NaaccrFieldName> names = _NAACCR.flatFieldNames("latest").execute().body();

        assertThat(names).isNotNull();
        for (NaaccrFieldName name : names) {
            assertThat(name.getItemNum()).isPositive();
            assertThat(name.getName()).isNotEmpty();
        }
    }

    @Test
    void testNaaccrXmlFieldNames() throws IOException {
        List<NaaccrFieldName> names = _NAACCR.xmlFieldNames("latest").execute().body();

        assertThat(names).isNotNull();
        for (NaaccrFieldName name : names) {
            assertThat(name.getNaaccrId()).isNotEmpty();
            assertThat(name.getItemNum()).isPositive();
            assertThat(name.getName()).isNotEmpty();
        }
    }

    @Test
    void testNaaccrFlatField() throws IOException {
        NaaccrFlatField name = _NAACCR.flatField("16", 521).execute().body();

        assertThat(name).isNotNull();
        assertThat(name.getItemNum()).isEqualTo(521);
        assertThat(name.getNaaccrId()).isEqualTo("morphTypebehavIcdO3");
        assertThat(name.getName()).isEqualTo("Morph--Type&Behav ICD-O-3");
        assertThat(name.getSection()).isEqualTo("Cancer Identification");
        assertThat(name.getAlign()).isEqualTo("LEFT");
        assertThat(name.getPadChar()).isEqualTo(" ");
        assertThat(name.getDocumentation()).startsWith("<table class=\"naaccr-summary-table naaccr-borders\">");
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
        NaaccrFlatField recordID = _NAACCR.flatField("18", 10).execute().body();
        assertThat(recordID).isNotNull();
        assertThat(recordID.getName()).isEqualTo("Record Type");
        assertThat(recordID.getSection()).isEqualTo("Record ID");
        assertThat(recordID.getDefaultValue()).isEqualTo("A");
    }

    @Test
    void testNaaccrXmlField() throws IOException {
        NaaccrXmlField name = _NAACCR.xmlField("21", "phase2RadiationExternalBeamTech").execute().body();

        assertThat(name).isNotNull();
        assertThat(name.getNaaccrId()).isEqualTo("phase2RadiationExternalBeamTech");
        assertThat(name.getItemNum()).isEqualTo(1512);
        assertThat(name.getName()).isEqualTo("Phase II Radiation External Beam Planning Tech");
        assertThat(name.getSection()).isEqualTo("Treatment-1st Course");
        assertThat(name.getParentXmlElement()).isEqualTo("Tumor");
        assertThat(name.getRecordTypes()).containsExactly("A", "M", "C", "I");
        assertThat(name.getDataType()).isEqualTo("digits");
        assertThat(name.getLength()).isEqualTo(2);
        assertThat(name.getPadType()).isEqualTo("rightBlank");
        assertThat(name.getTrimType()).isEqualTo("all");
        assertThat(name.getAllowUnlimitedText()).isFalse();
        assertThat(name.getDocumentation()).isNotEmpty();
    }

    // these two tests are slow so don't run them all the time; they verify that all the items from flat and NAACR can be read without error

    @Disabled("Slow test so do not run all the time")
    public void loadAllFlat() throws IOException {
        for (NaaccrFieldName name : Objects.requireNonNull(_NAACCR.flatFieldNames("latest").execute().body())) {
            NaaccrFlatField field = _NAACCR.flatField("latest", name.getItemNum()).execute().body();

            assertThat(field).isNotNull();
        }
    }

    @Disabled("Slow test so do not run all the time")
    public void loadAllXml() throws IOException {
        for (NaaccrFieldName name : Objects.requireNonNull(_NAACCR.xmlFieldNames("latest").execute().body())) {
            NaaccrXmlField field = _NAACCR.xmlField("latest", name.getNaaccrId()).execute().body();

            assertThat(field).isNotNull();
        }
    }
}
