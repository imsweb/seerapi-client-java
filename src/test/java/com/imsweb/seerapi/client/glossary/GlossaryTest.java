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
import com.imsweb.seerapi.client.glossary.Glossary.Category;

public class GlossaryTest {

    @Test
    public void testGlossaryCategory() {
        Assert.assertEquals(Category.SOLID_TUMOR, Category.valueOf("SOLID_TUMOR"));
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
        GlossarySearchResults results = SeerApi.connect().glossarySearch("latest", new GlossarySearch("Lymphangiogram"));
        Assert.assertTrue(results.getCount() > 0);

        Glossary glossary = SeerApi.connect().glossaryById("latest", results.getResults().get(0).getId());

        Assert.assertNotNull(glossary);
        Assert.assertEquals("Lymphangiogram", glossary.getName());
        Assert.assertEquals(Arrays.asList(Category.HEMATO), glossary.getCategories());
        Assert.assertNull(glossary.getPrimarySite());

        Assert.assertNull(glossary.getHistology());
        Assert.assertTrue(glossary.getDefinition().startsWith("An x-ray of the lymphatic system."));
        Assert.assertTrue(glossary.getAbstractorNote().startsWith("This procedure may be done to determine the extent"));
        Assert.assertNull(glossary.getAlternateName());
        Assert.assertNull(glossary.getHistory());

        //        GlossaryHistoryEvent event = glossary.getHistory().get(0);
        //        Assert.assertNotNull(event.getUser());
        //        Assert.assertNotNull(event.getDate());
        //        Assert.assertNull(event.getOld());
        //        Assert.assertNull(event.getNew());
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
        GlossarySearch search = new GlossarySearch("cell");

        GlossarySearchResults results = SeerApi.connect().glossarySearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertTrue(results.getTotal().longValue() > 0);
        Assert.assertTrue(results.getResults().size() > 0);
        Assert.assertEquals(Arrays.asList("cell"), results.getTerms());

        // add the category and verify there are no results
        search.setCategory(EnumSet.of(Category.GENERAL));
        results = SeerApi.connect().glossarySearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(0, results.getTotal().longValue());
        Assert.assertEquals(0, results.getResults().size());

        // add a second category and verify there are we get the results again
        search.setCategory(EnumSet.of(Category.GENERAL, Category.HEMATO));
        results = SeerApi.connect().glossarySearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertTrue(results.getTotal().longValue() > 0);
        Assert.assertTrue(results.getResults().size() > 0);
        Assert.assertEquals(Arrays.asList("cell"), results.getTerms());
    }

}
