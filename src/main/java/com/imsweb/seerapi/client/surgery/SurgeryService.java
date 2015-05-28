/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.surgery;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import com.imsweb.seerapi.client.shared.Version;

public interface SurgeryService {

    /**
     * Return a collection of Version objects which describe the available versions
     * @return a list of the available site-specific surgery versions and information about each of them
     */
    @GET("/surgery/versions")
    List<Version> versions();

    /**
     * Return a list of all the site-specific surgery table titles from a specific version
     * @param version version
     * @return a list of site-specific surgery table titles
     */
    @GET("/surgery/{version}/tables")
    List<String> tables(@Path("version") String version);

    /**
     * Return a specific site-specific surgary table from a specific version
     * @param version version
     * @param title site title (optional if the site/histology is provided)
     * @param site primary site (optional if the title is provided)
     * @param histology histology (optional if the title is provided)
     * @return a site-specific surgery table
     */
    @GET("/surgery/{version}/table")
    SurgeryTable table(@Path("version") String version, @Query("title") String title, @Query("site") String site, @Query("hist") String histology);

}
