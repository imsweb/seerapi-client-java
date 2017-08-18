/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import retrofit2.Response;

import com.imsweb.seerapi.client.SeerApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        assertEquals("20021126", product.getStartMarketingDate());
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

        assertEquals(1, product.getPackages().size());
        assertEquals("30", product.getPackages().get(0).getCode());
        assertEquals("30 CAPSULE in 1 BOTTLE (0002-3227-30)", product.getPackages().get(0).getDescription());

        assertNotNull(product.getDateAdded());
        assertNotNull(product.getDateModified());
        assertNull(product.getDateRemoved());
    }

    @Test
    public void testNdcSearch() throws IOException {
        NdcSearch search = new NdcSearch();
        search.setIncludeRemoved(true);

        Response<List<NdcProduct>> response = _NDC.search(search.paramMap()).execute();

        // hold onto total number (including "removed")
        Integer totalIncludingRemoved = Integer.valueOf(response.headers().get("X-Total-Count"));
        assertTrue(totalIncludingRemoved > 100000);

        List<NdcProduct> products = response.body();
        assertEquals(25, products.size());

        search.setQuery("daklinza");
        products = _NDC.search(search.paramMap()).execute().body();
        assertTrue(products.size() > 1);

        search.setRemovedSince("2016-07-21");
        products = _NDC.search(search.paramMap()).execute().body();
        assertEquals(0, products.size());

        // test removed
        search = new NdcSearch();
        search.setIncludeRemoved(false);
        response = _NDC.search(search.paramMap()).execute();
        Integer totalExcludingRemoved = Integer.valueOf(response.headers().get("X-Total-Count"));

        assertTrue(totalIncludingRemoved > totalExcludingRemoved);
    }

}
