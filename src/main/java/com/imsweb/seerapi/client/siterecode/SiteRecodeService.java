/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.siterecode;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SiteRecodeService {

    /**
     * Return the supported algorithms and versions
     * @return a list of information about the algorithms
     */
    @GET("recode/sitegroup/algorithms")
    Call<List<SiteGroupAlgorithm>> algorithms();

    /**
     * Return the specified algorithm site group for the site/histology/behavior combination, or 99999 if the combination is unknown.
     * @param algorithm site group algorithm
     * @param site Primary Site O3
     * @param hist Histology O3
     * @param behavior Behavior O3
     * @return a SiteRecode object based on the site and histology
     */
    @GET("recode/sitegroup/{algorithm}")
    Call<SiteRecode> siteGroup(@Path("algorithm") String algorithm, @Query("site") String site, @Query("hist") String hist, @Query("behavior") String behavior);

}
