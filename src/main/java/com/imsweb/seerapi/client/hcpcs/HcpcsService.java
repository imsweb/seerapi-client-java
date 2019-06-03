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
     */
    @GET("hcpcs/code/{code}")
    Call<Hcpcs> getProcedure(@Path("code") String code);

    /**
     * Return a list of matching HCPCS procedures
     */
    @GET("hcpcs")
    Call<List<Hcpcs>> search(@QueryMap Map<String, String> searchParams);

}
