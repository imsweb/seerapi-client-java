/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.glassfish.jersey.message.GZipEncoder;

import com.imsweb.seerapi.client.disease.Disease;
import com.imsweb.seerapi.client.disease.DiseaseChangelogResults;
import com.imsweb.seerapi.client.disease.DiseaseSearch;
import com.imsweb.seerapi.client.disease.DiseaseSearchResults;
import com.imsweb.seerapi.client.disease.DiseaseVersion;
import com.imsweb.seerapi.client.disease.PrimarySite;
import com.imsweb.seerapi.client.disease.SamePrimaries;
import com.imsweb.seerapi.client.disease.SiteCategory;
import com.imsweb.seerapi.client.glossary.Glossary;
import com.imsweb.seerapi.client.glossary.GlossaryChangelogResults;
import com.imsweb.seerapi.client.glossary.GlossarySearch;
import com.imsweb.seerapi.client.glossary.GlossarySearchResults;
import com.imsweb.seerapi.client.glossary.GlossaryVersion;
import com.imsweb.seerapi.client.naaccr.NaaccrField;
import com.imsweb.seerapi.client.naaccr.NaaccrFieldName;
import com.imsweb.seerapi.client.naaccr.NaaccrVersion;
import com.imsweb.seerapi.client.rx.Rx;
import com.imsweb.seerapi.client.rx.RxChangelogResults;
import com.imsweb.seerapi.client.rx.RxSearch;
import com.imsweb.seerapi.client.rx.RxSearchResults;
import com.imsweb.seerapi.client.rx.RxVersion;
import com.imsweb.seerapi.client.shared.Version;
import com.imsweb.seerapi.client.siterecode.SiteRecode;
import com.imsweb.seerapi.client.staging.SchemaLookup;
import com.imsweb.seerapi.client.staging.StagingAlgorithm;
import com.imsweb.seerapi.client.staging.StagingData;
import com.imsweb.seerapi.client.staging.StagingSchema;
import com.imsweb.seerapi.client.staging.StagingSchemaInfo;
import com.imsweb.seerapi.client.staging.StagingTable;
import com.imsweb.seerapi.client.staging.StagingVersion;
import com.imsweb.seerapi.client.surgery.SurgeryTable;

/**
 * Entry point for Java API into SEER*API.
 */
public final class SeerApi {

    // default base URL
    private static final String _SEERAPI_URL = "https://api.seer.cancer.gov/rest";

    // environment variable for URL and API key
    private static final String _ENV_URL = "SEER_API_URL";
    private static final String _ENV_API_KEY = "SEER_API_KEY";

    // output all dates in ISO-8061 format and UTC time
    private static final DateFormat _DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    // define the JSON provider which uses Jackson and a customized ObjectMapper
    private static final JacksonJsonProvider _JACKSON_PROVIDER = new JacksonJsonProvider();

