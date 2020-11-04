/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.imsweb.seerapi.client.glossary.Glossary.Category;
import com.imsweb.seerapi.client.shared.KeywordMatch;

public interface StagingService {

    /**
     * Return a list of all supported staging algorithms
     * @return a list of StagingAlgorithm objects
     */
    @GET("staging/algorithms")
    Call<List<StagingAlgorithm>> algorithms();

    /**
     * Return a list of supported versions for the passed algorithm
     * @param algorithm an algorithm identifier
     * @return a list of schema versions
     */
    @GET("staging/{algorithm}/versions")
    Call<List<StagingVersion>> versions(@Path("algorithm") String algorithm);

    /**
     * Return a list of matching schemas
     * @param algorithm an algorithm identifier
     * @param version a version
     * @return a list of schemas
     */
    @GET("staging/{algorithm}/{version}/schemas")
    Call<List<StagingSchemaInfo>> schemas(@Path("algorithm") String algorithm, @Path("version") String version);

    /**
     * Return a list of matching schemas
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @return a list of schemas
     */
    @GET("staging/{algorithm}/{version}/schemas")
    Call<List<StagingSchemaInfo>> schemas(@Path("algorithm") String algorithm, @Path("version") String version, @Query("q") String query);

    /**
     * Perform a schema lookup
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param data a map of key/value pairs containing the input for the lookup
     * @return a list of schemas
     */
    @POST("staging/{algorithm}/{version}/schemas/lookup")
    Call<List<StagingSchemaInfo>> schemaLookup(@Path("algorithm") String algorithm, @Path("version") String version, @Body Map<String, String> data);

    /**
     * Return a single schema definition by schema identifier
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a schema identifier
     * @return a schema object
     */
    @GET("staging/{algorithm}/{version}/schema/{id}")
    Call<StagingSchema> schemaById(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String id);

    /**
     * Return a glossary results for a staging schema
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a table identifier
     * @param categories options list of categories
     * @param wholeWordsOnly if true, only return whole word matcehs
     * @return a set of matches
     */
    @GET("staging/{algorithm}/{version}/schema/{id}/glossary")
    Call<Set<KeywordMatch>> schemaGlossary(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String id, @Query("category") Set<Category> categories, @Query("wholeWordsOnly") Boolean wholeWordsOnly);

    /**
     * Return a list of tables which are involved in the specified schema
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param schemaId a schema identifier
     * @return a list of staging tables
     */
    @GET("staging/{algorithm}/{version}/schema/{id}/tables")
    Call<List<StagingTable>> involvedTables(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String schemaId);

    /**
     * Return a list of matching tables
     * @param algorithm an algorithm identifier
     * @param version a version
     * @return a list of staging tables
     */
    @GET("staging/{algorithm}/{version}/tables")
    Call<List<StagingTable>> tables(@Path("algorithm") String algorithm, @Path("version") String version);

    /**
     * Return a list of matching tables
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @return a list of staging tables
     */
    @GET("staging/{algorithm}/{version}/tables")
    Call<List<StagingTable>> tables(@Path("algorithm") String algorithm, @Path("version") String version, @Query("q") String query);

    /**
     * Return a list of matching tables
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @param unusedOnly if true, limit to unused tables
     * @return a list of staging tables
     */
    @GET("staging/{algorithm}/{version}/tables")
    Call<List<StagingTable>> tables(@Path("algorithm") String algorithm, @Path("version") String version, @Query("q") String query, @Query("unused") Boolean unusedOnly);

    /**
     * Return a single table definition by table identifier
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a table identifier
     * @return a staging table
     */
    @GET("staging/{algorithm}/{version}/table/{id}")
    Call<StagingTable> tableById(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String id);

    /**
     * Return a glossary results for a staging table
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a table identifier
     * @param categories options list of categories
     * @param wholeWordsOnly if true, only return whole word matcehs
     * @return a set of matches
     */
    @GET("staging/{algorithm}/{version}/table/{id}/glossary")
    Call<Set<KeywordMatch>> tableGlossary(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String id, @Query("category") Set<Category> categories, @Query("wholeWordsOnly") Boolean wholeWordsOnly);

    /**
     * Return a list of schemas which the specified table is involved in
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param tableId a table identifier
     * @return a list of staging schemas
     */
    @GET("staging/{algorithm}/{version}/table/{id}/schemas")
    Call<List<StagingSchema>> involvedSchemas(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String tableId);

    /**
     * Stage the passed input
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param input a map of key/value pairs containing the input for the staging call
     * @return a StagingData object representing the results of the staging process
     */
    @POST("staging/{algorithm}/{version}/stage")
    Call<StagingData> stage(@Path("algorithm") String algorithm, @Path("version") String version, @Body Map<String, String> input);

}
