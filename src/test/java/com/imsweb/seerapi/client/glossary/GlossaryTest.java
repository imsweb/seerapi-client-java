/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.publishable.PublishableSearch;
import com.imsweb.seerapi.client.shared.KeywordMatch;

import static com.imsweb.seerapi.client.glossary.Glossary.Category.GENERAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class GlossaryTest {

    private static GlossaryService _GLOSSARY;

    @BeforeClass
    public static void setup() {
        _GLOSSARY = new SeerApi.Builder().connect().glossary();
    }

    @Test
    public void testGlossaryCategory() {
        Assert.assertEquals(Glossary.Category.SOLID_TUMOR, Glossary.Category.valueOf("SOLID_TUMOR"));
    }

    @Test
    public void testGlossaryVersions() throws IOException {
        List<GlossaryVersion> versions = _GLOSSARY.versions().execute().body();

        assertEquals(1, versions.size());
        GlossaryVersion version = versions.get(0);
        assertEquals("latest", version.getName());
        assertNull(version.getType());  // type not returned when no permisisons
        assertNotNull(version.getFirstPublished());
        assertNotNull(version.getCount());
    }

    @Test
    public void testGlossaryById() throws IOException {
        GlossarySearchResults results = _GLOSSARY.search("latest", "Lymphangiogram").execute().body();
        Assert.assertTrue(results.getCount() > 0);

        Glossary glossary = _GLOSSARY.getById("latest", results.getResults().get(0).getId()).execute().body();

        Assert.assertNotNull(glossary);
        Assert.assertEquals("Lymphangiogram", glossary.getName());
        Assert.assertEquals(Collections.singletonList(GENERAL), glossary.getCategories());
        Assert.assertNull(glossary.getPrimarySite());

        Assert.assertNull(glossary.getHistology());
        Assert.assertTrue(glossary.getDefinition().startsWith("An x-ray of the lymphatic system."));
        Assert.assertNull(glossary.getAlternateName());
        Assert.assertNull(glossary.getHistory());
    }

    @Test
    public void testGlossaryChangelog() throws IOException {
        GlossaryChangelogResults results = _GLOSSARY.changelogs("latest", null, null, 1).execute().body();

        Assert.assertNotNull(results);

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
        String term = "killer";
        GlossarySearch search = new GlossarySearch(term);

        GlossarySearchResults results = _GLOSSARY.search("latest", search.paramMap()).execute().body();

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertTrue(results.getTotal().longValue() > 0);
        Assert.assertTrue(results.getResults().size() > 0);
        Assert.assertEquals(Collections.singletonList(term), results.getTerms());

        // add the category and verify there are no results
        results = _GLOSSARY.search("latest", search.paramMap(), EnumSet.of(Glossary.Category.SOLID_TUMOR)).execute().body();

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertEquals(0, results.getTotal().longValue());
        Assert.assertNull(results.getResults());

        // add a second category and verify there are we get the results again
        results = _GLOSSARY.search("latest", search.paramMap(), EnumSet.of(Glossary.Category.SOLID_TUMOR, Glossary.Category.HEMATO)).execute().body();

        Assert.assertNotNull(results);
        Assert.assertEquals(25, results.getCount().longValue());
        Assert.assertTrue(results.getTotal().longValue() > 0);
        Assert.assertTrue(results.getResults().size() > 0);
        Assert.assertEquals(Collections.singletonList(term), results.getTerms());
    }

    @Test
    public void testGlossarySearchIterate() throws IOException {
        GlossarySearch search = new GlossarySearch();
        search.setOutputType(PublishableSearch.OutputType.FULL);
        search.setCount(25);
        search.setOffset(0);

        Integer total = null;

        while (total == null || search.getOffset() < total) {
            GlossarySearchResults results = _GLOSSARY.search("latest", search.paramMap(), EnumSet.of(Glossary.Category.HEMATO)).execute().body();
            Assert.assertNotNull(results);
            Assert.assertTrue(results.getTotal() > 0);
            Assert.assertTrue(results.getResults().size() > 0);

            // the first time through, set the total
            if (total == null)
                total = results.getTotal();

            search.setOffset(search.getOffset() + results.getResults().size());
        }

        Assert.assertTrue(total > 100);
    }

    @Test
    public void testGlossaryMatch() throws IOException {
        String text = "This text contains summary stage which should be found.";

        Set<KeywordMatch> matches = _GLOSSARY.match(text, null, true).execute().body();
        Assert.assertNotNull(matches);
        Assert.assertEquals(matches.size(), 1);

        matches = _GLOSSARY.match(text, EnumSet.of(GENERAL), true).execute().body();
        Assert.assertNotNull(matches);
        Assert.assertEquals(matches.size(), 0);
    }

}
