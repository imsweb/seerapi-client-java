/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;
import java.util.Map;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

public interface StagingService {

    /**
     * Return a list of all supported staging algorithms
     * @return a list of StagingAlgorithm objects
     */
    @GET("/staging/algorithms")
    List<StagingAlgorithm> algorithms();

    /**
     * Return a list of supported versions for the passed algorithm
     * @param algorithm an algorithm identifier
     * @return
     */
    @GET("/staging/{algorithm}/versions")
    List<StagingVersion> versions(@Path("algorithm") String algorithm);

    /**
     * Return a list of matching schemas
     * @param algorithm an algorithm identifier
     * @param version a version
     * @return a list of schemas
     */
    @GET("/staging/{algorithm}/{version}/schemas")
    List<StagingSchemaInfo> schemas(@Path("algorithm") String algorithm, @Path("version") String version);

    /**
     * Return a list of matching schemas
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @return a list of schemas
     */
    @GET("/staging/{algorithm}/{version}/schemas")
    List<StagingSchemaInfo> schemas(@Path("algorithm") String algorithm, @Path("version") String version, @Query("q") String query);

    /**
     * Perform a schema lookup
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param data a map of key/value pairs containing the input for the lookup
     * @return
     */
    @POST("/staging/{algorithm}/{version}/schemas/lookup")
    List<StagingSchemaInfo> schemaLookup(@Path("algorithm") String algorithm, @Path("version") String version, @Body Map<String, String> data);

    /**
     * Return a single schema definition by schema identifier
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a schema identifier
     * @return a schema object
     */
    @GET("/staging/{algorithm}/{version}/schema/{id}")
    StagingSchema schemaById(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String id);

    /**
     * Return a list of tables which are involved in the specified schema
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param schemaId a schema identifier
     */
    @GET("/staging/{algorithm}/{version}/schema/{id}/tables")
    List<StagingTable> involvedTables(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String schemaId);

    /**
     * Return a list of matching tables
     * @param algorithm an algorithm identifier
     * @param version a version
     * @return
     */
    @GET("/staging/{algorithm}/{version}/tables")
    List<StagingTable> tables(@Path("algorithm") String algorithm, @Path("version") String version);

    /**
     * Return a list of matching tables
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @return
     */
    @GET("/staging/{algorithm}/{version}/tables")
    List<StagingTable> tables(@Path("algorithm") String algorithm, @Path("version") String version, @Query("q") String query);

    /**
     * Return a single table definition by table identifier
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a table identifier
     * @return
     */
    @GET("/staging/{algorithm}/{version}/table/{id}")
    StagingTable tableById(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String id);

    /**
     * Return a list of schemas which the specified table is involved in
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param tableId a table identifier
     */
    @GET("/staging/{algorithm}/{version}/table/{id}/schemas")
    List<StagingSchema> involvedSchemas(@Path("algorithm") String algorithm, @Path("version") String version, @Path("id") String tableId);

    /**
     * Stage the passed input
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param input a map of key/value pairs containing the input for the staging call
     * @return
     */
    @POST("/staging/{algorithm}/{version}/stage")
    StagingData stage(@Path("algorithm") String algorithm, @Path("version") String version, @Body Map<String, String> input);

}
