/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.io.IOException;
import java.util.Collections;
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

class DiseaseTest {

    private static DiseaseService _DISEASE;

    @BeforeAll
    public static void setup() {
        _DISEASE = new SeerApi.Builder().connect().disease();
    }

    @Test
    void testDiseaseTypeCategory() {
        assertEquals(Disease.Type.SOLID_TUMOR, Disease.Type.valueOf("SOLID_TUMOR"));
    }

    @Test
    void testDiseaseVersions() throws IOException {
        List<DiseaseVersion> versions = _DISEASE.versions().execute().body();

        assertNotNull(versions);
        assertEquals(1, versions.size());
        DiseaseVersion version = versions.get(0);
        assertEquals("latest", version.getName());
        assertNull(version.getType());  // type not returned when no permisisons
        assertNotNull(version.getFirstPublished());
        assertNotNull(version.getCount());
    }

    @Test
    void testDiseasePrimarySites() throws IOException {
        List<PrimarySite> sites = _DISEASE.primarySites().execute().body();

        assertNotNull(sites);
        assertFalse(sites.isEmpty());
        assertEquals("C000", sites.get(0).getValue());
        assertEquals("External upper lip", sites.get(0).getLabel());
    }

    @Test
    void testDiseasePrimarySiteCode() throws IOException {
        List<PrimarySite> sites = _DISEASE.primarySiteCode("C021").execute().body();

        assertNotNull(sites);
        assertFalse(sites.isEmpty());
        assertEquals("C021", sites.get(0).getValue());
        assertEquals("Border of tongue", sites.get(0).getLabel());
    }

    @Test
    void testDiseaseSiteCateogires() throws IOException {
        List<SiteCategory> categories = _DISEASE.siteCategories().execute().body();

        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        assertEquals("head-and-neck", categories.get(0).getId());
        assertEquals("Head and Neck", categories.get(0).getLabel());
        assertEquals(2, categories.get(0).getSites().size());
        assertEquals("C000", categories.get(0).getSites().get(0).getLow());
        assertEquals("C148", categories.get(0).getSites().get(0).getHigh());
    }

    @SuppressWarnings("java:S5961")
    @Test
    void testDiseaseById() throws IOException {
        Disease disease = _DISEASE.getById("latest", "51f6cf58e3e27c3994bd5408").execute().body();

        assertNotNull(disease);
        assertEquals("Pure erythroid leukemia", disease.getName());
        assertEquals(Disease.Type.HEMATO, disease.getType());
        assertEquals("9840/3", disease.getIcdO3Morphology());
        assertFalse(disease.getSamePrimaries().isEmpty());

        assertNotNull(disease.getId());
        assertEquals("latest", disease.getVersion());
        assertNull(disease.getHidden());
        assertNull(disease.getStatus());
        assertNull(disease.getAssignedTo());
        assertNotNull(disease.getFirstPublished());
        assertNotNull(disease.getLastModified());
        assertNotNull(disease.getFingerprint());
        assertNull(disease.getNote());
        assertNull(disease.getFieldNotes());
        assertNull(disease.getScore());
        assertNull(disease.getGlossaryMatches());
        assertEquals(1, disease.getPrimarySite().size());
        assertEquals("C421", disease.getPrimarySite().get(0).getLow());
        assertEquals("C421", disease.getPrimarySite().get(0).getHigh());
        assertNull(disease.getSiteCategory());
        assertEquals(2001, disease.getValid().getStartYear().longValue());
        assertNull(disease.getValid().getEndYear());
        assertNull(disease.getObsoleteNewCode());
        assertEquals(1, disease.getAbstractorNote().size());
        assertEquals(2, disease.getTreatment().size());
        assertNull(disease.getGenetics());
        assertFalse(disease.getAlternateName().isEmpty());
        assertEquals("Acute erythremia", disease.getAlternateName().get(0).getValue());
        assertTrue(disease.getIcdO2Morphology().contains("9840/3"));
        assertTrue(disease.getIcdO1Morphology().contains("9840/3"));
        assertEquals("C94.0 Acute erythroid leukemia", disease.getIcd10CmCode().get(0).getValue());
        assertEquals("C94.0 Acute erythremia and erythroleukemia", disease.getIcd10Code().get(0));
        assertEquals("207.0 Acute erythremia and erythroleukemia", disease.getIcd9Code().get(0));
        assertNotNull(disease.getSigns());
        assertNotNull(disease.getExams());
        assertNull(disease.getRecurrence());
        assertNotNull(disease.getMortality());
        assertEquals(2001, disease.getIcdO3Effective().getStartYear().longValue());
        assertNull(disease.getIcdO3Effective().getEndYear());
        assertEquals(1992, disease.getIcdO2Effective().getStartYear().longValue());
        assertEquals(2000, disease.getIcdO2Effective().getEndYear().longValue());
        assertEquals(1978, disease.getIcdO1Effective().getStartYear().longValue());
        assertEquals(1991, disease.getIcdO1Effective().getEndYear().longValue());
        assertNull(disease.getMissingPrimarySiteMessage());
        assertNull(disease.getGrade());
        assertNotNull(disease.getTransformFrom());
        assertNull(disease.getTransformTo());
        assertNotNull(disease.getImmunophenotype());
        assertEquals("Bone marrow biopsy", disease.getDiagnosisMethod().get(0).getValue());
        assertEquals("See abstractor notes", disease.getModuleId().get(0).getValue());
        assertNull(disease.getBiomarkers());
        assertNull(disease.getTreatmentText());
        assertTrue(disease.getDiagnosticConfirmation().get(0).getValue().startsWith("This histology can be determined by positive histology"));
    }

