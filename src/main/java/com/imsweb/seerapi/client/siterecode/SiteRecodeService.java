/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.imsweb.seerapi.client.shared.Version;

public interface SiteRecodeService {

    /**
     * Return the version of the SEER Site Recode database.
     * @return a String representing the database version
     */
    @GET("recode/version")
    Call<Version> version();

    /**
     * Return the SEER Site Group for the site/histology combination, or 99999 if the combination is unknown.
     * @param site Primary Site O3
     * @param hist Histology O3
     * @return a SiteRecode object based on the site and histology
     */
    @GET("recode/sitegroup")
    Call<SiteRecode> siteGroup(@Query("site") String site, @Query("hist") String hist);

}
