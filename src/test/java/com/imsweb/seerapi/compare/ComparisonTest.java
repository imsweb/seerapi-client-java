/*
 * Copyright (C) 2024 Information Management Services, Inc.
 */
package com.imsweb.seerapi.compare;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.hcpcs.Hcpcs;
import com.imsweb.seerapi.client.hcpcs.HcpcsService;
import com.imsweb.seerapi.client.ndc.NdcProduct;
import com.imsweb.seerapi.client.ndc.NdcService;
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
        NdcService ndcProd = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().ndc();
        NdcService ndcLocal = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().ndc();

        long page = 1;

        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", "100");
        params.put("order", "ndc");
        List<NdcProduct> prodList = ndcProd.search(params).execute().body();
        List<NdcProduct> localList = ndcLocal.search(params).execute().body();

        while (!Objects.requireNonNull(prodList).isEmpty() && !Objects.requireNonNull(localList).isEmpty()) {
            assertThat(localList)
                    .hasSameSizeAs(prodList)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .isEqualTo(prodList);

            System.out.println("NDC page " + page);

            page += 1;
            params.put("page", String.valueOf(page));
            prodList = ndcProd.search(params).execute().body();
            localList = ndcLocal.search(params).execute().body();
        }
    }

    @Test
    void testHcpcs() throws IOException {
        HcpcsService hcpcsProd = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().hcpcs();
        HcpcsService hcpcsLocal = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().hcpcs();

        long page = 1;

        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("per_page", "100");
        params.put("order", "hcpcs_code");

        List<Hcpcs> prodList = hcpcsProd.search(params).execute().body();
        List<Hcpcs> localList = hcpcsLocal.search(params).execute().body();

        while (!Objects.requireNonNull(prodList).isEmpty()) {
            System.out.println("HCPCS page " + page);
            assertThat(localList)
                    .hasSameSizeAs(prodList)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .ignoringCollectionOrderInFields("categories")
                    .isEqualTo(prodList);

            page += 1;
            params.put("page", String.valueOf(page));
            prodList = hcpcsProd.search(params).execute().body();
            localList = hcpcsLocal.search(params).execute().body();
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
        StagingService stagingProd = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().staging();
        StagingService stagingLocal = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().staging();

        Map<String, String> algorithmMap = getAlgorithmVersions();
        for (String algorithmId : algorithmMap.keySet()) {
            String algorithmVersion = algorithmMap.get(algorithmId);

            System.out.println("Getting list of all schemas in " + algorithmId + ":" + algorithmVersion);

            List<StagingSchemaInfo> prodSchemas = stagingProd.schemas(algorithmId, algorithmVersion).execute().body();
            List<StagingSchemaInfo> localSchemas = stagingLocal.schemas(algorithmId, algorithmVersion).execute().body();

            assertThat(localSchemas)
                    .hasSameSizeAs(prodSchemas)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .ignoringCollectionOrder()
                    .isEqualTo(prodSchemas);

            for (StagingSchemaInfo prodSchema : Objects.requireNonNull(prodSchemas)) {
                StagingSchema prod = stagingProd.schemaById(algorithmId, algorithmVersion, prodSchema.getId()).execute().body();
                StagingSchema local = stagingLocal.schemaById(algorithmId, algorithmVersion, prodSchema.getId()).execute().body();

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
        StagingService stagingProd = new SeerApi.Builder().url(PROD_URL).apiKey(getApiKey()).connect().staging();
        StagingService stagingLocal = new SeerApi.Builder().url(LOCAL_URL).apiKey(getApiKey()).connect().staging();

        Map<String, String> algorithmMap = getAlgorithmVersions();
        for (String algorithmId : algorithmMap.keySet()) {
            String algorithmVersion = algorithmMap.get(algorithmId);

            System.out.println("Getting list of all tables in " + algorithmId + ":" + algorithmVersion);

            List<StagingTable> prodTables = stagingProd.tables(algorithmId, algorithmVersion).execute().body();
            List<StagingTable> localTables = stagingLocal.tables(algorithmId, algorithmVersion).execute().body();

            assertThat(localTables)
                    .hasSameSizeAs(prodTables)  // Ensure both lists have the same size
                    .usingRecursiveComparison()
                    .ignoringCollectionOrder()
                    .isEqualTo(prodTables);

            for (StagingTable prodTable : Objects.requireNonNull(prodTables)) {
                StagingTable prod = stagingProd.tableById(algorithmId, algorithmVersion, prodTable.getId()).execute().body();
                StagingTable local = stagingLocal.tableById(algorithmId, algorithmVersion, prodTable.getId()).execute().body();

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
}
