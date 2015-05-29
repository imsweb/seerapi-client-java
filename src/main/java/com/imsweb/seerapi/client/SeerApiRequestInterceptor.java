/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import retrofit.RequestInterceptor;

/**
 * Interceptor for all API calls that adds API key to each request
 */
class SeerApiRequestInterceptor implements RequestInterceptor {

    private final String _apiKey;

    /**
     * Constructor
     * @param apiKey
     */
    public SeerApiRequestInterceptor(String apiKey) {
        _apiKey = apiKey;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("X-SEERAPI-Key", _apiKey);
    }
}
