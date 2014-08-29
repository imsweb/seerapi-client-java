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
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.imsweb.seerapi.client.cs.CsCodeValidity;
import com.imsweb.seerapi.client.cs.CsInput;
import com.imsweb.seerapi.client.cs.CsResult;
import com.imsweb.seerapi.client.cs.CsSchema;
import com.imsweb.seerapi.client.cs.CsSchemaExistence;
import com.imsweb.seerapi.client.cs.CsSchemaName;
import com.imsweb.seerapi.client.cs.CsTable;
import com.imsweb.seerapi.client.cs.CsVersion;
import com.imsweb.seerapi.client.disease.Disease;
import com.imsweb.seerapi.client.disease.DiseaseChangelog;
import com.imsweb.seerapi.client.disease.DiseaseSearch;
import com.imsweb.seerapi.client.disease.DiseaseSearchResults;
import com.imsweb.seerapi.client.disease.DiseaseVersion;
import com.imsweb.seerapi.client.disease.PrimarySite;
import com.imsweb.seerapi.client.disease.SamePrimaries;
import com.imsweb.seerapi.client.disease.SiteCategory;
import com.imsweb.seerapi.client.glossary.Glossary;
import com.imsweb.seerapi.client.glossary.GlossaryChangelog;
import com.imsweb.seerapi.client.glossary.GlossarySearch;
import com.imsweb.seerapi.client.glossary.GlossarySearchResults;
import com.imsweb.seerapi.client.glossary.GlossaryVersion;
import com.imsweb.seerapi.client.naaccr.NaaccrField;
import com.imsweb.seerapi.client.naaccr.NaaccrFieldName;
import com.imsweb.seerapi.client.naaccr.NaaccrVersion;
import com.imsweb.seerapi.client.rx.Rx;
import com.imsweb.seerapi.client.rx.RxChangelog;
import com.imsweb.seerapi.client.rx.RxSearch;
import com.imsweb.seerapi.client.rx.RxSearchResults;
import com.imsweb.seerapi.client.rx.RxVersion;
import com.imsweb.seerapi.client.shared.Version;
import com.imsweb.seerapi.client.siterecode.SiteRecode;

/**
 * Entry point for Java API into SEER*API.
 */
public final class SeerApi {

    // default base URL
    private static final String _SEERAPI_URL = "https://api.seer.cancer.gov/rest";

    // environment variable for API key
    private static final String _ENV_API_KEY = "SEER_API_KEY";

    // output all dates in ISO-8061 format and UTC time
    private static final DateFormat _DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    // define the JSON provider which uses Jackson and a customized ObjectMapper
    private static final JacksonJsonProvider _JACKSON_PROVIDER = new JacksonJsonProvider();

