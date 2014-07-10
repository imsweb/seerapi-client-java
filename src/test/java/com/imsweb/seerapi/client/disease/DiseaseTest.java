/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class DiseaseTest {

    @Test
    public void testDiseaseVersions() throws IOException {
        List<DiseaseVersion> versions = SeerApi.connect().diseaseVersions();

        Assert.assertTrue(versions.size() > 0);
        for (DiseaseVersion version : versions) {
            Assert.assertTrue(version.getName().length() > 0);
            Assert.assertTrue(version.getType().length() > 0);
            Assert.assertNotNull(version.getLastModified());
        }
    }

    @Test
    public void testDiseasePrimarySites() throws IOException {
        List<PrimarySite> sites = SeerApi.connect().diseasePrimarySites();

        Assert.assertTrue(sites.size() > 0);
        Assert.assertEquals("C000", sites.get(0).getValue());
        Assert.assertEquals("External upper lip", sites.get(0).getLabel());
    }

    @Test
    public void testDiseasePrimarySiteCode() throws IOException {
        List<PrimarySite> sites = SeerApi.connect().diseasePrimarySiteCode("C021");

        Assert.assertTrue(sites.size() > 0);
        Assert.assertEquals("C021", sites.get(0).getValue());
        Assert.assertEquals("Border of tongue", sites.get(0).getLabel());
    }

    @Test
    public void testDiseaseSiteCateogires() throws IOException {
        List<SiteCategory> categories = SeerApi.connect().diseaseSiteCategories();

        Assert.assertTrue(categories.size() > 0);
        Assert.assertEquals("head-and-neck", categories.get(0).getId());
        Assert.assertEquals("Head and Neck", categories.get(0).getLabel());
    }

    @Test
    public void testDiseaseById() throws IOException {
        Disease disease = SeerApi.connect().diseaseById("latest", "51f6cf58e3e27c3994bd5408");

        Assert.assertNotNull(disease);
        Assert.assertEquals("Acute erythroid leukemia", disease.getName());
        Assert.assertEquals(Disease.Type.HEMATO, disease.getType());
        Assert.assertEquals("9840/3", disease.getIcdO3Morphology());
        Assert.assertEquals(10, disease.getSamePrimaries().size());
    }

    @Test
    public void testDiseaseSamePrimary() throws IOException {
        SamePrimaries same = SeerApi.connect().diseaseSamePrimaries("latest", "9870/3", "9872/3", "2010");

        Assert.assertNotNull(same);
        Assert.assertEquals(false, same.isSame());
    }

    @Test
    public void testDiseaseSearch() throws IOException {
        DiseaseSearch search = new DiseaseSearch();

        search.setType(Disease.Type.HEMATO);
        search.setQuery("basophilic");
        DiseaseSearchResults results = SeerApi.connect().diseaseSearch("latest", search);

        Assert.assertNotNull(results);
        Assert.assertEquals(3, results.getCount().longValue());
        Assert.assertEquals(3, results.getResults().size());
        Assert.assertEquals(Arrays.asList("basophilic"), results.getTerms());
    }

    @Test
    public void testDiseaseReportability() throws IOException {
        Disease partial = new Disease();

        partial.setType(Disease.Type.HEMATO);
        partial.setIcdO3Morphology("9840/3");
        partial.setIcdO2Morphology("9840/3");
        partial.setIcdO1Morphology("9840/3");
        partial.setIcdO3Effective(new YearRange(2001, null));
        partial.setIcdO2Effective(new YearRange(1992, 2000));
        partial.setIcdO1Effective(new YearRange(1978, 2001));
        partial.setPrimarySite(Arrays.asList(new SiteRange("C421", "C421")));

        Disease result = SeerApi.connect().diseaseReportability(partial);

        Assert.assertNotNull(result);
        Assert.assertNotNull(result.getReportable());
    }

    @Test
    public void testDiseaseChangelog() throws IOException {
        List<DiseaseChangelog> changes = SeerApi.connect().diseaseChangelogs("latest", null, null, 3);

        Assert.assertNotNull(changes);
        Assert.assertEquals(3, changes.size());
        Assert.assertNotNull(changes.get(0).getUser());
        Assert.assertEquals("latest", changes.get(0).getVersion());
    }

}
