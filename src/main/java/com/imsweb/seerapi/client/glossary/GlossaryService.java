/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.List;
import java.util.Map;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface GlossaryService {

    /**
     * Return a list of all glossary versions and information about them
     * @return a list of GlossaryVersion objects
     */
    @GET("/glossary/versions")
    List<GlossaryVersion> versions();

    /**
     * Return a complete glossary entity based in identifier
     * @param version Glossary version
     * @param id Glossary identifier
     * @return a Glossary object
     */
    @GET("/glossary/{version}/id/{id}")
    Glossary getById(@Path("version") String version, @Path("id") String id);

    /**
     * Return a list of matching glossaries
     * @param version Glossary version
     * @param query search query
     * @return a GlossarySearchResults object
     */
    @GET("/glossary/{version}")
    GlossarySearchResults search(@Path("version") String version, @Query("q") String query);

    /**
     * Return a list of matching glossaries
     * @param version Glossary version
     * @param searchParams A Map of search parameters.  Use GlossarySearch to easily build parameter list.
     * @return a GlossarySearchResults object
     */
    @GET("/glossary/{version}")
    GlossarySearchResults search(@Path("version") String version, @QueryMap Map<String, String> searchParams);

    /**
     * Return the changelog entries for the passed database version
     * @param version Glossary version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of GlossaryChangelogResults objects
     */
    @GET("/glossary/{version}/changelog")
    GlossaryChangelogResults changelogs(@Path("version") String version, @Query("from") String fromDate, @Query("to") String toDate, @Query("count") Integer count);

}
