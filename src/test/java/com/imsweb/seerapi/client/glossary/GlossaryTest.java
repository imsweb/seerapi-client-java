/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.publishable.PublishableSearch;
import com.imsweb.seerapi.client.shared.KeywordMatch;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static com.imsweb.seerapi.client.glossary.Glossary.Category.GENERAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlossaryTest {

    private static GlossaryService _GLOSSARY;

    @BeforeAll
    public static void setup() {
        _GLOSSARY = new SeerApi.Builder().connect().glossary();
    }

    @Test
    void testGlossaryCategory() {
        assertEquals(Glossary.Category.SOLID_TUMOR, Glossary.Category.valueOf("SOLID_TUMOR"));
    }

    @Test
    void testGlossaryVersions() throws IOException {
        List<GlossaryVersion> versions = _GLOSSARY.versions().execute().body();

        assertNotNull(versions);
        assertEquals(1, versions.size());
        GlossaryVersion version = versions.get(0);
        assertEquals("latest", version.getName());
        assertNull(version.getType());  // type not returned when no permisisons
        assertNotNull(version.getFirstPublished());
        assertNotNull(version.getCount());
    }

    @Test
    void testGlossaryById() throws IOException {
        GlossarySearchResults results = _GLOSSARY.search("latest", "Lymphangiogram").execute().body();
        assertNotNull(results);
        assertTrue(results.getCount() > 0);

        Glossary glossary = _GLOSSARY.getById("latest", results.getResults().get(0).getId()).execute().body();

        assertNotNull(glossary);
        assertEquals("Lymphangiogram", glossary.getName());
        assertEquals(Collections.singletonList(GENERAL), glossary.getCategories());
        assertNull(glossary.getPrimarySite());

        assertNull(glossary.getHistology());
        assertTrue(glossary.getDefinition().startsWith("An x-ray of the lymphatic system."));
        assertNull(glossary.getAlternateName());
        assertNull(glossary.getHistory());
    }

    @Test
    void testGlossarySearch() throws IOException {
        String term = "killer";
        GlossarySearch search = new GlossarySearch(term);

        GlossarySearchResults results = _GLOSSARY.search("latest", search.paramMap()).execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertTrue(results.getTotal().longValue() > 0);
        assertFalse(results.getResults().isEmpty());
        assertEquals(Collections.singletonList(term), results.getTerms());

        // add the category and verify there are no results
        results = _GLOSSARY.search("latest", search.paramMap(), EnumSet.of(Glossary.Category.SOLID_TUMOR)).execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertEquals(0, results.getTotal().longValue());
        assertNull(results.getResults());

        // add a second category and verify there are we get the results again
        results = _GLOSSARY.search("latest", search.paramMap(), EnumSet.of(Glossary.Category.SOLID_TUMOR, Glossary.Category.HEMATO)).execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertTrue(results.getTotal().longValue() > 0);
        assertFalse(results.getResults().isEmpty());
        assertEquals(Collections.singletonList(term), results.getTerms());
    }

    @Test
    void testGlossarySearchIterate() throws IOException {
        GlossarySearch search = new GlossarySearch();
        search.setOutputType(PublishableSearch.OutputType.FULL);
        search.setCount(25);
        search.setOffset(0);

        Integer total = null;

        while (total == null || search.getOffset() < total) {
            GlossarySearchResults results = _GLOSSARY.search("latest", search.paramMap(), EnumSet.of(Glossary.Category.HEMATO)).execute().body();
            assertNotNull(results);
            assertTrue(results.getTotal() > 0);
            assertFalse(results.getResults().isEmpty());

            // the first time through, set the total
            if (total == null)
                total = results.getTotal();

            search.setOffset(search.getOffset() + results.getResults().size());
        }

        assertTrue(total > 100);
    }

    @Test
    void testGlossaryMatch() throws IOException {
        String text = "This text contains summary stage which should be found.";

        Set<KeywordMatch> matches = _GLOSSARY.match(text, null, true).execute().body();
        assertNotNull(matches);
        assertEquals(1, matches.size());

        matches = _GLOSSARY.match(text, EnumSet.of(GENERAL), true).execute().body();
        assertNotNull(matches);
        assertEquals(0, matches.size());
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(Glossary.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossaryVersion.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossaryResource.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossaryChangelog.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossaryChangelogEntry.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossarySearchResults.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossaryChangelogResults.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(GlossaryHistoryEvent.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(KeywordMatch.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }
}
