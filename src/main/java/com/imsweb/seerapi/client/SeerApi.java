/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;

import com.imsweb.seerapi.client.naaccr.NaaccrField;
import com.imsweb.seerapi.client.naaccr.NaaccrFieldName;
import com.imsweb.seerapi.client.naaccr.NaaccrVersion;
import com.imsweb.seerapi.client.shared.Version;
import com.imsweb.seerapi.client.siterecode.SiteRecode;

/**
 * Entry point for Java API into SEER*API.
 */
public final class SeerApi {

    private static final String _SEERAPI_URL = "https://api.seer.cancer.gov/rest";

    private Client _client;
    private String _baseUrl;
    private String _apiKey;

    /**
     * Creates a client API root object
     * @param baseUrl
     * @param apiKey
     * @throws IOException
     */
    private SeerApi(String baseUrl, String apiKey) throws IOException {
        if (baseUrl.endsWith("/"))
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);

        this._baseUrl = baseUrl;
        this._apiKey = apiKey;

        _client = ClientBuilder.newClient().register(JacksonFeature.class).register(GzipInterceptor.class);
    }

    /**
     * Creates a connection to the API using the key stored in ~/.seerapi
     * @return a new SeerApi instance
     * @throws IOException
     */
    public static SeerApi connect() throws IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(new File(System.getProperty("user.home"), ".seerapi"));

        try {
            props.load(in);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ioe) {
                // ignore
            }
        }

        return new SeerApi(_SEERAPI_URL, props.getProperty("apikey"));
    }

    /**
     * Creates a connection to the API
     * @param apiKey
     * @return a new SeerApi instance
     * @throws IOException
     */
    public static SeerApi connect(String apiKey) throws IOException {
        return new SeerApi(_SEERAPI_URL, apiKey);
    }

    /**
     * Creates a connection to the API
     * @param baseUrl
     * @param apiKey
     * @return a new SeerApi instance
     * @throws IOException
     */
    public static SeerApi connect(String baseUrl, String apiKey) throws IOException {
        return new SeerApi(baseUrl, apiKey);
    }

    /**
     * Helper method to return a base web target
     * @param path
     * @return a WebTarget using the base URL and the passed path
     */
    private WebTarget createTarget(String path) {
        if (_apiKey == null || _apiKey.isEmpty())
            throw new IllegalStateException("This operation requires a credential but none is given to the SeerApi constructor");

        return _client.target(_baseUrl).path(path);
    }

    /**
     * Builds the default invocation builder.  All requests are currently JSON and are GZIP encoded.
     * @param target
     * @return a Builder instance using the passed target and including default header information that is used on all our calls
     */
    private Invocation.Builder getBuilder(WebTarget target) {
        return target.request().header("X-SEERAPI-Key", _apiKey).accept(MediaType.APPLICATION_JSON_TYPE).acceptEncoding("gzip");
    }

    /**
     * Return the version of the SEER Site Recode database.
     * @return a String representing the database version
     */
    public String siteRecodeVersion() {
        WebTarget target = createTarget("/recode/version");

        return getBuilder(target).get(Version.class).getVersion();
    }

    /**
     * Return the SEER Site Group for the site/histology combination, or 99999 if the combination is unknown.
     * @param site
     * @param histology
     * @return a SiteRecode object based on the site and histology
     * @throws IOException
     */
    public SiteRecode siteRecode(String site, String histology) throws IOException {
        WebTarget target = createTarget("/recode/sitegroup").queryParam("site", site).queryParam("hist", histology);

        return getBuilder(target).get(SiteRecode.class);
    }

    /**
     * Return a collection of NaaccrVersion objects which descibe the available versions
     * @return a list of the available NAACCR versions and information about each of them
     */
    public List<NaaccrVersion> naaccrVersions() {
        WebTarget target = createTarget("/naaccr/versions");

        return getBuilder(target).get(new GenericType<List<NaaccrVersion>>() {});
    }

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR version
     * @param version
     * @return a list of NaaccrFieldName objects
     */
    public List<NaaccrFieldName> naaccrFieldNames(String version) {
        WebTarget target = createTarget("/naaccr/{version}").resolveTemplate("version", version);

        return getBuilder(target).get(new GenericType<List<NaaccrFieldName>>() {});
    }

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR version
     * @param version
     * @return a list of NaaccrFieldName objects
     */
    public NaaccrField naaccrField(String version, Integer item) {
        WebTarget target = createTarget("/naaccr/{version}/item/{item}").resolveTemplate("version", version).resolveTemplate("item", item);

        return getBuilder(target).get(NaaccrField.class);
    }
}
