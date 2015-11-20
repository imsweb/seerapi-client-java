/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface RxService {

    /**
     * Return a list of all Rx versions and information about them.  Note that by default the Rx entity does not include relevant glossary references.
     * @return a list of RxVersion objects
     */
    @GET("rx/versions")
    Call<List<RxVersion>> versions();

    /**
     * Return a complete Rx entity based in identifier
     * @param version Rx version
     * @param id Rx identifier
     * @return a Rx object
     */
    @GET("rx/{version}/id/{id}")
    Call<Rx> getById(@Path("version") String version, @Path("id") String id);

    /**
     * Return a complete Rx entity based in identifier
     * @param version Rx version
     * @param id Rx identifier
     * @param includeGlossary if true, include the glossary
     * @return a Rx object
     */
    @GET("rx/{version}/id/{id}")
    Call<Rx> getById(@Path("version") String version, @Path("id") String id, @Query("glossary") boolean includeGlossary);

    /**
     * Return a list of matching Rx entities
     * @param version Rx version
     * @param query search query
     * @return a RxSearchResults object
     */
    @GET("rx/{version}")
    Call<RxSearchResults> search(@Path("version") String version, @Query("q") String query);

    /**
     * Return a list of matching Rx entities
     * @param version Rx version
     * @param query search query
     * @param categories limit to these categories
     * @return a RxSearchResults object
     */
    @GET("rx/{version}")
    Call<RxSearchResults> search(@Path("version") String version, @Query("q") String query, @Query("category") Set<String> categories);

    /**
     * Return a list of matching Rx entities
     * @param version Rx version
     * @param searchParams A Map of search parameters.  Use RxSearch to easily build parameter list.
     * @return a RxSearchResults object
     */
    @GET("rx/{version}")
    Call<RxSearchResults> search(@Path("version") String version, @QueryMap Map<String, String> searchParams);

    /**
     * Return a list of matching Rx entities
     * @param version Rx version
     * @param searchParams A Map of search parameters.  Use RxSearch to easily build parameter list.
     * @param categories limit to these categories
     * @return a RxSearchResults object
     */
    @GET("rx/{version}")
    Call<RxSearchResults> search(@Path("version") String version, @QueryMap Map<String, String> searchParams, @Query("category") Set<String> categories);

    /**
     * Return the changelog entries for the passed database version
     * @param version Rx version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of RxChangelogResults objects
     */
    @GET("rx/{version}/changelog")
    Call<RxChangelogResults> changelogs(@Path("version") String version, @Query("from") String fromDate, @Query("to") String toDate, @Query("count") Integer count);

}
