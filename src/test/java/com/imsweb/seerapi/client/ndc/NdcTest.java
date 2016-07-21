/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class NdcTest {

    private static NdcService _NDC;

    @BeforeClass
    public static void setup() {
        _NDC = new SeerApi.Builder().connect().ndc();
    }

    @Test
    public void testNdcByCode() throws IOException {
        NdcProduct product = _NDC.getByCode("0002-3227").execute().body();

        assertNotNull(product);
        assertEquals("0002-3227", product.getNdc());
        assertEquals("HUMAN PRESCRIPTION DRUG", product.getTypeName());
        assertEquals("Strattera", product.getProprietaryName());
        assertNull(product.getProprietaryNameSuffix());
        assertEquals(Collections.singletonList("Atomoxetine hydrochloride"), product.getNonProprietaryName());
        assertEquals("CAPSULE", product.getDosageFormName());
        assertEquals(Collections.singletonList("ORAL"), product.getRouteName());
        assertEquals("20030110", product.getStartMarketingDate());
        assertNull(product.getEndMarketingDate());
        assertEquals("NDA", product.getMarketingCategoryName());
        assertEquals("NDA021411", product.getApplicationNumber());
        assertEquals("Eli Lilly and Company", product.getLabelerName());
        assertNull(product.getDeaSchedule());
        assertEquals(1, product.getSubstances().size());

        NdcSubstance substance = product.getSubstances().get(0);
        assertEquals("ATOMOXETINE HYDROCHLORIDE", substance.getName());
        assertEquals("10", substance.getStrengthNumber());
        assertEquals("mg/1", substance.getStrengthUnit());

        assertEquals(Arrays.asList("Norepinephrine Reuptake Inhibitor [EPC]", "Norepinephrine Uptake Inhibitors [MoA]"), product.getPharmClass());

        assertEquals(2, product.getPackages().size());
        assertEquals("07", product.getPackages().get(0).getCode());
        assertEquals("7 CAPSULE in 1 BOTTLE (0002-3227-07)", product.getPackages().get(0).getDescription());
        assertEquals("30", product.getPackages().get(1).getCode());
        assertEquals("30 CAPSULE in 1 BOTTLE (0002-3227-30)", product.getPackages().get(1).getDescription());

        assertNotNull(product.getDateAdded());
        assertNotNull(product.getDateModified());
        assertNull(product.getDateRemoved());
        assertNull(product.getScore());
    }

}
