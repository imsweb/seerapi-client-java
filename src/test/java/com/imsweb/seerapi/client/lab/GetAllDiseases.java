/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.lab;

import java.io.IOException;

import org.junit.Ignore;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.disease.Disease;
import com.imsweb.seerapi.client.disease.DiseaseSearch;
import com.imsweb.seerapi.client.disease.DiseaseSearchResults;

@Ignore
public class GetAllDiseases {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        DiseaseSearch search = new DiseaseSearch();
        search.setOutputType(DiseaseSearch.OutputType.MIN);

        SeerApi api = SeerApi.connect();

        DiseaseSearchResults result = api.diseaseSearch("latest_dev", search);
        for (Disease diseaseId : result.getResults()) {
            Disease disease = api.diseaseById("latest_dev", diseaseId.getId());
        }

        System.out.println("Read " + result.getResults().size() + " diseases in " + (System.currentTimeMillis() - start) + "ms");
    }

}
