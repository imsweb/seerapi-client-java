/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 * Manages gzip encoding to and from the API
 */
public class GzipInterceptor implements WriterInterceptor, ReaderInterceptor {

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        Object encoding = context.getHeaders().getFirst(HttpHeaders.CONTENT_ENCODING);

        if (encoding != null && encoding.toString().equalsIgnoreCase("gzip")) {
            OutputStream outputStream = context.getOutputStream();
            context.setOutputStream(new GZIPOutputStream(outputStream));
        }

        context.proceed();
    }

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        Object encoding = context.getHeaders().getFirst(HttpHeaders.CONTENT_ENCODING);

        if (encoding != null && encoding.toString().equalsIgnoreCase("gzip")) {
            InputStream originalInputStream = context.getInputStream();
            context.setInputStream(new GZIPInputStream(originalInputStream));
        }

        return context.proceed();
    }
}
