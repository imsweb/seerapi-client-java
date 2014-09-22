/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

public class RxTest {

    /*
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
        Rx Rx = SeerApi.connect().rxById("latest", "51f6cf85e3e2d4d91c7a7296");

        Assert.assertNotNull(Rx);
        Assert.assertEquals("Brain stem", Rx.getName());
        Assert.assertEquals(Arrays.asList(Rx.Category.SOLID_TUMOR), Rx.getCategories());
        Assert.assertEquals(Arrays.asList("C717"), Rx.getPrimarySite());
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

        search.setQuery("stem");
        RxSearchResults results = SeerApi.connect().rxSearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(2, results.getCount().longValue());
        Assert.assertEquals(2, results.getResults().size());
        Assert.assertEquals(Arrays.asList("stem"), results.getTerms());
    }
    */
}
