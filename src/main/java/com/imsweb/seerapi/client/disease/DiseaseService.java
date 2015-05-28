/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

public interface DiseaseService {

    //
    //    /**
    //     * Return a list of all disease versions and information about them
    //     * @return a list of DiseaseVersion objects
    //     */
    //    public List<DiseaseVersion> diseaseVersions() {
    //        WebTarget target = createTarget("/disease/versions");
    //
    //        return getBuilder(target).get(new GenericType<List<DiseaseVersion>>() {});
    //    }
    //
    //    /**
    //     * Return the changelog entries for the passed database version
    //     * @param version Disease version
    //     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
    //     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
    //     * @param count if not null, limit the number returned
    //     * @return a list of DiseaseChangelogResults objects
    //     */
    //    public DiseaseChangelogResults diseaseChangelogs(String version, String fromDate, String toDate, Integer count) {
    //        WebTarget target = createTarget("/disease/{version}/changelog")
    //                .resolveTemplate("version", version)
    //                .queryParam("from", fromDate)
    //                .queryParam("to", toDate)
    //                .queryParam("count", count);
    //
    //        return getBuilder(target).get(DiseaseChangelogResults.class);
    //    }
    //
    //    /**
    //     * Return a list of matching diseases
    //     * @param version Disease version
    //     * @param search DiseaseSearch object
    //     * @return a DiseaseSearchResults object
    //     */
    //    public DiseaseSearchResults diseaseSearch(String version, DiseaseSearch search) {
    //        WebTarget target = createTarget("/disease/{version}").resolveTemplate("version", version);
    //
    //        target = target.queryParam("q", search.getQuery())
    //                .queryParam("type", search.getType())
    //                .queryParam("site_category", search.getSiteCategory())
    //                .queryParam("mode", search.getMode())
    //                .queryParam("status", search.getStatus())
    //                .queryParam("assigned_to", search.getAssignedTo())
    //                .queryParam("modified_from", search.getModifiedFrom())
    //                .queryParam("modified_to", search.getModifiedTo())
    //                .queryParam("published_from", search.getPublishedFrom())
    //                .queryParam("published_to", search.getPublishedTo())
    //                .queryParam("been_published", search.getBeenPublished())
    //                .queryParam("hidden", search.getHidden())
    //                .queryParam("count", search.getCount())
    //                .queryParam("offset", search.getOffset())
    //                .queryParam("order", search.getOrderBy())
    //                .queryParam("output_type", search.getOutputType());
    //
    //        return getBuilder(target).get(DiseaseSearchResults.class);
    //    }
    //
    //    /**
    //     * Return a complete disease entity based in identifier.  Note that by default the disease entity does not include relevant glossary references.
    //     * @param version Disease version
    //     * @param id Disease identifier
    //     * @return a Disease object
    //     */
    //    public Disease diseaseById(String version, String id) {
    //        return diseaseById(version, id, false);
    //    }
    //
    //    /**
    //     * Return a complete disease entity based in identifier
    //     * @param version Disease version
    //     * @param id Disease identifier
    //     * @param includeGlossary if true, include the glossary
    //     * @return a Disease object
    //     */
    //    public Disease diseaseById(String version, String id, boolean includeGlossary) {
    //        WebTarget target = createTarget("/disease/{version}/id/{id}")
    //                .resolveTemplate("version", version)
    //                .resolveTemplate("id", id)
    //                .queryParam("glossary", includeGlossary);
    //
    //        return getBuilder(target).get(Disease.class);
    //    }
    //
    //    /**
    //     * Return a list of all primary sites and labels
    //     * @return a List of PrimarySite objects
    //     */
    //    public List<PrimarySite> diseasePrimarySites() {
    //        WebTarget target = createTarget("/disease/primary_site");
    //
    //        return getBuilder(target).get(new GenericType<List<PrimarySite>>() {});
    //    }
    //
    //    /**
    //     * Return a single primary site and label
    //     * @param primarySite Primary Site O3
    //     * @return a PrimarySite object
    //     */
    //    public List<PrimarySite> diseasePrimarySiteCode(String primarySite) {
    //        WebTarget target = createTarget("/disease/primary_site/{code}").resolveTemplate("code", primarySite);
    //
    //        return getBuilder(target).get(new GenericType<List<PrimarySite>>() {});
    //    }
    //
    //    /**
    //     * Return a complete list of site categories and definitions
    //     * @return a list of SiteCategory objects
    //     */
    //    public List<SiteCategory> diseaseSiteCategories() {
    //        WebTarget target = createTarget("/disease/site_categories");
    //
    //        return getBuilder(target).get(new GenericType<List<SiteCategory>>() {});
    //    }
    //
    //    /**
    //     * Return whether the 2 morphologies represent the same primary for the given year.
    //     * @param version Disease version
    //     * @param morphology1 ICD O3 Morphology
    //     * @param morphology2 ICD O3 Morphology
    //     * @param year Year of Diagnosis
    //     * @return a SamePrimary object
    //     */
    //    public SamePrimaries diseaseSamePrimaries(String version, String morphology1, String morphology2, String year) {
    //        WebTarget target = createTarget("/disease/{version}/same_primary")
    //                .resolveTemplate("version", version)
    //                .queryParam("d1", morphology1)
    //                .queryParam("d2", morphology2)
    //                .queryParam("year", year);
    //
    //        return getBuilder(target).get(SamePrimaries.class);
    //    }
    //
    //    /**
    //     * Returns the reportable year range of the supplied disease.
    //     * @param disease Disease object
    //     * @return a Disease object with the reportability field filled in
    //     */
    //    public Disease diseaseReportability(Disease disease) {
    //        WebTarget target = createTarget("/disease/reportability");
    //
    //        return getBuilder(target).post(Entity.json(disease), Disease.class);
    //    }

}
