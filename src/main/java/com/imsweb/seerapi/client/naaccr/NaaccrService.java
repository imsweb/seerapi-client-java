/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.naaccr;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NaaccrService {

    /**
     * Return a collection of NaaccrVersion objects which descibe the available flat file versions
     * @return a list of the available NAACCR versions and information about each of them
     */
    @GET("naaccr/flat/versions")
    Call<List<NaaccrVersion>> flatVersions();

    /**
     * Return a collection of NaaccrVersion objects which descibe the available XML versions
     * @return a list of the available NAACCR versions and information about each of them
     */
    @GET("naaccr/xml/versions")
    Call<List<NaaccrVersion>> xmlVersions();

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR flat file version
     * @param version NAACCR version
     * @return a list of NaaccrFieldName objects
     */
    @GET("naaccr/flat/{version}")
    Call<List<NaaccrFieldName>> flatFieldNames(@Path("version") String version);

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR XML version
     * @param version NAACCR version
     * @return a list of NaaccrFieldName objects
     */
    @GET("naaccr/xml/{version}")
    Call<List<NaaccrFieldName>> xmlFieldNames(@Path("version") String version);

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR flat file version
     * @param version NAACCR version
     * @param item NAACCR item number
     * @return a list of NaaccrFieldName objects
     */
    @GET("naaccr/flat/{version}/item/{item}")
    Call<NaaccrFlatField> flatField(@Path("version") String version, @Path("item") Integer item);

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR XML version
     * @param version NAACCR version
     * @param id NAACCR XML id
     * @return a list of NaaccrFieldName objects
     */
    @GET("naaccr/xml/{version}/id/{id}")
    Call<NaaccrXmlField> xmlField(@Path("version") String version, @Path("id") String id);

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR XML version
     * @param version NAACCR version
     * @param item NAACCR item number
     * @return a list of NaaccrFieldName objects
     */
    @GET("naaccr/xml/{version}/item/{item}")
    Call<NaaccrXmlField> xmlField(@Path("version") String version, @Path("item") Integer item);

}
