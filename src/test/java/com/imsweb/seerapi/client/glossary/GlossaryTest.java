/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

public class GlossaryTest {

    //    private static SeerApi _SEERAPI;
    //
    //    @BeforeClass
    //    public static void setup() {
    //        _SEERAPI = new SeerApiBuilder().connect();
    //    }
    //
    //    @Test
    //    public void testGlossaryCategory() {
    //        Assert.assertEquals(Category.SOLID_TUMOR, Category.valueOf("SOLID_TUMOR"));
    //    }
    //
    //    @Test
    //    public void testGlossaryVersions() throws IOException {
    //        List<GlossaryVersion> versions = _SEERAPI.glossaryVersions();
    //
    //        Assert.assertTrue(versions.size() > 0);
    //        for (GlossaryVersion version : versions) {
    //            Assert.assertTrue(version.getName().length() > 0);
    //            Assert.assertTrue(version.getType().length() > 0);
    //            Assert.assertNotNull(version.getLastModified());
    //        }
    //    }
    //
    //    @Test
    //    public void testGlossaryById() throws IOException {
    //        GlossarySearchResults results = _SEERAPI.glossarySearch("latest", new GlossarySearch("Lymphangiogram"));
    //        Assert.assertTrue(results.getCount() > 0);
    //
    //        Glossary glossary = _SEERAPI.glossaryById("latest", results.getResults().get(0).getId());
    //
    //        Assert.assertNotNull(glossary);
    //        Assert.assertEquals("Lymphangiogram", glossary.getName());
    //        Assert.assertEquals(Collections.singletonList(Category.HEMATO), glossary.getCategories());
    //        Assert.assertNull(glossary.getPrimarySite());
    //
    //        Assert.assertNull(glossary.getHistology());
    //        Assert.assertTrue(glossary.getDefinition().startsWith("An x-ray of the lymphatic system."));
    //        Assert.assertTrue(glossary.getAbstractorNote().startsWith("This procedure may be done to determine the extent"));
    //        Assert.assertNull(glossary.getAlternateName());
    //        Assert.assertNull(glossary.getHistory());
    //
    //        //        GlossaryHistoryEvent event = glossary.getHistory().get(0);
    //        //        Assert.assertNotNull(event.getUser());
    //        //        Assert.assertNotNull(event.getDate());
    //        //        Assert.assertNull(event.getOld());
    //        //        Assert.assertNull(event.getNew());
    //    }
    //
    //    @Test
    //    public void testGlossaryChangelog() throws IOException {
    //        GlossaryChangelogResults results = _SEERAPI.glossaryChangelogs("latest", null, null, 1);
    //
    //        Assert.assertNotNull(results);
    //
    //        // TODO since all the glossary items were removed from the production database, this needs to be commented out; it will return when they are published again
    //
    //        /*
    //        Assert.assertEquals(1, changes.size());
    //
    //        GlossaryChangelog changelog = changes.get(0);
    //
    //        Assert.assertNotNull(changelog.getUser());
    //        Assert.assertEquals("latest_dev", changelog.getVersion());
    //        Assert.assertTrue(changelog.getId().length() > 0);
    //        Assert.assertTrue(changelog.getAdds().size() > 0);
    //
    //        GlossaryChangelogEntry entry = changelog.getAdds().get(0);
    //        Assert.assertTrue(entry.getId().length() > 0);
    //        Assert.assertTrue(entry.getName().length() > 0);
    //        Assert.assertNull(entry.getOldVersion());
    //        Assert.assertNull(entry.getNewVersion());
    //
    //        Assert.assertNull(changelog.getMods());
    //        Assert.assertNull(changelog.getDeletes());
    //        Assert.assertNotNull(changelog.getDate());
    //        Assert.assertEquals("Initial migration", changelog.getDescription());
    //        */
    //    }
    //
    //    @Test
    //    public void testGlossarySearch() throws IOException {
    //        GlossarySearch search = new GlossarySearch("cell");
    //
    //        GlossarySearchResults results = _SEERAPI.glossarySearch("latest", search);
    //
    //        Assert.assertNotNull(results);
    //        Assert.assertEquals(25, results.getCount().longValue());
    //        Assert.assertTrue(results.getTotal().longValue() > 0);
    //        Assert.assertTrue(results.getResults().size() > 0);
    //        Assert.assertEquals(Collections.singletonList("cell"), results.getTerms());
    //
    //        // add the category and verify there are no results
    //        search.setCategory(EnumSet.of(Category.SOLID_TUMOR));
    //        results = _SEERAPI.glossarySearch("latest", search);
    //
    //        Assert.assertNotNull(results);
    //        Assert.assertEquals(25, results.getCount().longValue());
    //        Assert.assertEquals(0, results.getTotal().longValue());
    //        Assert.assertEquals(0, results.getResults().size());
    //
    //        // add a second category and verify there are we get the results again
    //        search.setCategory(EnumSet.of(Category.SOLID_TUMOR, Category.HEMATO));
    //        results = _SEERAPI.glossarySearch("latest", search);
    //
    //        Assert.assertNotNull(results);
    //        Assert.assertEquals(25, results.getCount().longValue());
    //        Assert.assertTrue(results.getTotal().longValue() > 0);
    //        Assert.assertTrue(results.getResults().size() > 0);
    //        Assert.assertEquals(Collections.singletonList("cell"), results.getTerms());
    //    }

}
