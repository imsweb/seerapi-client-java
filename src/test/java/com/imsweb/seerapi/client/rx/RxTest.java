/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.publishable.PublishableSearch;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RxTest {

    private static RxService _RX;

    @BeforeAll
    public static void setup() {
        _RX = new SeerApi.Builder().connect().rx();
    }

    @Test
    void testRxVersions() throws IOException {
        List<RxVersion> versions = _RX.versions().execute().body();

        assertNotNull(versions);
        assertEquals(1, versions.size());
        RxVersion version = versions.get(0);
        assertEquals("latest", version.getName());
        assertNull(version.getType());  // type not returned when no permisisons
        assertNotNull(version.getFirstPublished());
        assertNotNull(version.getCount());
    }

    @Test
    void testRxById() throws IOException {
        Rx rx = _RX.getById("latest", "53c44b0a102c1290262dce76").execute().body();

        assertNotNull(rx);
        assertEquals("Imatinib mesylate", rx.getName());
        assertTrue(rx.getAlternateName().size() > 1);
        assertEquals(Rx.Type.DRUG, rx.getType());
        assertNull(rx.getHistology());
        assertTrue(rx.getRemarks().startsWith("January 31, 2012"));
        assertNull(rx.getEvsId());
        assertNull(rx.getAbbreviation());
        assertEquals(Collections.singletonList("Chemotherapy"), rx.getCategory());
        assertFalse(rx.getSubcategory().isEmpty());
        assertEquals(1, rx.getNscNumber().size());
        assertNull(rx.getDrugs());
        assertNull(rx.getRadiation());
        assertNull(rx.getHidden());
        assertNull(rx.getStatus());
        assertNull(rx.getAssignedTo());
        assertNotNull(rx.getFirstPublished());
        assertNotNull(rx.getLastModified());
        assertNotNull(rx.getFingerprint());
        assertNull(rx.getNote());
        assertNull(rx.getFieldNotes());
        assertNull(rx.getScore());
        assertEquals(Arrays.asList("aggressive mastocytosis",
                "chronic eosinophilic leukemia",
                "Colorectal",
                "dermatofibrosarcoma protuberans",
                "gastric",
                "GIST",
                "hypereosinophilic syndrome",
                "leukemia",
                "lung",
                "other cancer",
                "Ph+ALL"), rx.getPrimarySite());
        assertNull(rx.getHistory());
    }

    @Test
    void testRxChangelog() throws IOException {
        RxChangelogResults results = _RX.changelogs("latest", "2015-08-30", "2015-09-30", 1).execute().body();

        assertNotNull(results);

        List<RxChangelog> changes = results.getChangelogs();

        assertEquals(1, changes.size());
        assertNotNull(changes.get(0).getUser());
        assertEquals("latest", changes.get(0).getVersion());

        RxChangelog changelog = changes.get(0);

        assertNotNull(changelog.getUser());
        assertEquals("latest", changelog.getVersion());
        assertNull(changelog.getAdds());
        assertFalse(changelog.getMods().isEmpty());
        assertNull(changelog.getDeletes());
        assertNotNull(changelog.getDate());
        assertNull(changelog.getDescription());

        RxChangelogEntry entry = changelog.getMods().get(0);
        assertFalse(entry.getId().isEmpty());
        assertFalse(entry.getName().isEmpty());
        assertNotNull(entry.getOldVersion());
        assertNotNull(entry.getNewVersion());
    }

    @Test
    void testRxSearch() throws IOException {
        RxSearch search = new RxSearch("abt", Rx.Type.DRUG);
        RxSearchResults results = _RX.search("latest", search.paramMap()).execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertEquals(7, results.getTotal().longValue());
        assertEquals(7, results.getResults().size());
        assertEquals(Collections.singletonList("abt"), results.getTerms());

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

        assertNotNull(results);
        assertEquals(100, results.getCount().longValue());
        assertEquals(0, results.getTotal().longValue());
        assertNull(results.getResults());
    }

    @Test
    void testRxSearchIterate() throws IOException {
        RxSearch search = new RxSearch();
        search.setOutputType(PublishableSearch.OutputType.FULL);
        search.setCount(100);
        search.setOffset(0);

        Integer total = null;

        while (total == null || search.getOffset() < total) {
            RxSearchResults results = _RX.search("latest", search.paramMap()).execute().body();
            assertNotNull(results);
            assertTrue(results.getTotal() > 0);
            assertFalse(results.getResults().isEmpty());

            // the first time through, set the total
            if (total == null)
                total = results.getTotal();

            search.setOffset(search.getOffset() + results.getResults().size());
        }
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(Rx.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(RxChangelog.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(RxChangelogEntry.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(RxChangelogResults.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(RxVersion.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(RxHistoryEvent.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(RxSearchResults.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }
}
