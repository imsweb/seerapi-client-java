/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface DiseaseService {

    /**
     * Return a list of all disease versions and information about them
     * @return a list of DiseaseVersion objects
     */
    @GET("disease/versions")
    Call<List<DiseaseVersion>> versions();

    /**
     * Return a list of matching diseases
     * @param version Disease version
     * @param query search query
     * @return a DiseaseSearchResults object
     */
    @GET("disease/{version}")
    Call<DiseaseSearchResults> search(@Path("version") String version, @Query("q") String query);

    /**
     * Return a list of matching diseases
     * @param version Disease version
     * @param searchParams A Map of search parameters.  Use DiseaseSearch to easily build parameter list.
     * @return a DiseaseSearchResults object
     */
    @GET("disease/{version}")
    Call<DiseaseSearchResults> search(@Path("version") String version, @QueryMap Map<String, String> searchParams);

    /**
     * Return a complete disease entity based in identifier.  Note that by default the disease entity does not include relevant glossary references.
     * @param version Disease version
     * @param id Disease identifier
     * @return a Disease object
     */
    @GET("disease/{version}/id/{id}")
    Call<Disease> getById(@Path("version") String version, @Path("id") String id);

    /**
     * Return a complete disease entity based in identifier
     * @param version Disease version
     * @param id Disease identifier
     * @param includeGlossary if true, include the glossary
     * @return a Disease object
     */
    @GET("disease/{version}/id/{id}")
    Call<Disease> getById(@Path("version") String version, @Path("id") String id, @Query("glossary") boolean includeGlossary);

    /**
     * Return a list of all primary sites and labels
     * @return a List of PrimarySite objects
     */
    @GET("disease/primary_site")
    Call<List<PrimarySite>> primarySites();

    /**
     * Return a single primary site and label
     * @param primarySite Primary Site O3
     * @return a PrimarySite object
     */
    @GET("disease/primary_site/{code}")
    Call<List<PrimarySite>> primarySiteCode(@Path("code") String primarySite);

    /**
     * Return a complete list of site categories and definitions
     * @return a list of SiteCategory objects
     */
    @GET("disease/site_categories")
    Call<List<SiteCategory>> siteCategories();

    /**
     * Return whether the 2 morphologies represent the same primary for the given year.
     * @param version Disease version
     * @param morphology1 ICD O3 Morphology
     * @param morphology2 ICD O3 Morphology
     * @param year Year of Diagnosis
     * @return a SamePrimary object
     */
    @GET("disease/{version}/same_primary")
    Call<SamePrimaries> samePrimaries(@Path("version") String version, @Query("d1") String morphology1, @Query("d2") String morphology2, @Query("year") String year);

    /**
     * Returns the reportable year range of the supplied disease.
     * @param disease Disease object
     * @return a Disease object with the reportability field filled in
     */
    @POST("disease/reportability")
    Call<Disease> reportability(@Body Disease disease);

    /**
     * Return the changelog entries for the passed database version
     * @param version Disease version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of DiseaseChangelogResults objects
     */
    @GET("disease/{version}/changelog")
    Call<DiseaseChangelogResults> diseaseChangelogs(@Path("version") String version, @Query("from") String fromDate, @Query("to") String toDate, @Query("count") Integer count);
}
