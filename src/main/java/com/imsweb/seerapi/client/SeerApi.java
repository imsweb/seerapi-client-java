/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

    /**
     * Class to build a connection to SeerApi
     */
    public static class Builder {

        // default base URL
        private static final String _SEERAPI_URL = "https://api.seer.cancer.gov/rest";

        // environment variable for URL and API key
        private static final String _ENV_URL = "SEER_API_URL";
        private static final String _ENV_API_KEY = "SEER_API_KEY";

        private String _url;
        private String _apiKey;

        /**
         * Return a list of user properties from the local .seerapi file
         * @return
         */
        private Properties getProperties() {
            Properties props = new Properties();

            File config = new File(System.getProperty("user.home"), ".seerapi");
            if (config.exists()) {
                FileInputStream in = null;

                try {
                    in = new FileInputStream(config);
                    props.load(in);
                }
                catch (IOException e) {
                    // error reading
                }
                finally {
                    try {
                        if (in != null)
                            in.close();
                    }
                    catch (IOException e) {
                        // do nothing if error closing stream
                    }
                }
            }

            return props;
        }

        /**
         * Constructor defaults url and key using the key stored in ~/.seerapi or the environment variable SEER_API_KEY
         */
        public Builder() {
            Properties props = getProperties();

            // if the URL is specified (either in properties file or environment), use it, otherwise use the default
            _url = props.getProperty("url");
            if (_url == null)
                _url = System.getenv(_ENV_URL);
            if (_url == null)
                _url = _SEERAPI_URL;

            // if the apikey does not exist, try to read it from the environment
            _apiKey = props.getProperty("apikey");
            if (_apiKey == null)
                _apiKey = System.getenv(_ENV_API_KEY);
        }

        public Builder url(String url) {
            _url = url;
            return this;
        }

        public Builder apiKey(String apiKey) {
            _apiKey = apiKey;
            return this;
        }

        public SeerApi connect() {
            return new SeerApi(_url, _apiKey);
        }
    }

}
