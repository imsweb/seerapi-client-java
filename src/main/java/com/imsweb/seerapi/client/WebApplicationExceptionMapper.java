package com.imsweb.seerapi.client;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Map all RestEasy {@link javax.ws.rs.WebApplicationException} exceptions to a centralized mapper for consistency of output.
 */
@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException e) {
        int errorCode = e.getResponse().getStatus();
        String message = (String)e.getResponse().getEntity();

        return Response.status(errorCode).type(MediaType.valueOf(MediaType.APPLICATION_JSON)).entity(new ErrorResponseConverter(errorCode, message)).build();
    }

}
