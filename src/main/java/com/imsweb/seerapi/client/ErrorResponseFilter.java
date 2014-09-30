/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * This class is needed because the JAX-RS 2.0 client libraries are losing the "real" error messages.  SEER*API returns errors in
 * a JSON object that contains a status field and a message field.  I could not figure out a way to have it automatically unmarshall
 * it when non-200 codes happen, so I had to replcate the exception mapping here.  This *seems* to be working correctly, but I am sure
 * there is a much more elegant way to handle this.
 */
@Provider
public class ErrorResponseFilter implements ClientResponseFilter {

    private static ObjectMapper _MAPPER = new ObjectMapper();

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        // for non-200 response, deal with the custom error messages
        if (responseContext.getStatus() != Response.Status.OK.getStatusCode()) {
            if (responseContext.hasEntity()) {
                // get the "real" error message
                InputStream fullResponse = responseContext.getEntityStream();
                String encoding = responseContext.getHeaderString(HttpHeaders.CONTENT_ENCODING);
                if (encoding != null && encoding.contains("gzip"))
                    fullResponse = new GZIPInputStream(fullResponse);

                ErrorResponse error = _MAPPER.readValue(fullResponse, ErrorResponse.class);
                String message = error.getMessage();

                Response.Status status = Response.Status.fromStatusCode(responseContext.getStatus());
                WebApplicationException webAppException;
                switch (status) {
                    case BAD_REQUEST:
                        webAppException = new BadRequestException(message);
                        break;
                    case UNAUTHORIZED:
                        webAppException = new NotAuthorizedException(message);
                        break;
                    case FORBIDDEN:
                        webAppException = new ForbiddenException(message);
                        break;
                    case NOT_FOUND:
                        webAppException = new NotFoundException(message);
                        break;
                    case METHOD_NOT_ALLOWED:
                        webAppException = new NotAllowedException(message);
                        break;
                    case NOT_ACCEPTABLE:
                        webAppException = new NotAcceptableException(message);
                        break;
                    case UNSUPPORTED_MEDIA_TYPE:
                        webAppException = new NotSupportedException(message);
                        break;
                    case INTERNAL_SERVER_ERROR:
                        webAppException = new InternalServerErrorException(message);
                        break;
                    case SERVICE_UNAVAILABLE:
                        webAppException = new ServiceUnavailableException(message);
                        break;
                    default:
                        webAppException = new WebApplicationException(message);
                }

                throw webAppException;
            }
        }
    }
}
