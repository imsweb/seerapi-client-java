/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NdcService {

    /**
     * Return a complete NDC entity based on code.
     * @param code an NDC code
     * @return an NDCProduct object
     */
    @GET("ndc/code/{code}")
    Call<NdcProduct> getByCode(@Path("code") String code);

}