    @Test
    void testDiseaseSamePrimary() throws IOException {
        SamePrimaries same = _DISEASE.samePrimaries("latest", "9870/3", "9872/3", "2010", "2010").execute().body();

        assertNotNull(same);
        assertEquals(false, same.getIsSame());
        assertEquals(2010, same.getYear1().longValue());
        assertEquals(2010, same.getYear2().longValue());
        assertEquals("9870/3", same.getDisease1());
        assertEquals("9872/3", same.getDisease2());

        // try same with different years
        same = _DISEASE.samePrimaries("latest", "9870/3", "9872/3", "2010", "2015").execute().body();

        assertNotNull(same);
        assertEquals(false, same.getIsSame());
        assertEquals(2010, same.getYear1().longValue());
        assertEquals(2015, same.getYear2().longValue());
        assertEquals("9870/3", same.getDisease1());
        assertEquals("9872/3", same.getDisease2());
    }

    @Test
    void testDiseaseSearch() throws IOException {
        DiseaseSearch search = new DiseaseSearch("basophilic", Disease.Type.HEMATO);

        DiseaseSearchResults results = _DISEASE.search("latest", search.paramMap()).execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertEquals(5, results.getTotal().longValue());
        assertEquals(5, results.getResults().size());
        assertEquals(Collections.singletonList("basophilic"), results.getTerms());

        search.setSiteCategory("BAD_VALUE");
        results = _DISEASE.search("latest", search.paramMap()).execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertEquals(0, results.getTotal().longValue());
        assertNull(results.getResults());

        // test a case where all search options are set
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
        results = _DISEASE.search("latest", search.paramMap()).execute().body();

        assertNotNull(results);
        assertEquals(100, results.getCount().longValue());
        assertEquals(0, results.getTotal().longValue());
        assertNull(results.getResults());

        // test searching without type
        results = _DISEASE.search("latest", "basophilic").execute().body();

        assertNotNull(results);
        assertEquals(25, results.getCount().longValue());
        assertEquals(6, results.getTotal().longValue());
        assertEquals(6, results.getResults().size());
        assertEquals(Collections.singletonList("basophilic"), results.getTerms());
    }

    @Test
    void testDiseaseSearchIterate() throws IOException {
        DiseaseSearch search = new DiseaseSearch();
        search.setOutputType(PublishableSearch.OutputType.FULL);
        search.setCount(100);
        search.setOffset(0);

        Integer total = null;

        while (total == null || search.getOffset() < total) {
            DiseaseSearchResults results = _DISEASE.search("latest", search.paramMap()).execute().body();
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
    void testDiseaseReportability() throws IOException {
        Disease partial = new Disease();

        partial.setType(Disease.Type.HEMATO);
        partial.setIcdO3Morphology("9840/3");
        partial.setIcdO2Morphology(Collections.singletonList("9840/3"));
        partial.setIcdO1Morphology(Collections.singletonList("9840/3"));
        partial.setIcdO3Effective(new YearRange(2001, null));
        partial.setIcdO2Effective(new YearRange(1992, 2000));
        partial.setIcdO1Effective(new YearRange(1978, 2001));
        partial.setPrimarySite(Collections.singletonList(new SiteRange("C421", "C421")));

        Disease result = _DISEASE.reportability(partial).execute().body();

        assertNotNull(result);
        assertNotNull(result.getReportable());
    }

    @Test
    void testDiseaseChangelog() throws IOException {
        DiseaseChangelogResults results = _DISEASE.diseaseChangelogs("latest", null, "2013-07-30", 1).execute().body();

        assertNotNull(results);
        assertEquals(1, results.getChangelogs().size());
        assertNotNull(results.getChangelogs().get(0).getUser());

        DiseaseChangelog changelog = results.getChangelogs().get(0);

        assertNotNull(changelog.getUser());
        assertEquals("latest", changelog.getVersion());
        assertEquals(300, changelog.getAdds().size());
        assertNull(changelog.getMods());
        assertNull(changelog.getDeletes());
        assertNotNull(changelog.getDate());
        assertEquals("Initial migration", changelog.getDescription());

        DiseaseChangelogEntry entry = changelog.getAdds().get(0);
        assertFalse(entry.getId().isEmpty());
        assertFalse(entry.getName().isEmpty());
        assertNull(entry.getOldVersion());
        assertNull(entry.getNewVersion());
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(Disease.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseVersion.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseChangelog.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseChangelogEntry.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseChangelogResults.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseHistoryEvent.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseSearchResults.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(DiseaseSource.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(SiteCategory.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(SamePrimaries.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(PrimarySite.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(SiteRange.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

}
