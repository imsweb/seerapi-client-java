/*
 * Copyright (C) 2024 Information Management Services, Inc.
 */
package com.imsweb.seerapi.compare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.disease.Disease;
import com.imsweb.seerapi.client.disease.DiseaseSearchResults;
import com.imsweb.seerapi.client.disease.DiseaseService;
import com.imsweb.seerapi.client.glossary.Glossary;
import com.imsweb.seerapi.client.glossary.GlossarySearchResults;
import com.imsweb.seerapi.client.glossary.GlossaryService;
import com.imsweb.seerapi.client.hcpcs.Hcpcs;
import com.imsweb.seerapi.client.hcpcs.HcpcsService;
import com.imsweb.seerapi.client.ndc.NdcProduct;
import com.imsweb.seerapi.client.ndc.NdcService;
import com.imsweb.seerapi.client.rx.Rx;
import com.imsweb.seerapi.client.rx.RxSearchResults;
import com.imsweb.seerapi.client.rx.RxService;
import com.imsweb.seerapi.client.staging.StagingSchema;
import com.imsweb.seerapi.client.staging.StagingSchemaInfo;
import com.imsweb.seerapi.client.staging.StagingService;
import com.imsweb.seerapi.client.staging.StagingTable;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled("Only for manual testing")
class ComparisonTest {

    private static final String PROD_URL = "https://api.seer.cancer.gov/rest/";
    private static final String LOCAL_URL = "http://localhost:8080/rest/";

    private String getApiKey() {
        return System.getenv("TESTING_API_KEY");
    }

    @Test
    void testNdc() throws IOException {
        NdcService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().ndc();
        NdcService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().ndc();

        long page = 1;

        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", "100");
        params.put("order", "ndc");
        List<NdcProduct> prodList = prodService.search(params).execute().body();
        List<NdcProduct> localList = localService.search(params).execute().body();

        while (!Objects.requireNonNull(prodList).isEmpty() && !Objects.requireNonNull(localList).isEmpty()) {
            assertThat(localList)
                    .hasSameSizeAs(prodList)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .isEqualTo(prodList);

            System.out.println("NDC page " + page);

            page += 1;
            params.put("page", String.valueOf(page));
            prodList = prodService.search(params).execute().body();
            localList = localService.search(params).execute().body();
        }
    }

    @Test
    void testHcpcs() throws IOException {
        HcpcsService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().hcpcs();
        HcpcsService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().hcpcs();

        long page = 1;

        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", "100");
        params.put("order", "hcpcs_code");

        List<Hcpcs> prodList = prodService.search(params).execute().body();
        List<Hcpcs> localList = localService.search(params).execute().body();

        while (!Objects.requireNonNull(prodList).isEmpty()) {
            System.out.println("HCPCS page " + page);
            assertThat(localList)
                    .hasSameSizeAs(prodList)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .ignoringCollectionOrderInFields("categories")
                    .isEqualTo(prodList);

            page += 1;
            params.put("page", String.valueOf(page));
            prodList = prodService.search(params).execute().body();
            localList = localService.search(params).execute().body();
        }
    }

    private Map<String, String> getAlgorithmVersions() {
        Map<String, String> versions = new LinkedHashMap<>();

        versions.put("pediatric", "1.2");
        versions.put("eod_public", "3.2");
        versions.put("tnm", "2.0");
        versions.put("cs", "02.05.50");

        return versions;
    }

    @Test
    void testStagingSchemas() throws IOException {
        StagingService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().staging();
        StagingService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().staging();

        Map<String, String> algorithmMap = getAlgorithmVersions();
        for (String algorithmId : algorithmMap.keySet()) {
            String algorithmVersion = algorithmMap.get(algorithmId);

            System.out.println("Getting list of all schemas in " + algorithmId + ":" + algorithmVersion);

            List<StagingSchemaInfo> prodSchemas = prodService.schemas(algorithmId, algorithmVersion).execute().body();
            List<StagingSchemaInfo> localSchemas = localService.schemas(algorithmId, algorithmVersion).execute().body();

            assertThat(localSchemas)
                    .hasSameSizeAs(prodSchemas)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .ignoringCollectionOrder()
                    .isEqualTo(prodSchemas);

            for (StagingSchemaInfo prodSchema : Objects.requireNonNull(prodSchemas)) {
                StagingSchema prod = prodService.schemaById(algorithmId, algorithmVersion, prodSchema.getId()).execute().body();
                StagingSchema local = localService.schemaById(algorithmId, algorithmVersion, prodSchema.getId()).execute().body();

                System.out.println("Comparing [" + algorithmId + ":" + algorithmVersion + "] schema " + prodSchema.getId());

                assertThat(local)
                        .usingRecursiveComparison()
                        .ignoringCollectionOrder()
                        .withComparatorForType(
                                (date1, date2) -> {
                                    // ignore milliseconds when comparing two Date objects
                                    long time1 = date1.getTime() / 1000;
                                    long time2 = date2.getTime() / 1000;
                                    return Long.compare(time1, time2);
                                },
                                Date.class
                        )
                        .isEqualTo(prod);
            }
        }
    }

