/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.staging.cs.CsSchemaLookup;

public class StagingTest {

    private static final String _ALGORITHM = "cs";
    private static final String _VERSION = "02.05.50";

    private static StagingService _STAGING;

    @BeforeClass
    public static void setup() {
        _STAGING = new SeerApi.Builder().connect().staging();
    }

    @Test
    public void testGetAlgorithms() {
        List<StagingAlgorithm> algorithms = _STAGING.algorithms();

        Assert.assertTrue(algorithms.size() > 0);
    }

    @Test
    public void testGetAlgorithmVersions() {
        List<StagingVersion> versions = _STAGING.versions(_ALGORITHM);

        Assert.assertTrue(versions.size() > 0);
    }

    @Test
    public void testListSchemas() {
        List<StagingSchemaInfo> schemaInfos = _STAGING.schemas(_ALGORITHM, _VERSION);
        Assert.assertTrue(schemaInfos.size() > 0);

        schemaInfos = _STAGING.schemas(_ALGORITHM, _VERSION, "skin");
        Assert.assertTrue(schemaInfos.size() > 0);
    }

    @Test
    public void testSchemaLookup() {
        // first test simple lookup that returns a single schema with site/hist only
        List<StagingSchemaInfo> schemas = _STAGING.schemaLookup(_ALGORITHM, _VERSION, new CsSchemaLookup("C509", "8000").getInputs());
        Assert.assertEquals(1, schemas.size());
        Assert.assertEquals("breast", schemas.get(0).getId());

        // now test just site
        SchemaLookup data = new SchemaLookup();
        data.setInput(StagingData.PRIMARY_SITE_KEY, "C111");
        Assert.assertEquals(7, _STAGING.schemaLookup(_ALGORITHM, _VERSION, data.getInputs()).size());

        // add histology
        data.setInput(StagingData.HISTOLOGY_KEY, "8000");
        Assert.assertEquals(2, _STAGING.schemaLookup(_ALGORITHM, _VERSION, data.getInputs()).size());

        // add discriminator
        data.setInput("ssf25", "010");
        schemas = _STAGING.schemaLookup(_ALGORITHM, _VERSION, data.getInputs());
        Assert.assertEquals(1, schemas.size());
        Assert.assertEquals("nasopharynx", schemas.get(0).getId());

        // test with the CsStaging class
        schemas = _STAGING.schemaLookup(_ALGORITHM, _VERSION, new CsSchemaLookup("C111", "8000", "010").getInputs());
        Assert.assertEquals(1, schemas.size());
        Assert.assertEquals("nasopharynx", schemas.get(0).getId());
    }

    @Test
    public void testSchemaById() {
        StagingSchema schema = _STAGING.schemaById(_ALGORITHM, _VERSION, "brain");

        Assert.assertEquals("cs", schema.getAlgorithm());
        Assert.assertEquals("02.05.50", schema.getVersion());
        Assert.assertEquals("brain", schema.getId());
    }

    @Test
    public void testSchemaInvolvedTables() {
        List<StagingTable> tables = _STAGING.involvedTables(_ALGORITHM, _VERSION, "brain");

        Assert.assertTrue(tables.size() > 0);
    }

    @Test
    public void testListTables() {
        List<StagingTable> tables = _STAGING.tables(_ALGORITHM, _VERSION, "ssf1");

        Assert.assertTrue(tables.size() > 0);
    }

    @Test
    public void testTableById() {
        StagingTable table = _STAGING.tableById(_ALGORITHM, _VERSION, "primary_site");

        Assert.assertEquals("cs", table.getAlgorithm());
        Assert.assertEquals("02.05.50", table.getVersion());
        Assert.assertEquals("primary_site", table.getId());
    }

    @Test
    public void testTableInvoledSchemas() {
        List<StagingSchema> schemas = _STAGING.involvedSchemas(_ALGORITHM, _VERSION, "extension_baa");

        Assert.assertTrue(schemas.size() > 0);
    }

    @Test
    public void testStaging() {
        // test this case:  http://seer.cancer.gov/seertools/cstest/?mets=10&lnexam=99&diagnosis_year=2013&grade=9&exteval=9&age=060&site=C680&metseval=9&hist=8000&ext=100&version=020550&nodeseval=9&behav=3&lnpos=99&nodes=100&csver_original=020440&lvi=9&ssf1=020&size=075
        StagingData data = new StagingData();
        data.setInput("site", "C680");
        data.setInput("hist", "8000");
        data.setInput("behavior", "3");
        data.setInput("grade", "9");
        data.setInput("year_dx", "2013");
        data.setInput("cs_input_version_original", "020550");
        data.setInput("size", "075");
        data.setInput("extension", "100");
        data.setInput("extension_eval", "9");
        data.setInput("nodes", "100");
        data.setInput("nodes_eval", "9");
        data.setInput("nodes_pos", "99");
        data.setInput("nodes_exam", "99");
        data.setInput("mets", "10");
        data.setInput("mets_eval", "9");
        data.setInput("lvi", "9");
        data.setInput("age_dx", "060");
        data.setInput("ssf1", "020");

        // perform the staging
        StagingData output = _STAGING.stage(_ALGORITHM, _VERSION, data.getInput());

        Assert.assertEquals(StagingData.Result.STAGED, output.getResult());
        Assert.assertEquals(0, output.getErrors().size());
        Assert.assertEquals(37, output.getPath().size());

        // check output
        Assert.assertEquals("129", output.getOutput("schema_number"));
        Assert.assertEquals("urethra", output.getSchemaId());
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
