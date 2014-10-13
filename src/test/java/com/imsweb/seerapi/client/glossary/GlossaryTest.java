/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class GlossaryTest {

    @Test
    public void testGlossaryCategory() {
        Assert.assertEquals(Glossary.Category.SOLID_TUMOR, Glossary.Category.valueOf("SOLID_TUMOR"));
    }

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
        GlossarySearchResults results = SeerApi.connect().glossarySearch("latest_dev", new GlossarySearch("stem"));
        Assert.assertTrue(results.getCount() > 0);

        Glossary glossary = SeerApi.connect().glossaryById("latest_dev", results.getResults().get(0).getId());

        Assert.assertNotNull(glossary);
        Assert.assertEquals("Brain stem", glossary.getName());
        Assert.assertEquals(Arrays.asList(Glossary.Category.SOLID_TUMOR), glossary.getCategories());
        Assert.assertEquals(Arrays.asList("C717"), glossary.getPrimarySite());

        Assert.assertNull(glossary.getHistology());
        Assert.assertNull(glossary.getAbstractorNote());
        Assert.assertEquals(1, glossary.getAlternateName().size());
        Assert.assertTrue(glossary.getDefinition().startsWith("The brain stem is the posterior part of the brain"));
        Assert.assertTrue(glossary.getHistory().size() >= 1);

        GlossaryHistoryEvent event = glossary.getHistory().get(0);
        Assert.assertEquals("mayc@imsweb.com", event.getUser());
        Assert.assertNotNull(event.getDate());
        Assert.assertNull(event.getOld());
        Assert.assertNull(event.getNew());
    }

    @Test
    public void testGlossaryChangelog() throws IOException {
        List<GlossaryChangelog> changes = SeerApi.connect().glossaryChangelogs("latest", null, null, 1);

        Assert.assertNotNull(changes);

        // TODO since all the glossary items were removed from the production database, this needs to be commented out; it will return when they are published again

        /*
        Assert.assertEquals(1, changes.size());

        GlossaryChangelog changelog = changes.get(0);

        Assert.assertNotNull(changelog.getUser());
        Assert.assertEquals("latest_dev", changelog.getVersion());
        Assert.assertTrue(changelog.getId().length() > 0);
        Assert.assertTrue(changelog.getAdds().size() > 0);

        GlossaryChangelogEntry entry = changelog.getAdds().get(0);
        Assert.assertTrue(entry.getId().length() > 0);
        Assert.assertTrue(entry.getName().length() > 0);
        Assert.assertNull(entry.getOldVersion());
        Assert.assertNull(entry.getNewVersion());

        Assert.assertNull(changelog.getMods());
        Assert.assertNull(changelog.getDeletes());
        Assert.assertNotNull(changelog.getDate());
        Assert.assertEquals("Initial migration", changelog.getDescription());
        */
    }

    @Test
    public void testGlossarySearch() throws IOException {
        GlossarySearch search = new GlossarySearch("stem");

        GlossarySearchResults results = SeerApi.connect().glossarySearch("latest_dev", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(21, results.getTotal().longValue());
        Assert.assertEquals(21, results.getResults().size());
        Assert.assertEquals(Arrays.asList("stem"), results.getTerms());

        // add the category and verify there are no results
        search.setCategory(EnumSet.of(Glossary.Category.GENERAL));
        results = SeerApi.connect().glossarySearch("latest_dev", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(0, results.getTotal().longValue());
        Assert.assertEquals(0, results.getResults().size());

        // add a second category and verify there are we get the results again
        search.setCategory(EnumSet.of(Glossary.Category.GENERAL, Glossary.Category.SOLID_TUMOR));
        results = SeerApi.connect().glossarySearch("latest_dev", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(21, results.getTotal().longValue());
        Assert.assertEquals(21, results.getResults().size());
        Assert.assertEquals(Arrays.asList("stem"), results.getTerms());
    }

}