    @Test
    void testStagingTables() throws IOException {
        StagingService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().staging();
        StagingService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().staging();

        Map<String, String> algorithmMap = getAlgorithmVersions();
        for (String algorithmId : algorithmMap.keySet()) {
            String algorithmVersion = algorithmMap.get(algorithmId);

            System.out.println("Getting list of all tables in " + algorithmId + ":" + algorithmVersion);

            List<StagingTable> prodTables = prodService.tables(algorithmId, algorithmVersion).execute().body();
            List<StagingTable> localTables = localService.tables(algorithmId, algorithmVersion).execute().body();

            assertThat(localTables)
                    .hasSameSizeAs(prodTables)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .ignoringCollectionOrder()
                    .isEqualTo(prodTables);

            for (StagingTable prodTable : Objects.requireNonNull(prodTables)) {
                StagingTable prod = prodService.tableById(algorithmId, algorithmVersion, prodTable.getId()).execute().body();
                StagingTable local = localService.tableById(algorithmId, algorithmVersion, prodTable.getId()).execute().body();

                System.out.println("Comparing [" + algorithmId + ":" + algorithmVersion + "] table " + prodTable.getId());

                assertThat(local)
                        .usingRecursiveComparison()
                        .withComparatorForType(
                                (date1, date2) -> {
                                    // ignore milliseconds when comparing two Date objects
                                    long time1 = date1.getTime() / 1000;
                                    long time2 = date2.getTime() / 1000;
                                    return Long.compare(time1, time2);
                                },
                                Date.class
                        )
                        .isEqualTo(prod);
            }
        }
    }

    @Test
    void testGlossary() throws IOException {
        GlossaryService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().glossary();
        GlossaryService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().glossary();

        Map<String, String> params = new HashMap<>();

        long offset = 0;
        long perPage = 100;
        params.put("offset", String.valueOf(offset));
        params.put("count", String.valueOf(perPage));

        // get a list of ids
        List<String> ids = new ArrayList<>();

        System.out.println("Collecting identifiers for glossary 'latest' version");

        GlossarySearchResults prod = prodService.search("latest", params).execute().body();

        while (Objects.requireNonNull(prod).getResults() != null && !prod.getResults().isEmpty()) {
            for (Glossary glossary : prod.getResults())
                ids.add(glossary.getId());

            offset += perPage;
            params.put("offset", String.valueOf(offset));

            prod = prodService.search("latest", params).execute().body();
        }

        System.out.println("Found " + ids.size() + " identifiers for glossary 'latest' version");

        for (String id : ids) {
            System.out.println("Comparing " + id);
            Glossary prodGlossary = prodService.getById("latest", id).execute().body();
            Glossary localGlossary = localService.getById("latest", id).execute().body();

            assertThat(localGlossary)
                    .usingRecursiveComparison()
                    .withComparatorForType(
                            (value1, value2) -> {
                                // Treat null and empty string as equal
                                if (value1 == null && "".equals(value2) || "".equals(value1) && value2 == null) {
                                    return 0; // Consider them equal
                                }
                                if (value1 == null)
                                    return -1;
                                if (value2 == null)
                                    return 1;
                                return value1.compareTo(value2);
                            },
                            String.class  // Apply to all String fields, including in lists
                    )
                    .isEqualTo(prodGlossary);
        }
    }

    @Test
    void testRx() throws IOException {
        RxService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().rx();
        RxService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().rx();

        Map<String, String> params = new HashMap<>();

        long offset = 0;
        long perPage = 100;
        params.put("offset", String.valueOf(offset));
        params.put("count", String.valueOf(perPage));

        // get a list of ids
        List<String> ids = new ArrayList<>();

        System.out.println("Collecting identifiers for rx 'latest' version");

        RxSearchResults prod = prodService.search("latest", params).execute().body();

        while (Objects.requireNonNull(prod).getResults() != null && !prod.getResults().isEmpty()) {
            for (Rx rx : prod.getResults())
                ids.add(rx.getId());

            offset += perPage;
            params.put("offset", String.valueOf(offset));

            prod = prodService.search("latest", params).execute().body();
        }

        System.out.println("Found " + ids.size() + " identifiers for rx 'latest' version");

        for (String id : ids) {
            System.out.println("Comparing " + id);
            Rx prodRx = prodService.getById("latest", id).execute().body();
            Rx localRx = localService.getById("latest", id).execute().body();

            assertThat(localRx)
                    .usingRecursiveComparison()
                    .isEqualTo(prodRx);
        }
    }

    @Test
    void testDisease() throws IOException {
        DiseaseService prodService = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().disease();
        DiseaseService localService = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().disease();

        Map<String, String> params = new HashMap<>();

        long offset = 0;
        long perPage = 100;
        params.put("offset", String.valueOf(offset));
        params.put("count", String.valueOf(perPage));

        // get a list of ids
        List<String> ids = new ArrayList<>();

        System.out.println("Collecting identifiers for disease 'latest' version");

        DiseaseSearchResults prod = prodService.search("latest", params).execute().body();

        while (Objects.requireNonNull(prod).getResults() != null && !prod.getResults().isEmpty()) {
            for (Disease disease : prod.getResults())
                ids.add(disease.getId());

            offset += perPage;
            params.put("offset", String.valueOf(offset));

            prod = prodService.search("latest", params).execute().body();
        }

        System.out.println("Found " + ids.size() + " identifiers for disease 'latest' version");

        for (String id : ids) {
            System.out.println("Comparing " + id);
            Disease prodDisease = prodService.getById("latest", id).execute().body();
            Disease localDisease = localService.getById("latest", id).execute().body();

            assertThat(localDisease)
                    .usingRecursiveComparison()
                    .withComparatorForType(
                            (value1, value2) -> {
                                // Treat null and empty string as equal
                                if (value1 == null && "".equals(value2) || "".equals(value1) && value2 == null) {
                                    return 0; // Consider them equal
                                }
                                if (value1 == null)
                                    return -1;
                                if (value2 == null)
                                    return 1;
                                return value1.compareTo(value2);
                            },
                            String.class  // Apply to all String fields, including in lists
                    )
                    .isEqualTo(prodDisease);
        }
    }
}
