/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Interceptor to catch all non-200 responses and convert them to exceptions.
 */
public class ErrorInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        if (response.code() != 200) {
            ErrorResponse error = null;

            // convert body to error response
            ResponseBody body = response.body();
            if (body != null) {
                try {
                    error = new ObjectMapper().readValue(body.byteStream(), ErrorResponse.class);
                }
                catch (IOException e) {
                    // sometimes the error message is not right format (like for 404 errors)
                }
            }

            String message = error == null ? "Error code " + response.code() : error.getMessage();

            switch (response.code()) {
                case 401:  // unauthorized
                    throw new NotAuthorizedException(message);
                case 400:  // bad request
                    throw new BadRequestException(message);
                case 404:
                    throw new NotFoundException(message);
                default:
                    throw new SeerApiException(message);
            }
        }

        return response;
    }
}
