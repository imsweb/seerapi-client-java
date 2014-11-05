/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.publishable.PublishableSearch;

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

        Assert.assertEquals(1, rx.getHistory().size());

        RxHistoryEvent event = rx.getHistory().get(0);
        Assert.assertEquals("cyrj@imsweb.com", event.getUser());
        Assert.assertNotNull(event.getDate());
        Assert.assertNull(event.getOld());
        Assert.assertNull(event.getNew());
    }

    @Test
    public void testRxChangelog() throws IOException {
        List<RxChangelog> changes = SeerApi.connect().rxChangelogs("latest", "2013-07-30", null, 1);

        Assert.assertNotNull(changes);
        Assert.assertEquals(1, changes.size());
        Assert.assertNotNull(changes.get(0).getUser());
        Assert.assertEquals("latest", changes.get(0).getVersion());

        RxChangelog changelog = changes.get(0);

        Assert.assertNotNull(changelog.getUser());
        Assert.assertEquals("latest", changelog.getVersion());
        Assert.assertTrue(changelog.getId().length() > 0);
        Assert.assertNull(changelog.getAdds());
        Assert.assertTrue(changelog.getMods().size() > 0);
        Assert.assertNull(changelog.getDeletes());
        Assert.assertNotNull(changelog.getDate());
        Assert.assertNull(changelog.getDescription());

        RxChangelogEntry entry = changelog.getMods().get(0);
        Assert.assertTrue(entry.getId().length() > 0);
        Assert.assertTrue(entry.getName().length() > 0);
        Assert.assertNotNull(entry.getOldVersion());
        Assert.assertNotNull(entry.getNewVersion());
    }

    @Test
    public void testRxSearch() throws IOException {
        RxSearch search = new RxSearch("abt", Rx.Type.DRUG);
        RxSearchResults results = SeerApi.connect().rxSearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(7, results.getTotal().longValue());
        Assert.assertEquals(7, results.getResults().size());
        Assert.assertEquals(Arrays.asList("abt"), results.getTerms());

        search.setMode(PublishableSearch.SearchMode.OR);
        search.setStatus("TEST");
        search.setAssignedTo("user");
        search.setModifiedFrom("2014-01-01");
        search.setModifiedTo("2014-05-31");
        search.setPublishedFrom("2014-01-01");
        search.setPublishedTo("2014-05-31");
        search.setBeenPublished(true);
        search.setHidden(false);
        search.setCount(100);
        search.setOffset(0);
        search.setOrderBy("name");
        search.setOutputType(PublishableSearch.OutputType.MIN);
        search.setCategory(new HashSet<String>(Arrays.asList("category")));
        search.setDoNotCode(true);
        results = SeerApi.connect().rxSearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(100, results.getCount().longValue());
        Assert.assertEquals(0, results.getTotal().longValue());
        Assert.assertEquals(0, results.getResults().size());

    }
}
