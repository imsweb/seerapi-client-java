/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.io.IOException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;

public class SurgeryTest {

    private static SurgeryService _SURGERY;

    @BeforeClass
    public static void setup() {
        _SURGERY = new SeerApi.Builder().connect().surgery();
    }

    @Test
    public void testSiteSpecificSurgeryTables() throws IOException {
        List<String> titles = _SURGERY.tables("2014").execute().body();

        assertThat(titles).isNotEmpty().containsAnyOf("Oral Cavity");
    }

    @Test
    public void testSiteSpecificSurgeryTable() throws IOException {
        SurgeryTable table = _SURGERY.table("2014", "Oral Cavity", null, null).execute().body();

        assertThat(table).isNotNull();
        assertThat(table.getTitle()).isEqualTo("Oral Cavity");
        assertThat(table.getSiteInclusions()).isNotNull();
        assertThat(table.getHistExclusions()).isNotNull();
        assertThat(table.getHistInclusions()).isNull();
        assertThat(table.getPreNote()).isNotNull();
        assertThat(table.getPostNote()).isNull();

        SurgeryRow row = table.getRows().get(0);
        assertThat(row.getCode()).isEqualTo("00");
        assertThat(row.getDescription()).isNotNull();
        assertThat(row.getLevel()).isEqualTo(Integer.valueOf(0));
        assertThat(row.getLineBreak()).isFalse();

        table = _SURGERY.table("2014", null, "C001", "8000").execute().body();
        assertThat(table).isNotNull();
        assertThat(table.getTitle()).isEqualTo("Oral Cavity");
    }

    @Test
    public void testBeans() {
        MatcherAssert.assertThat(SurgeryTable.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(SurgeryRow.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }
}
