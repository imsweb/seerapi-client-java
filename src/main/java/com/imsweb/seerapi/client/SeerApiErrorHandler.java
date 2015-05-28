/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Handle various error conditions
 */
class SeerApiErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError cause) {
        Response r = cause.getResponse();

        if (r != null) {
            // convert body to error response
            ErrorResponse error = null;
            try {
                error = new ObjectMapper().readValue(r.getBody().in(), ErrorResponse.class);
            }
            catch (IOException e) {
                // if there was an error reading the body InputStream, default to the original error
            }

            String message = error == null ? cause.getMessage() : error.getMessage();

            switch (r.getStatus()) {
                case 401:  // unauthorized
                    return new NotAuthorizedException(message, cause);
                case 400:  // bad request
                    return new BadRequestException(message, cause);
                default:
                    return new SeerApiException(message, cause);

            }
        }

        return cause;
    }
}