/*
 * Copyright (C) 2016 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.mph;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MphService {

    /**
     * Uses multiple primary rules to compare two diseases
     * @param pair a pair of diseases
     * @return a result indicating whether the two diseases are the same primary
     */
    @POST("mph")
    Call<MphResult> mph(@Body MphInputPair pair);

}