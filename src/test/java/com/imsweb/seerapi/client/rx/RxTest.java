/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.publishable.PublishableSearch;

public class RxTest {

    private static RxService _RX;

    @BeforeClass
    public static void setup() {
        _RX = new SeerApi.Builder().connect().rx();
    }

    @Test
    public void testRxVersions() throws IOException {
        List<RxVersion> versions = _RX.versions().execute().body();

        Assert.assertTrue(versions.size() > 0);
        for (RxVersion version : versions) {
            Assert.assertTrue(version.getName().length() > 0);
            Assert.assertTrue(version.getType().length() > 0);
            Assert.assertNotNull(version.getLastModified());
        }
    }

    @Test
    public void testRxById() throws IOException {
        Rx rx = _RX.getById("latest", "53c44afe102c1290262dc672").execute().body();

        Assert.assertNotNull(rx);
        Assert.assertEquals("ABT-751", rx.getName());
        Assert.assertTrue(rx.getAlternateName().size() > 1);
        Assert.assertEquals(Rx.Type.DRUG, rx.getType());
        Assert.assertNull(rx.getHistology());
        Assert.assertTrue(rx.getRemarks().startsWith("Phase II ALL"));
        Assert.assertNull(rx.getEvsId());
        Assert.assertNull(rx.getAbbreviation());
        Assert.assertEquals(Collections.singletonList("Chemotherapy"), rx.getCategory());
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
        Assert.assertNull(rx.getHistory());
    }

    @Test
    public void testRxChangelog() throws IOException {
        RxChangelogResults results = _RX.changelogs("latest", "2015-08-30", "2015-09-30", 1).execute().body();

        Assert.assertNotNull(results);

        List<RxChangelog> changes = results.getChangelogs();

        Assert.assertEquals(1, changes.size());
        Assert.assertNotNull(changes.get(0).getUser());
        Assert.assertEquals("latest", changes.get(0).getVersion());

        RxChangelog changelog = changes.get(0);

        Assert.assertNotNull(changelog.getUser());
        Assert.assertEquals("latest", changelog.getVersion());
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
        RxSearchResults results = _RX.search("latest", search.paramMap()).execute().body();

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(8, results.getTotal().longValue());
        Assert.assertEquals(8, results.getResults().size());
        Assert.assertEquals(Collections.singletonList("abt"), results.getTerms());

        search.setMode(PublishableSearch.SearchMode.OR);
        search.setStatus("TEST");
        search.setAssignedTo("user");
        search.setModifiedFrom("2014-01-01");
        search.setModifiedTo("2014-05-31");
        search.setPublishedFrom("2014-01-01");
        search.setPublishedTo("2014-05-31");
        search.setCount(100);
        search.setOffset(0);
        search.setOrderBy("name");
        search.setOutputType(PublishableSearch.OutputType.MIN);
        search.setDoNotCode(Rx.DoNoCodeValue.YES);
        results = _RX.search("latest", search.paramMap(), new HashSet<>(Collections.singletonList("category"))).execute().body();

        Assert.assertNotNull(results);
        Assert.assertEquals(100, results.getCount().longValue());
        Assert.assertEquals(0, results.getTotal().longValue());
        Assert.assertNull(results.getResults());
    }

    @Test
    public void testRxSearchIterate() throws IOException {
        RxSearch search = new RxSearch();
        search.setOutputType(PublishableSearch.OutputType.FULL);
        search.setCount(100);
        search.setOffset(0);

        Integer total = null;

        while (total == null || search.getOffset() < total) {
            RxSearchResults results = _RX.search("latest", search.paramMap()).execute().body();
            Assert.assertNotNull(results);
            Assert.assertTrue(results.getTotal() > 0);
            Assert.assertTrue(results.getResults().size() > 0);

            // the first time through, set the total
            if (total == null)
                total = results.getTotal();

            search.setOffset(search.getOffset() + results.getResults().size());
        }
    }
}
