/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.hcpcs;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface HcpcsService {

    /**
     * Return an HCPCS entity based on code.
     * @param code HCPCS code
     * @return an Hcpcs procedure
     */
    @GET("hcpcs/code/{code}")
    Call<Hcpcs> getProcedure(@Path("code") String code);

    /**
     * Return a list of matching Hcpcs procedures
     * @param searchParams A Map of search parameters.
     * @return a List of Hcpcs procedures
     */
    @GET("hcpcs")
    Call<List<Hcpcs>> search(@QueryMap Map<String, String> searchParams);

}
