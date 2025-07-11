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
     * Return a collection of NaaccrVersion objects which descibe the available versions
     * @return a list of the available NAACCR versions and information about each of them
     */
    @GET("naaccr/versions")
    Call<List<NaaccrVersion>> versions();

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR version
     * @param version NAACCR version
     * @return a list of NaaccrFieldName objects
     */
    @GET("naaccr/{version}")
    Call<List<NaaccrItemInfo>> fieldNames(@Path("version") String version);

    /**
     * Return a single item from a NAACCR version
     * @param version NAACCR version
     * @param item NAACCR item number or XML identifier
     * @return a NaaccrItem
     */
    @GET("naaccr/{version}/{item}")
    Call<NaaccrItem> item(@Path("version") String version, @Path("item") String item);

}
