/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import retrofit.RestAdapter;
import retrofit.converter.JacksonConverter;

import com.imsweb.seerapi.client.disease.DiseaseService;
import com.imsweb.seerapi.client.glossary.GlossaryService;
import com.imsweb.seerapi.client.naaccr.NaaccrService;
import com.imsweb.seerapi.client.rx.RxService;
import com.imsweb.seerapi.client.siterecode.SiteRecodeService;
import com.imsweb.seerapi.client.staging.StagingService;
import com.imsweb.seerapi.client.surgery.SurgeryService;

/**
 * Entry point for Java API into SEER*API.
 */
public final class SeerApi {

    // output all dates in ISO-8061 format and UTC time
    private static final DateFormat _DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    // define the JSON converter which uses Jackson and a customized ObjectMapper
    private static final JacksonConverter _JACKSON_CONVERTER;

    static {
        _DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));

        ObjectMapper mapper = new ObjectMapper();

        // do not write null values
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);

        // set Date objects to output in readable customized format
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(_DATE_FORMAT);

        _JACKSON_CONVERTER = new JacksonConverter(mapper);
    }

    RestAdapter _restAdapter;

    /**
     * Creates a client API root object
     * @param baseUrl base URL for API
     * @param apiKey API key
     */
    private SeerApi(String baseUrl, String apiKey) {
        if (baseUrl.endsWith("/"))
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);

        _restAdapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setConverter(_JACKSON_CONVERTER)
                .setRequestInterceptor(new SeerApiRequestInterceptor(apiKey))
                .setErrorHandler(new SeerApiErrorHandler())
                .build();
    }

    /**
     * Creates a connection to the API
     * @param baseUrl base URL for API
     * @param apiKey API key
     * @return a new SeerApi instance
     */
    protected static SeerApi connect(String baseUrl, String apiKey) {
        return new SeerApi(baseUrl, apiKey);
    }

    /**
     * Return the disease service
     * @return an interface to all the disease APIs
     */
    public DiseaseService disease() {
        return _restAdapter.create(DiseaseService.class);
    }

    /**
     * Return the glossary service
     * @return an interface to all the glossary APIs
     */
    public GlossaryService glossary() {
        return _restAdapter.create(GlossaryService.class);
    }

    /**
     * Return the NAACCR service
     * @return an inteface to all the NAACCR APIs
     */
    public NaaccrService naaccr() {
        return _restAdapter.create(NaaccrService.class);
    }

    /**
     * Return the Rx service
     * @return an inteface to all the Rx APIs
     */
    public RxService rx() {
        return _restAdapter.create(RxService.class);
    }

    /**
     * Return the site recode service
     * @return an interface to all the site recode APIs
     */
    public SiteRecodeService siteRecode() {
        return _restAdapter.create(SiteRecodeService.class);
    }

    /**
     * Return the staging service
     * @return an interface to all the staging APIs
     */
    public StagingService staging() {
        return _restAdapter.create(StagingService.class);
    }

    /**
     * Return the surgery service
     * @return an interface to all the surgery APIs
     */
    public SurgeryService surgery() {
        return _restAdapter.create(SurgeryService.class);
    }

}