    static {
        _DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));

        ObjectMapper mapper = new ObjectMapper();

        // do not write null values
        mapper.configure(Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(Inclusion.NON_NULL);

        // set Date objects to output in readable customized format
        mapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(_DATE_FORMAT);

        mapper.setVisibility(JsonMethod.ALL, Visibility.NONE);
        mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);

        _JACKSON_PROVIDER.setMapper(mapper);
    }

    private Client _client;
    private String _baseUrl;
    private String _apiKey;

    /**
     * Creates a client API root object
     * @param baseUrl base URL for API
     * @param apiKey API key
     */
    private SeerApi(String baseUrl, String apiKey) {
        if (baseUrl.endsWith("/"))
            baseUrl = baseUrl.substring(0, baseUrl.length() - 1);

        this._baseUrl = baseUrl;
        this._apiKey = apiKey;

        _client = ClientBuilder.newClient()
                .register(_JACKSON_PROVIDER)
                .register(GZipEncoder.class)
                .register(ErrorResponseFilter.class);
    }

    /**
     * Return a list of user properties from the local .seerapi file
     * @return
     */
    private static Properties getProperties() {
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
     * Creates a connection to the API using the key stored in ~/.seerapi or the environment variable SEER_API_KEY
     * @return a new SeerApi instance
     */
    public static SeerApi connect() {
        Properties props = getProperties();

        // if the URL is specified (either in properties file or environment), use it, otherwise use the default
        String url = props.getProperty("url");
        if (url == null)
            url = System.getenv(_ENV_URL);
        if (url == null)
            url = _SEERAPI_URL;

        // if the apikey does not exist, try to read it from the environment
        String apiKey = props.getProperty("apikey");
        if (apiKey == null)
            apiKey = System.getenv(_ENV_API_KEY);

        return new SeerApi(url, apiKey);
    }

    /**
     * Creates a connection to the API
     * @param apiKey API key
     * @return a new SeerApi instance
     */
    public static SeerApi connect(String apiKey) {
        Properties props = getProperties();

        // if the URL is specified, use it, otherwise use the default
        String url = props.getProperty("url");
        if (url == null)
            url = _SEERAPI_URL;

        return new SeerApi(url, apiKey);
    }

    /**
     * Creates a connection to the API
     * @param baseUrl base URL for API
     * @param apiKey API key
     * @return a new SeerApi instance
     */
    public static SeerApi connect(String baseUrl, String apiKey) {
        return new SeerApi(baseUrl, apiKey);
    }

    /**
     * Helper method to return a base web target
     * @param path the API path which is added onto the base URL
     * @return a WebTarget using the base URL and the passed path
     */
    private WebTarget createTarget(String path) {
        if (_apiKey == null || _apiKey.isEmpty())
            throw new IllegalStateException("This operation requires a credential but none is given to the SeerApi constructor");

        return _client.target(_baseUrl).path(path);
    }

    /**
     * Builds the default invocation builder.  All requests are currently JSON and are GZIP encoded.
     * @param target the WebTarget for the API call
     * @return a Builder instance using the passed target and including default header information that is used on all our calls
     */
    private Builder getBuilder(WebTarget target) {
        return target.request(MediaType.APPLICATION_JSON_TYPE).header("X-SEERAPI-Key", _apiKey).acceptEncoding("gzip");
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
     * @param site Primary Site O3
     * @param histology Histology O3
     * @return a SiteRecode object based on the site and histology
     */
    public SiteRecode siteRecode(String site, String histology) {
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
     * @param version NAACCR version
     * @return a list of NaaccrFieldName objects
     */
    public List<NaaccrFieldName> naaccrFieldNames(String version) {
        WebTarget target = createTarget("/naaccr/{version}").resolveTemplate("version", version);

        return getBuilder(target).get(new GenericType<List<NaaccrFieldName>>() {});
    }

    /**
     * Return a list of all the field identifiers and names from a specified NAACCR version
     * @param version NAACCR version
     * @param item NAACCR item number
     * @return a list of NaaccrFieldName objects
     */
    public NaaccrField naaccrField(String version, Integer item) {
        WebTarget target = createTarget("/naaccr/{version}/item/{item}").resolveTemplate("version", version).resolveTemplate("item", item);

        return getBuilder(target).get(NaaccrField.class);
    }

    /**
     * Return a list of all disease versions and information about them
     * @return a list of DiseaseVersion objects
     */
    public List<DiseaseVersion> diseaseVersions() {
        WebTarget target = createTarget("/disease/versions");

        return getBuilder(target).get(new GenericType<List<DiseaseVersion>>() {});
    }

    /**
     * Return the changelog entries for the passed database version
     * @param version Disease version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of DiseaseChangelogResults objects
     */
    public DiseaseChangelogResults diseaseChangelogs(String version, String fromDate, String toDate, Integer count) {
        WebTarget target = createTarget("/disease/{version}/changelog")
                .resolveTemplate("version", version)
                .queryParam("from", fromDate)
                .queryParam("to", toDate)
                .queryParam("count", count);

        return getBuilder(target).get(DiseaseChangelogResults.class);
    }

    /**
     * Return a list of matching diseases
     * @param version Disease version
     * @param search DiseaseSearch object
     * @return a DiseaseSearchResults object
     */
    public DiseaseSearchResults diseaseSearch(String version, DiseaseSearch search) {
        WebTarget target = createTarget("/disease/{version}").resolveTemplate("version", version);

        target = target.queryParam("q", search.getQuery())
                .queryParam("type", search.getType())
                .queryParam("site_category", search.getSiteCategory())
                .queryParam("mode", search.getMode())
                .queryParam("status", search.getStatus())
                .queryParam("assigned_to", search.getAssignedTo())
                .queryParam("modified_from", search.getModifiedFrom())
                .queryParam("modified_to", search.getModifiedTo())
                .queryParam("published_from", search.getPublishedFrom())
                .queryParam("published_to", search.getPublishedTo())
                .queryParam("been_published", search.getBeenPublished())
                .queryParam("hidden", search.getHidden())
                .queryParam("count", search.getCount())
                .queryParam("offset", search.getOffset())
                .queryParam("order", search.getOrderBy())
                .queryParam("output_type", search.getOutputType());

        return getBuilder(target).get(DiseaseSearchResults.class);
    }

    /**
     * Return a complete disease entity based in identifier.  Note that by default the disease entity does not include relevant glossary references.
     * @param version Disease version
     * @param id Disease identifier
     * @return a Disease object
     */
    public Disease diseaseById(String version, String id) {
        return diseaseById(version, id, false);
    }

    /**
     * Return a complete disease entity based in identifier
     * @param version Disease version
     * @param id Disease identifier
     * @param includeGlossary if true, include the glossary
     * @return a Disease object
     */
    public Disease diseaseById(String version, String id, boolean includeGlossary) {
        WebTarget target = createTarget("/disease/{version}/id/{id}")
                .resolveTemplate("version", version)
                .resolveTemplate("id", id)
                .queryParam("glossary", includeGlossary);

        return getBuilder(target).get(Disease.class);
    }

    /**
     * Return a list of all primary sites and labels
     * @return a List of PrimarySite objects
     */
    public List<PrimarySite> diseasePrimarySites() {
        WebTarget target = createTarget("/disease/primary_site");

        return getBuilder(target).get(new GenericType<List<PrimarySite>>() {});
    }

    /**
     * Return a single primary site and label
     * @param primarySite Primary Site O3
     * @return a PrimarySite object
     */
    public List<PrimarySite> diseasePrimarySiteCode(String primarySite) {
        WebTarget target = createTarget("/disease/primary_site/{code}").resolveTemplate("code", primarySite);

        return getBuilder(target).get(new GenericType<List<PrimarySite>>() {});
    }

    /**
     * Return a complete list of site categories and definitions
     * @return a list of SiteCategory objects
     */
    public List<SiteCategory> diseaseSiteCategories() {
        WebTarget target = createTarget("/disease/site_categories");

        return getBuilder(target).get(new GenericType<List<SiteCategory>>() {});
    }

    /**
     * Return whether the 2 morphologies represent the same primary for the given year.
     * @param version Disease version
     * @param morphology1 ICD O3 Morphology
     * @param morphology2 ICD O3 Morphology
     * @param year Year of Diagnosis
     * @return a SamePrimary object
     */
    public SamePrimaries diseaseSamePrimaries(String version, String morphology1, String morphology2, String year) {
        WebTarget target = createTarget("/disease/{version}/same_primary")
                .resolveTemplate("version", version)
                .queryParam("d1", morphology1)
                .queryParam("d2", morphology2)
                .queryParam("year", year);

        return getBuilder(target).get(SamePrimaries.class);
    }

    /**
     * Returns the reportable year range of the supplied disease.
     * @param disease Disease object
     * @return a Disease object with the reportability field filled in
     */
    public Disease diseaseReportability(Disease disease) {
        WebTarget target = createTarget("/disease/reportability");

        return getBuilder(target).post(Entity.json(disease), Disease.class);
    }

    /**
     * Return a list of all glossary versions and information about them
     * @return a list of GlossaryVersion objects
     */
    public List<GlossaryVersion> glossaryVersions() {
        WebTarget target = createTarget("/glossary/versions");

        return getBuilder(target).get(new GenericType<List<GlossaryVersion>>() {});
    }

    /**
     * Return a complete glossary entity based in identifier
     * @param version Glossary version
     * @param id Glossary identifier
     * @return a Glossary object
     */
    public Glossary glossaryById(String version, String id) {
        WebTarget target = createTarget("/glossary/{version}/id/{id}").resolveTemplate("version", version).resolveTemplate("id", id);

        return getBuilder(target).get(Glossary.class);
    }

    /**
     * Return a list of matching glossaries
     * @param version Glossary version
     * @param search GlossarySearch object
     * @return a GlossarySearchResults object
     */
    public GlossarySearchResults glossarySearch(String version, GlossarySearch search) {
        WebTarget target = createTarget("/glossary/{version}").resolveTemplate("version", version);

        target = target.queryParam("q", search.getQuery())
                .queryParam("mode", search.getMode())
                .queryParam("status", search.getStatus())
                .queryParam("assigned_to", search.getAssignedTo())
                .queryParam("modified_from", search.getModifiedFrom())
                .queryParam("modified_to", search.getModifiedTo())
                .queryParam("published_from", search.getPublishedFrom())
                .queryParam("published_to", search.getPublishedTo())
                .queryParam("been_published", search.getBeenPublished())
                .queryParam("hidden", search.getHidden())
                .queryParam("count", search.getCount())
                .queryParam("offset", search.getOffset())
                .queryParam("order", search.getOrderBy())
                .queryParam("output_type", search.getOutputType());

        // list parameters need to passed as an object array to get multiple query parameters; otherwise there is a single query
        // parameter with a list of values, which the API won't understand
        if (search.getCategory() != null)
            target = target.queryParam("category", search.getCategory().toArray());

        return getBuilder(target).get(GlossarySearchResults.class);
    }

    /**
     * Return the changelog entries for the passed database version
     * @param version Glossary version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of GlossaryChangelogResults objects
     */
    public GlossaryChangelogResults glossaryChangelogs(String version, String fromDate, String toDate, Integer count) {
        WebTarget target = createTarget("/glossary/{version}/changelog")
                .resolveTemplate("version", version)
                .queryParam("from", fromDate)
                .queryParam("to", toDate)
                .queryParam("count", count);

        return getBuilder(target).get(GlossaryChangelogResults.class);
    }

    /**
     * Return a collection of Version objects which descibe the available versions
     * @return a list of the available site-specific surgery versions and information about each of them
     */
    public List<Version> siteSpecificSurgeryVersions() {
        WebTarget target = createTarget("/surgery/versions");

        return getBuilder(target).get(new GenericType<List<Version>>() {});
    }

    /**
     * Return a list of all the site-specific surgery table titles from a specific version
     * @param version version
     * @return a list of site-specific surgery table titles
     */
    public List<String> siteSpecificSurgeryTables(String version) {
        WebTarget target = createTarget("/surgery/{version}/tables").resolveTemplate("version", version);

        return getBuilder(target).get(new GenericType<List<String>>() {});
    }

    /**
     * Return a specific site-specific surgary table from a specific version
     * @param version version
     * @param title site title (optional if the site/histology is provided)
     * @param site primary site (optional if the title is provided)
     * @param histology histology (optional if the title is provided)
     * @return a site-specific surgery table
     */
    public SurgeryTable siteSpecificSurgeryTable(String version, String title, String site, String histology) {
        WebTarget target = createTarget("/surgery/{version}/table")
                .resolveTemplate("version", version)
                .queryParam("title", title)
                .queryParam("site", site)
                .queryParam("hist", histology);

        return getBuilder(target).get(SurgeryTable.class);
    }

    /**
     * Return a list of all Rx versions and information about them.  Note that by default the Rx entity does not include relevant glossary references.
     * @return a list of RxVersion objects
     */
    public List<RxVersion> rxVersions() {
        WebTarget target = createTarget("/rx/versions");

        return getBuilder(target).get(new GenericType<List<RxVersion>>() {});
    }

    /**
     * Return a complete Rx entity based in identifier
     * @param version Rx version
     * @param id Rx identifier
     * @return a Rx object
     */
    public Rx rxById(String version, String id) {
        return rxById(version, id, false);
    }

    /**
     * Return a complete Rx entity based in identifier
     * @param version Rx version
     * @param id Rx identifier
     * @param includeGlossary if true, include the glossary
     * @return a Rx object
     */

    public Rx rxById(String version, String id, boolean includeGlossary) {
        WebTarget target = createTarget("/rx/{version}/id/{id}")
                .resolveTemplate("version", version)
                .resolveTemplate("id", id)
                .queryParam("glossary", includeGlossary);

        return getBuilder(target).get(Rx.class);
    }

    /**
     * Return a list of matching Rx entities
     * @param version Rx version
     * @param search RxSearch object
     * @return a RxSearchResults object
     */
    public RxSearchResults rxSearch(String version, RxSearch search) {
        WebTarget target = createTarget("/rx/{version}").resolveTemplate("version", version);

        target = target.queryParam("q", search.getQuery())
                .queryParam("type", search.getType())
                .queryParam("do_not_code", search.getDoNotCode())
                .queryParam("category", search.getCategory())
                .queryParam("mode", search.getMode())
                .queryParam("status", search.getStatus())
                .queryParam("assigned_to", search.getAssignedTo())
                .queryParam("modified_from", search.getModifiedFrom())
                .queryParam("modified_to", search.getModifiedTo())
                .queryParam("published_from", search.getPublishedFrom())
                .queryParam("published_to", search.getPublishedTo())
                .queryParam("been_published", search.getBeenPublished())
                .queryParam("hidden", search.getHidden())
                .queryParam("count", search.getCount())
                .queryParam("offset", search.getOffset())
                .queryParam("order", search.getOrderBy())
                .queryParam("output_type", search.getOutputType());

        return getBuilder(target).get(RxSearchResults.class);
    }

    /**
     * Return the changelog entries for the passed database version
     * @param version Rx version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of RxChangelogResults objects
     */
    public RxChangelogResults rxChangelogs(String version, String fromDate, String toDate, Integer count) {
        WebTarget target = createTarget("/rx/{version}/changelog")
                .resolveTemplate("version", version)
                .queryParam("from", fromDate)
                .queryParam("to", toDate)
                .queryParam("count", count);

        return getBuilder(target).get(RxChangelogResults.class);
    }

    /**
     * Return a list of all supported staging algorithms
     * @return a list of StagingAlgorithm objects
     */
    public List<StagingAlgorithm> stagingAlgorithms() {
        WebTarget target = createTarget("/staging/algorithms");

        return getBuilder(target).get(new GenericType<List<StagingAlgorithm>>() {});
    }

    /**
     * Return a list of supported versions for the passed algorithm
     * @param algorithm an algorithm identifier
     * @return
     */
    public List<StagingVersion> stagingAlgorithmVersions(String algorithm) {
        WebTarget target = createTarget("/staging/{algorithm}/versions").resolveTemplate("algorithm", algorithm);

        return getBuilder(target).get(new GenericType<List<StagingVersion>>() {});
    }

    /**
     * Return a list of matching schemas
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @return a list of schemas
     */
    public List<StagingSchemaInfo> stagingSchemas(String algorithm, String version, String query) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/schemas")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version)
                .queryParam("q", query);

        return getBuilder(target).get(new GenericType<List<StagingSchemaInfo>>() {});
    }

    /**
     * Perform a schema lookup
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param data a StagingData object containing the input for the lookup
     * @return
     */
    public List<StagingSchemaInfo> stagingSchemaLookup(String algorithm, String version, SchemaLookup data) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/schemas/lookup")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version);

        return getBuilder(target).post(Entity.json(data.getInputs()), new GenericType<List<StagingSchemaInfo>>() {});
    }

    /**
     * Return a single schema definition by schema identifier
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a schema identifier
     * @return a schema object
     */
    public StagingSchema stagingSchemaById(String algorithm, String version, String id) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/schema/{id}")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version)
                .resolveTemplate("id", id);

        return getBuilder(target).get(StagingSchema.class);
    }

    /**
     * Return a list of tables which are involved in the specified schema
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a schema identifier
     */
    public List<StagingTable> stagingSchemaInvolvedTables(String algorithm, String version, String id) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/schema/{id}/tables")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version)
                .resolveTemplate("id", id);

        return getBuilder(target).get(new GenericType<List<StagingTable>>() {});
    }

    /**
     * Return a list of matching tables
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param query an optional text query
     * @return
     */
    public List<StagingTable> stagingTables(String algorithm, String version, String query) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/tables")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version)
                .queryParam("q", query);

        return getBuilder(target).get(new GenericType<List<StagingTable>>() {});
    }

    /**
     * Return a single table definition by table identifier
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a table identifier
     * @return
     */
    public StagingTable stagingTableById(String algorithm, String version, String id) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/table/{id}")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version)
                .resolveTemplate("id", id);

        return getBuilder(target).get(StagingTable.class);
    }

    /**
     * Return a list of schemas which the specified table is involved in
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param id a table identifier
     */
    public List<StagingSchema> stagingTableInvolvedSchemas(String algorithm, String version, String id) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/table/{id}/schemas")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version)
                .resolveTemplate("id", id);

        return getBuilder(target).get(new GenericType<List<StagingSchema>>() {});
    }

    /**
     * Stage the passed input
     * @param algorithm an algorithm identifier
     * @param version a version
     * @param data a StagingData object containing the input for the staging call
     * @return
     */
    public StagingData stagingStage(String algorithm, String version, StagingData data) {
        WebTarget target = createTarget("/staging/{algorithm}/{version}/stage")
                .resolveTemplate("algorithm", algorithm)
                .resolveTemplate("version", version);

        return getBuilder(target).post(Entity.json(data.getInput()), StagingData.class);
    }

}
