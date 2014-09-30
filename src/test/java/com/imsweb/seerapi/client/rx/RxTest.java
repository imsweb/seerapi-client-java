/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class RxTest {

    @Test
    public void testRxVersions() throws IOException {
        List<RxVersion> versions = SeerApi.connect().rxVersions();

        Assert.assertTrue(versions.size() > 0);
        for (RxVersion version : versions) {
            Assert.assertTrue(version.getName().length() > 0);
            Assert.assertTrue(version.getType().length() > 0);
            Assert.assertNotNull(version.getLastModified());
        }
    }

    @Test
    public void testRxById() throws IOException {
        Rx rx = SeerApi.connect().rxById("latest", "53c44afe102c1290262dc672");

        Assert.assertNotNull(rx);
        Assert.assertEquals("ABT-751", rx.getName());
        Assert.assertTrue(rx.getAlternateName().size() > 1);
        Assert.assertEquals(Rx.Type.DRUG, rx.getType());
        Assert.assertNull(rx.getHistology());
        Assert.assertTrue(rx.getRemarks().startsWith("Phase II ALL"));
        Assert.assertNull(rx.getEvsId());
        Assert.assertNull(rx.getAbbreviation());
        Assert.assertEquals(Arrays.asList("Chemotherapy"), rx.getCategory());
        Assert.assertTrue(rx.getSubcategory().size() > 0);
        Assert.assertNull(rx.getNscNumber());
        Assert.assertNull(rx.getDrugs());
        Assert.assertNull(rx.getRadiation());
        Assert.assertNull(rx.getHidden());
        Assert.assertNull(rx.getStatus());
        Assert.assertNull(rx.getAssignedTo());
        Assert.assertNotNull(rx.getFirstPublished());
        Assert.assertNotNull(rx.getLastModified());
        Assert.assertNotNull(rx.getFingerprint());
        Assert.assertNull(rx.getNote());
        Assert.assertNull(rx.getFieldNotes());
        Assert.assertNull(rx.getScore());
        Assert.assertEquals(Arrays.asList("neuroblastoma", "Pediatric ALL"), rx.getPrimarySite());
    }

    @Test
    public void testRxChangelog() throws IOException {
        List<RxChangelog> changes = SeerApi.connect().rxChangelogs("latest", null, null, 1);

        Assert.assertNotNull(changes);
        Assert.assertEquals(1, changes.size());
        Assert.assertNotNull(changes.get(0).getUser());
        Assert.assertEquals("latest", changes.get(0).getVersion());
    }

    @Test
    public void testRxSearch() throws IOException {
        RxSearch search = new RxSearch();

        search.setQuery("abt");
        search.setType(Rx.Type.DRUG);
        RxSearchResults results = SeerApi.connect().rxSearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(7, results.getCount().longValue());
        Assert.assertEquals(7, results.getResults().size());
        Assert.assertEquals(Arrays.asList("abt"), results.getTerms());
    }
}
