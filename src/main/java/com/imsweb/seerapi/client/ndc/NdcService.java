/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface NdcService {

    /**
     * Return a complete NDC entity based on code.
     * @param code an NDC code
     * @return an NdcProduct object
     */
    @GET("ndc/code/{code}")
    Call<NdcProduct> getByCode(@Path("code") String code);

    /**
     * Return a list of matching NDC entities
     * @param searchParams A Map of search parameters.  Use NdcSearch to easily build parameter list.
     * @return a List of NdcProduct objects
     */
    @GET("ndc")
    Call<List<NdcProduct>> search(@QueryMap Map<String, String> searchParams);

}
