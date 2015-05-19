/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to build a connection to SeerApi
 */
public class SeerApiBuilder {

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
    public SeerApiBuilder() {
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

    public SeerApiBuilder url(String url) {
        _url = url;
        return this;
    }

    public SeerApiBuilder apiKey(String apiKey) {
        _apiKey = apiKey;
        return this;
    }

    public SeerApi connect() {
        return SeerApi.connect(_url, _apiKey);
    }
}
