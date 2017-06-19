/*
 * Copyright (C) 2016 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.mph;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

import com.imsweb.seerapi.client.mph.MphInput.HistologyMatchMode;

public interface MphService {

    /**
     * Uses multiple primary rules to compare two diseases using strict histology matching mode
     * @param pair a pair of diseases
     * @return a result indicating whether the two diseases are the same primary
     */
    @POST("mph")
    Call<MphOutput> mph(@Body MphInputPair pair);

    /**
     * Uses multiple primary rules to compare two diseases
     * @param pair a pair of diseases
     * @param matchMode
     * @return a result indicating whether the two diseases are the same primary
     */
    @POST("mph")
    Call<MphOutput> mph(@Body MphInputPair pair, @Query("histology-matching-mode") HistologyMatchMode matchMode);

}