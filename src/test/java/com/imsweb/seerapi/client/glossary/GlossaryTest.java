/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class GlossaryTest {

    @Test
    public void testGlossaryVersions() throws IOException {
        List<GlossaryVersion> versions = SeerApi.connect().glossaryVersions();

        Assert.assertTrue(versions.size() > 0);
        for (GlossaryVersion version : versions) {
            Assert.assertTrue(version.getName().length() > 0);
            Assert.assertTrue(version.getType().length() > 0);
            Assert.assertNotNull(version.getLastModified());
        }
    }

    @Test
    public void testGlossaryById() throws IOException {
        Glossary glossary = SeerApi.connect().glossaryById("latest", "51f6cf85e3e2d4d91c7a7296");

        Assert.assertNotNull(glossary);
        Assert.assertEquals("Brain stem", glossary.getName());
        Assert.assertEquals(Arrays.asList(Glossary.Category.SOLID_TUMOR), glossary.getCategories());
        Assert.assertEquals(Arrays.asList("C717"), glossary.getPrimarySite());
    }

    @Test
    public void testGlossaryChangelog() throws IOException {
        List<GlossaryChangelog> changes = SeerApi.connect().glossaryChangelogs("latest", null, null, 1);

        Assert.assertNotNull(changes);
        Assert.assertEquals(1, changes.size());
        Assert.assertNotNull(changes.get(0).getUser());
        Assert.assertEquals("latest", changes.get(0).getVersion());
    }

    @Test
    public void testGlossarySearch() throws IOException {
        GlossarySearch search = new GlossarySearch();

        search.setQuery("stem");
        GlossarySearchResults results = SeerApi.connect().glossarySearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.getCount().longValue());
        Assert.assertEquals(2, results.getResults().size());
        Assert.assertEquals(Arrays.asList("stem"), results.getTerms());
    }

}
