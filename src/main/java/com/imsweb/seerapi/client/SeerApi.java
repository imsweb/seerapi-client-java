/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import com.imsweb.seerapi.client.disease.DiseaseService;
import com.imsweb.seerapi.client.glossary.GlossaryService;
import com.imsweb.seerapi.client.hcpcs.HcpcsService;
import com.imsweb.seerapi.client.mph.MphService;
import com.imsweb.seerapi.client.naaccr.NaaccrService;
import com.imsweb.seerapi.client.ndc.NdcService;
import com.imsweb.seerapi.client.rx.RxService;
import com.imsweb.seerapi.client.siterecode.SiteRecodeService;
import com.imsweb.seerapi.client.staging.StagingService;
import com.imsweb.seerapi.client.surgery.SurgeryService;

/**
 * Entry point for Java API into SEER*API.
 */
public final class SeerApi {

    private final DiseaseService _diseaseService;
    private final GlossaryService _glossaryService;
    private final MphService _mphService;
    private final NaaccrService _naaccrService;
    private final NdcService _ndcService;
    private final RxService _rxService;
    private final SiteRecodeService _siteRecodeService;
    private final StagingService _stagingService;
    private final SurgeryService _surgeryService;
    private final HcpcsService _hcpcsService;

    /**
     * Creates a client API root object
     * @param baseUrl base URL for API
     * @param apiKey API key
     */
    private SeerApi(String baseUrl, final String apiKey) {
        if (!baseUrl.endsWith("/"))
            baseUrl += "/";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    // add the api key to all requests
                    Request request = original.newBuilder()
                            .header("Accept", "application/json")
                            .header("X-SEERAPI-Key", apiKey)
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                })
                .addInterceptor(new ErrorInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create(getMapper()))
                .client(client)
                .build();

        // create cached service entities
        _diseaseService = retrofit.create(DiseaseService.class);
        _glossaryService = retrofit.create(GlossaryService.class);
        _mphService = retrofit.create(MphService.class);
        _naaccrService = retrofit.create(NaaccrService.class);
        _ndcService = retrofit.create(NdcService.class);
        _rxService = retrofit.create(RxService.class);
        _siteRecodeService = retrofit.create(SiteRecodeService.class);
        _stagingService = retrofit.create(StagingService.class);
        _surgeryService = retrofit.create(SurgeryService.class);
        _hcpcsService = retrofit.create(HcpcsService.class);
    }

    /**
     * Return the internal ObjectMapper
     * @return an Objectmapper
     */
    static ObjectMapper getMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // do not write null values
        mapper.setSerializationInclusion(Include.NON_NULL);

        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        // set Date objects to output in readable customized format
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        mapper.setDateFormat(dateFormat);

        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }

    /**
     * Return the disease service
     * @return an interface to all the disease APIs
     */
    public DiseaseService disease() {
        return _diseaseService;
    }

    /**
     * Return the glossary service
     * @return an interface to all the glossary APIs
     */
    public GlossaryService glossary() {
        return _glossaryService;
    }

    /**
     * Return the multiple primary service
     * @return an interface to the multiple APIs
     */
    public MphService mph() {
        return _mphService;
    }

    /**
     * Return the NAACCR service
     * @return an inteface to all the NAACCR APIs
     */
    public NaaccrService naaccr() {
        return _naaccrService;
    }

    /**
     * Return the NDC service
     * @return an inteface to all the NDC APIs
     */
    public NdcService ndc() {
        return _ndcService;
    }

    /**
     * Return the Rx service
     * @return an inteface to all the Rx APIs
     */
    public RxService rx() {
        return _rxService;
    }

    /**
     * Return the site recode service
     * @return an interface to all the site recode APIs
     */
    public SiteRecodeService siteRecode() {
        return _siteRecodeService;
    }

    /**
     * Return the staging service
     * @return an interface to all the staging APIs
     */
    public StagingService staging() {
        return _stagingService;
    }

    /**
     * Return the surgery service
     * @return an interface to all the surgery APIs
     */
    public SurgeryService surgery() {
        return _surgeryService;
    }

    /**
     * Return the HCPCS service
     * @return an interface to all the HCPCS APIs
     */
    public HcpcsService hcpcs() {
        return _hcpcsService;
    }

    /**
     * Class to build a connection to SeerApi
     */
    public static class Builder {

        // default base URL
        private static final String _SEERAPI_URL = "https://api.seer.cancer.gov/rest/";

        // environment variable for URL and API key
        private static final String _ENV_URL = "SEER_API_URL";
        private static final String _ENV_API_KEY = "SEER_API_KEY";

        private String _url;
        private String _apiKey;

        /**
         * Return a list of user properties from the local .seerapi file
         * @return Properties object
         */
        private Properties getProperties() {
            Properties props = new Properties();

            File config = new File(System.getProperty("user.home"), ".seerapi");
            if (config.exists()) {

                try (FileInputStream in = new FileInputStream(config)) {
                    props.load(in);
                }
                catch (IOException e) {
                    // error reading
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
