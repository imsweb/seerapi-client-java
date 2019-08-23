/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SurgeryService {

    /**
     * Return a list of all the site-specific surgery table titles from a specific year
     * @param year year
     * @return a list of site-specific surgery table titles
     */
    @GET("surgery/{year}/tables")
    Call<List<String>> tables(@Path("year") String year);

    /**
     * Return a specific site-specific surgary table from a specific year
     * @param year year
     * @param title site title (optional if the site/histology is provided)
     * @param site primary site (optional if the title is provided)
     * @param histology histology (optional if the title is provided)
     * @return a site-specific surgery table
     */
    @GET("surgery/{year}/table")
    Call<SurgeryTable> table(@Path("year") String year, @Query("title") String title, @Query("site") String site, @Query("hist") String histology);

}
