/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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
    void testVersions() throws IOException {
        List<NaaccrVersion> versions = _NAACCR.versions().execute().body();

        assertThat(versions).isNotNull().isNotEmpty();
        for (NaaccrVersion version : versions) {
            assertThat(version.getVersion()).isNotEmpty();
            assertThat(version.getYearImplemented()).isGreaterThan(2020);
            assertThat(version.getDateOfPublication()).isNotNull();
        }
    }

    @Test
    void testNaaccrFieldNames() throws IOException {
        List<NaaccrItemInfo> names = _NAACCR.fieldNames("latest").execute().body();

        assertThat(names).isNotNull();
        for (NaaccrItemInfo name : names) {
            assertThat(name.getItem()).isNotEmpty();
            assertThat(name.getName()).isNotEmpty();
        }
    }

    @Test
    void testNaaccrItem() throws IOException {
        validateItem(_NAACCR.item("25", "80").execute().body());
        validateItem(_NAACCR.item("25", "addrAtDxState").execute().body());
    }

    private void validateItem(NaaccrItem item) {
        assertThat(item).isNotNull();
        assertThat(item.getItemNumber()).isEqualTo("80");
        assertThat(item.getItemName()).isEqualTo("Addr at DX--State");
        assertThat(item.getItemDataType()).isEqualTo("alpha");
        assertThat(item.getItemLength()).isEqualTo("2");
        assertThat(item.getYearImplemented()).isEqualTo("2011");
        assertThat(item.getVersionImplemented()).isEqualTo("12.2");
        assertThat(item.getXmlNaaccrId()).isEqualTo("addrAtDxState");
        assertThat(item.getXmlParentId()).isEqualTo("Tumor");
        assertThat(item.getRecordTypes()).containsExactlyInAnyOrder("A", "C", "I", "M");
        assertThat(item.getSection()).isEqualTo("Demographic");
        assertThat(item.getSourceOfStandard()).isEqualTo("CoC");
        assertThat(item.getDateCreated()).isInThePast();
        assertThat(item.getDateModified()).isInThePast();
        assertThat(item.getDescription()).isNotEmpty();
        assertThat(item.getRationale()).isNotEmpty();
        assertThat(item.getNpcrCollect()).isEqualTo("Required");
        assertThat(item.getCocCollect()).isEqualTo("Required");
        assertThat(item.getSeerCollect()).isEqualTo("Required");
        assertThat(item.getCccrCollect()).isEqualTo("No recommendation");
        assertThat(item.getAlternateNames()).containsExactlyInAnyOrder("State at Diagnosis (CoC)", "State (pre-96 CoC)");
        assertThat(item.getFormat()).isEqualTo("Upper case");
        assertThat(item.getCodeDescription()).isEqualTo("In addition to USPS abbreviations");
        assertThat(item.getAllowableValues()).isEqualTo("Refer to EDITS table STATE.DBF in Appendix B; CD, US, XX, YY, ZZ");
        assertThat(item.getAllowedCodes())
                .hasSize(5)
                .allMatch(allowedCode -> allowedCode.getDescription() != null)
                .extracting("code")
                .containsExactlyInAnyOrder("CD", "US", "XX", "YY", "ZZ");
    }

}