    static {
        _DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));

        ObjectMapper mapper = new ObjectMapper();

        // do not write null values
        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        // set Date objects to output in readable customized format
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.setDateFormat(_DATE_FORMAT);

        mapper.setVisibility(JsonMethod.ALL, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);

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

        _client = ClientBuilder.newClient().register(_JACKSON_PROVIDER).register(GzipInterceptor.class).register(ErrorResponseFilter.class);
    }

    /**
     * Creates a connection to the API using the key stored in ~/.seerapi or the environment variable SEER_API_KEY
     * @return a new SeerApi instance
     * @throws IOException if there is an error reading local .seerapi file
     */
    public static SeerApi connect() throws IOException {
        Properties props = new Properties();

        File config = new File(System.getProperty("user.home"), ".seerapi");
        if (config.exists()) {
            FileInputStream in = new FileInputStream(config);

            try {
                props.load(in);
            }
            finally {
                in.close();
            }
        }

        // if the apikey does not exist, try to read it from the environment
        String apiKey = props.getProperty("apikey");
        if (apiKey == null)
            apiKey = System.getenv(_ENV_API_KEY);

        return new SeerApi(_SEERAPI_URL, apiKey);
    }

    /**
     * Creates a connection to the API
     * @param apiKey API key
     * @return a new SeerApi instance
     */
    public static SeerApi connect(String apiKey) {
        return new SeerApi(_SEERAPI_URL, apiKey);
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
     * Return a collection of CsVersion objects which descibe the available versions
     * @return a list of the available Collaborative Staging versions and information about each of them
     */
    public List<CsVersion> csVersions() {
        WebTarget target = createTarget("/cstage/versions");

        return getBuilder(target).get(new GenericType<List<CsVersion>>() {});
    }

    /**
     * Return the list of schema names and identifiers for the specifid version
     * @param version CS version
     * @return a list of CsCschemaName objects
     */
    public List<CsSchemaName> csSchemas(String version) {
        WebTarget target = createTarget("/cstage/{version}").resolveTemplate("version", version);

        return getBuilder(target).get(new GenericType<List<CsSchemaName>>() {});
    }

    /**
     * Checks whether a site/histology combination maps to an existing schema for the passed CS version
     * @param version CS version
     * @param site Primary Site O3
     * @param histology Histology O3
     * @return a CsSchemaExistence object which includes information about the matching schema
     */
    public CsSchemaExistence csSchemaExists(String version, String site, String histology) {
        WebTarget target = createTarget("/cstage/{version}/check_schema_exists").resolveTemplate("version", version).queryParam("site", site).queryParam("hist", histology);

        return getBuilder(target).get(CsSchemaExistence.class);
    }

    /**
     * Return a full schema based on schema number
     * @param version CS version
     * @param schemaNumber CS schema number
     * @return a csSchema object
     */
    public CsSchema csSchema(String version, Integer schemaNumber) {
        WebTarget target = createTarget("/cstage/{version}/schema").resolveTemplate("version", version).queryParam("id", schemaNumber);

        return getBuilder(target).get(CsSchema.class);
    }

    /**
     * Return a full schema based on primary site, histology and site-specific factor 25
     * @param version CS version
     * @param site Primary Site O3
     * @param histology Histology O3
     * @param ssf25 Site-specific Factor 25
     * @return a CsSchema object
     */
    public CsSchema csSchema(String version, String site, String histology, String ssf25) {
        WebTarget target = createTarget("/cstage/{version}/schema").resolveTemplate("version", version).queryParam("site", site).queryParam("hist", histology).queryParam("ssf25", ssf25);

        return getBuilder(target).get(CsSchema.class);
    }

    /**
     * Tests whether a code is valid or obsolete.
     * @param version CS version
     * @param schemaNumber CS schema number
     * @param tableNumber CS table number
     * @param code code to check for validity
     * @return a CsCodeValidity object indicating validity and whether the code is obsolete
     */
    public CsCodeValidity csValidCode(String version, Integer schemaNumber, Integer tableNumber, String code) {
        WebTarget target = createTarget("/cstage/{version}/is_code_valid").resolveTemplate("version", version).queryParam("id", schemaNumber).queryParam("table", tableNumber).queryParam("code", code);

        return getBuilder(target).get(CsCodeValidity.class);
    }

    /**
     * Return the specified CS table
     * @param version CS version
     * @param schemaNumber CS schema number
     * @param tableNumber CS table number
     * @return a CsTable object
     */
    public CsTable csTable(String version, Integer schemaNumber, Integer tableNumber) {
        WebTarget target = createTarget("/cstage/{version}/table").resolveTemplate("version", version).queryParam("id", schemaNumber).queryParam("table", tableNumber);

        return getBuilder(target).get(CsTable.class);
    }

    /**
     * Calculate collaborative stage
     * @param version CS version
     * @param input Input properties used in calculation
     * @return a CsResult object containing the staging results
     */
    public CsResult csCalculate(String version, CsInput input) {
        WebTarget target = createTarget("/cstage/{version}/calculate").resolveTemplate("version", version);

        target = target.queryParam("site", input.getSite()).queryParam("hist", input.getHistology()).queryParam("diagnosis_year", input.getDiagnosisYear()).queryParam("csver_original",
                input.getCsVersionOriginal()).queryParam("behav", input.getBehavior()).queryParam("grade", input.getGrade()).queryParam("age", input.getAge()).queryParam("lvi", input.getLvi())
                .queryParam("size", input.getTumorSize()).queryParam("ext", input.getExtension()).queryParam("exteval", input.getExtensionEval()).queryParam("nodes", input.getLymphNodes()).queryParam(
                        "nodeseval", input.getLymphNodesEval()).queryParam("lnpos", input.getLymphNodesPositive()).queryParam("lnexam", input.getLymphNodesExamined()).queryParam("mets",
                        input.getMetsAtDx()).queryParam("metseval", input.getMetsEval()).queryParam("ssf1", input.getSsf1()).queryParam("ssf2", input.getSsf2()).queryParam("ssf3", input.getSsf3())
                .queryParam("ssf4", input.getSsf4()).queryParam("ssf5", input.getSsf5()).queryParam("ssf6", input.getSsf6()).queryParam("ssf7", input.getSsf7()).queryParam("ssf8", input.getSsf8())
                .queryParam("ssf9", input.getSsf9()).queryParam("ssf10", input.getSsf10()).queryParam("ssf11", input.getSsf11()).queryParam("ssf12", input.getSsf12()).queryParam("ssf13",
                        input.getSsf13()).queryParam("ssf14", input.getSsf14()).queryParam("ssf15", input.getSsf15()).queryParam("ssf16", input.getSsf16()).queryParam("ssf17", input.getSsf17())
                .queryParam("ssf18", input.getSsf18()).queryParam("ssf19", input.getSsf19()).queryParam("ssf10", input.getSsf20()).queryParam("ssf21", input.getSsf21()).queryParam("ssf22",
                        input.getSsf22()).queryParam("ssf23", input.getSsf23()).queryParam("ssf24", input.getSsf24()).queryParam("ssf25", input.getSsf25());

        return getBuilder(target).get(CsResult.class);
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
     * @return a list of DiseaseChangelog objects
     */
    public List<DiseaseChangelog> diseaseChangelogs(String version, String fromDate, String toDate, Integer count) {
        WebTarget target = createTarget("/disease/{version}/changelog").resolveTemplate("version", version).queryParam("from", fromDate).queryParam("to", toDate).queryParam("count", count);

        return getBuilder(target).get(new GenericType<List<DiseaseChangelog>>() {});
    }

    /**
     * Return a list of matching diseases
     * @param version Disease version
     * @param search DiseaseSearch object
     * @return a DiseaseSearchResults object
     */
    public DiseaseSearchResults diseaseSearch(String version, DiseaseSearch search) {
        WebTarget target = createTarget("/disease/{version}").resolveTemplate("version", version);

        target = target.queryParam("q", search.getQuery()).queryParam("type", search.getType()).queryParam("site_category", search.getSiteCategory()).queryParam("mode", search.getMode()).queryParam(
                "status", search.getStatus()).queryParam("assigned_to", search.getAssignedTo()).queryParam("modified_from", search.getModifiedFrom()).queryParam("modified_to", search.getModifiedTo())
                .queryParam("published_from", search.getPublishedFrom()).queryParam("published_to", search.getPublishedTo()).queryParam("been_published", search.getBeenPublished()).queryParam(
                        "hidden", search.getHidden()).queryParam("count", search.getCount()).queryParam("count_only", search.getCountOnly()).queryParam("glossary", search.getIncludeGlossary())
                .queryParam("output_type", search.getOutputType());

        return getBuilder(target).get(DiseaseSearchResults.class);
    }

    /**
     * Return a complete disease entity based in identifier
     * @param version Disease version
     * @param id Disease identifier
     * @return a Disease object
     */
    public Disease diseaseById(String version, String id) {
        WebTarget target = createTarget("/disease/{version}/id/{id}").resolveTemplate("version", version).resolveTemplate("id", id);

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
        WebTarget target = createTarget("/disease/{version}/same_primary").resolveTemplate("version", version).queryParam("d1", morphology1).queryParam("d2", morphology2).queryParam("year", year);

        return getBuilder(target).get(SamePrimaries.class);
    }

    /**
     * Returns the reportable year range of the supplied disease.
     * @param disease Disease object
     * @return a Disease object with the reportability field filled in
     */
    public Disease diseaseReportability(Disease disease) {
        WebTarget target = createTarget("/disease/reportability");

        Entity<Disease> entity = Entity.json(disease);
        return getBuilder(target).post(entity, Disease.class);
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

        target = target.queryParam("q", search.getQuery()).queryParam("mode", search.getMode()).queryParam("status", search.getStatus()).queryParam(
                "assigned_to", search.getAssignedTo()).queryParam("modified_from", search.getModifiedFrom()).queryParam("modified_to", search.getModifiedTo()).queryParam("published_from",
                search.getPublishedFrom()).queryParam("published_to", search.getPublishedTo()).queryParam("been_published", search.getBeenPublished()).queryParam("hidden", search.getHidden())
                .queryParam("count", search.getCount()).queryParam("count_only", search.getCountOnly()).queryParam("glossary", search.getIncludeGlossary()).queryParam("output_type",
                        search.getOutputType());

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
     * @return a list of GlossaryChangelog objects
     */
    public List<GlossaryChangelog> glossaryChangelogs(String version, String fromDate, String toDate, Integer count) {
        WebTarget target = createTarget("/glossary/{version}/changelog").resolveTemplate("version", version).queryParam("from", fromDate).queryParam("to", toDate).queryParam("count", count);

        return getBuilder(target).get(new GenericType<List<GlossaryChangelog>>() {});
    }

    /**
     * Return a list of all Rx versions and information about them
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
        WebTarget target = createTarget("/rx/{version}/id/{id}").resolveTemplate("version", version).resolveTemplate("id", id);

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

        target = target.queryParam("q", search.getQuery()).queryParam("type", search.getType()).queryParam("do_not_code", search.getDoNotCode())
                .queryParam("category", search.getCategory()).queryParam("mode", search.getMode()).queryParam("status", search.getStatus())
                .queryParam("assigned_to", search.getAssignedTo()).queryParam("modified_from", search.getModifiedFrom()).queryParam("modified_to", search.getModifiedTo())
                .queryParam("published_from", search.getPublishedFrom()).queryParam("published_to", search.getPublishedTo())
                .queryParam("been_published", search.getBeenPublished()).queryParam("hidden", search.getHidden()).queryParam("count", search.getCount())
                .queryParam("count_only", search.getCountOnly()).queryParam("glossary", search.getIncludeGlossary()).queryParam("output_type", search.getOutputType());

        return getBuilder(target).get(RxSearchResults.class);
    }

    /**
     * Return the changelog entries for the passed database version
     * @param version Rx version
     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
     * @param count if not null, limit the number returned
     * @return a list of RxChangelog objects
     */
    public List<RxChangelog> rxChangelogs(String version, String fromDate, String toDate, Integer count) {
        WebTarget target = createTarget("/rx/{version}/changelog").resolveTemplate("version", version).queryParam("from", fromDate).queryParam("to", toDate).queryParam("count", count);

        return getBuilder(target).get(new GenericType<List<RxChangelog>>() {});
    }

}
