/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import retrofit.RestAdapter;

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
                .setConverter(new SeerApiJacksonConverter())
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
