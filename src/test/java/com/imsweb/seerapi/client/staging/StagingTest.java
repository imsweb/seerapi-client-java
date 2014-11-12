/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class StagingTest {

    private static final String _ALGORITHM = "cs";
    private static final String _VERSION = "02.05.50";

    private static SeerApi _SEERAPI;

    @BeforeClass
    public static void setup() {
        _SEERAPI = SeerApi.connect();
    }

    @Test
    public void testGetAlgorithms() {
        List<StagingAlgorithm> algorithms = _SEERAPI.stagingAlgorithms();

        Assert.assertTrue(algorithms.size() > 0);
    }

    @Test
    public void testGetAlgorithmVersions() {
        List<StagingVersion> versions = _SEERAPI.stagingAlgorithmVersions(_ALGORITHM);

        Assert.assertTrue(versions.size() > 0);
    }

    @Test
    public void testListSchemas() {
        List<StagingSchemaInfo> schemaInfos = _SEERAPI.stagingSchemas(_ALGORITHM, _VERSION, null, null, null, null);

        Assert.assertTrue(schemaInfos.size() > 0);
    }

    @Test
    public void testSchemaById() {
        StagingSchema schema = _SEERAPI.stagingSchemaById(_ALGORITHM, _VERSION, "brain");

        Assert.assertEquals("cs", schema.getAlgorithm());
        Assert.assertEquals("02.05.50", schema.getVersion());
        Assert.assertEquals("brain", schema.getId());
    }

    @Test
    public void testSchemaInvolvedTables() {
        List<StagingTable> tables = _SEERAPI.stagingSchemaInvolvedTables(_ALGORITHM, _VERSION, "brain");

        Assert.assertTrue(tables.size() > 0);
    }

    @Test
    public void testListTables() {
        List<StagingTable> tables = _SEERAPI.stagingTables(_ALGORITHM, _VERSION, "ssf1");

        Assert.assertTrue(tables.size() > 0);
    }

    @Test
    public void testTableById() {
        StagingTable table = _SEERAPI.stagingTableById(_ALGORITHM, _VERSION, "primary_site");

        Assert.assertEquals("cs", table.getAlgorithm());
        Assert.assertEquals("02.05.50", table.getVersion());
        Assert.assertEquals("primary_site", table.getId());
    }

    @Test
    public void testTableInvoledSchemas() {
        List<StagingSchema> schemas = _SEERAPI.stagingTableInvolvedSchemas(_ALGORITHM, _VERSION, "extension_baa");

        Assert.assertTrue(schemas.size() > 0);
    }

    @Test
    public void testStaging() {
        // test this case:  http://seer.cancer.gov/seertools/cstest/?mets=10&lnexam=99&diagnosis_year=2013&grade=9&exteval=9&age=060&site=C680&metseval=9&hist=8000&ext=100&version=020550&nodeseval=9&behav=3&lnpos=99&nodes=100&csver_original=020440&lvi=9&ssf1=020&size=075
        Map<String, String> inputs = new HashMap<>();
        inputs.put("site", "C680");
        inputs.put("hist", "8000");
        inputs.put("behavior", "3");
        inputs.put("grade", "9");
        inputs.put("year_dx", "2013");
        inputs.put("cs_input_version_original", "020550");
        inputs.put("size", "075");
        inputs.put("extension", "100");
        inputs.put("extension_eval", "9");
        inputs.put("nodes", "100");
        inputs.put("nodes_eval", "9");
        inputs.put("nodes_pos", "99");
        inputs.put("nodes_exam", "99");
        inputs.put("mets", "10");
        inputs.put("mets_eval", "9");
        inputs.put("lvi", "9");
        inputs.put("age_dx", "060");
        inputs.put("ssf1", "020");

        // perform the staging
        StagingOutput output = _SEERAPI.stagingStage(_ALGORITHM, _VERSION, inputs);

        Assert.assertEquals(0, output.getErrors().size());
        Assert.assertEquals(37, output.getPath().size());

        // check output
        Assert.assertEquals("129", output.getOutput("schema_number"));
        Assert.assertEquals("urethra", output.getOutput("schema_id"));
        Assert.assertEquals("020550", output.getOutput("csver_derived"));

        // AJCC 6
        Assert.assertEquals("T1", output.getOutput("ajcc6_t"));
        Assert.assertEquals("10", output.getOutput("stor_ajcc6_t"));
        Assert.assertEquals("c", output.getOutput("ajcc6_tdescriptor"));
        Assert.assertEquals("c", output.getOutput("stor_ajcc6_tdescriptor"));
        Assert.assertEquals("N1", output.getOutput("ajcc6_n"));
        Assert.assertEquals("10", output.getOutput("stor_ajcc6_n"));
        Assert.assertEquals("c", output.getOutput("ajcc6_ndescriptor"));
        Assert.assertEquals("c", output.getOutput("stor_ajcc6_ndescriptor"));
        Assert.assertEquals("M1", output.getOutput("ajcc6_m"));
        Assert.assertEquals("10", output.getOutput("stor_ajcc6_m"));
        Assert.assertEquals("c", output.getOutput("ajcc6_mdescriptor"));
        Assert.assertEquals("c", output.getOutput("stor_ajcc6_mdescriptor"));
        Assert.assertEquals("IV", output.getOutput("ajcc6_stage"));
        Assert.assertEquals("70", output.getOutput("stor_ajcc6_stage"));

        // AJCC 7
        Assert.assertEquals("T1", output.getOutput("ajcc7_t"));
        Assert.assertEquals("100", output.getOutput("stor_ajcc7_t"));
        Assert.assertEquals("c", output.getOutput("ajcc7_tdescriptor"));
        Assert.assertEquals("c", output.getOutput("stor_ajcc7_tdescriptor"));
        Assert.assertEquals("N1", output.getOutput("ajcc7_n"));
        Assert.assertEquals("100", output.getOutput("stor_ajcc7_n"));
        Assert.assertEquals("c", output.getOutput("ajcc7_ndescriptor"));
        Assert.assertEquals("c", output.getOutput("stor_ajcc7_ndescriptor"));
        Assert.assertEquals("M1", output.getOutput("ajcc7_m"));
        Assert.assertEquals("100", output.getOutput("stor_ajcc7_m"));
        Assert.assertEquals("c", output.getOutput("ajcc7_mdescriptor"));
        Assert.assertEquals("c", output.getOutput("stor_ajcc7_mdescriptor"));
        Assert.assertEquals("IV", output.getOutput("ajcc7_stage"));
        Assert.assertEquals("700", output.getOutput("stor_ajcc7_stage"));

        // Summary Stage
        Assert.assertEquals("L", output.getOutput("t77"));
        Assert.assertEquals("RN", output.getOutput("n77"));
        Assert.assertEquals("D", output.getOutput("m77"));
        Assert.assertEquals("D", output.getOutput("ss77"));
        Assert.assertEquals("7", output.getOutput("stor_ss77"));
        Assert.assertEquals("L", output.getOutput("t2000"));
        Assert.assertEquals("RN", output.getOutput("n2000"));
        Assert.assertEquals("D", output.getOutput("m2000"));
        Assert.assertEquals("D", output.getOutput("ss2000"));
        Assert.assertEquals("7", output.getOutput("stor_ss2000"));
    }

}
