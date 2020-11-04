/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

import java.io.IOException;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.glossary.Glossary.Category;
import com.imsweb.seerapi.client.shared.KeywordMatch;
import com.imsweb.seerapi.client.staging.cs.CsSchemaLookup;

import static org.assertj.core.api.Assertions.assertThat;

public class StagingTest {

    private static final String _ALGORITHM = "cs";
    private static final String _VERSION = "02.05.50";

    private static StagingService _STAGING;

    @BeforeClass
    public static void setup() {
        _STAGING = new SeerApi.Builder().connect().staging();
    }

    @Test
    public void testGetAlgorithms() throws IOException {
        List<StagingAlgorithm> algorithms = _STAGING.algorithms().execute().body();

        assertThat(algorithms).isNotEmpty().extracting("algorithm").contains("cs", "tnm", "eod_public");
    }

    @Test
    public void testGetAlgorithmVersions() throws IOException {
        List<StagingVersion> versions = _STAGING.versions(_ALGORITHM).execute().body();

        assertThat(versions).isNotEmpty();
    }

    @Test
    public void testListSchemas() throws IOException {
        List<StagingSchemaInfo> schemaInfos = _STAGING.schemas(_ALGORITHM, _VERSION).execute().body();
        assertThat(schemaInfos).isNotEmpty();

        schemaInfos = _STAGING.schemas(_ALGORITHM, _VERSION, "skin").execute().body();
        assertThat(schemaInfos).isNotEmpty();
    }

    @Test
    public void testSchemaLookup() throws IOException {
        // first test simple lookup that returns a single schema with site/hist only
        List<StagingSchemaInfo> schemas = _STAGING.schemaLookup(_ALGORITHM, _VERSION, new CsSchemaLookup("C509", "8000").getInputs()).execute().body();
        assertThat(schemas).hasSize(1).extracting("id").contains("breast");

        // now test just site
        SchemaLookup data = new SchemaLookup();
        data.setInput(StagingData.PRIMARY_SITE_KEY, "C111");
        assertThat(_STAGING.schemaLookup(_ALGORITHM, _VERSION, data.getInputs()).execute().body()).hasSize(7);

        // add histology
        data.setInput(StagingData.HISTOLOGY_KEY, "8000");
        assertThat(_STAGING.schemaLookup(_ALGORITHM, _VERSION, data.getInputs()).execute().body()).hasSize(2);

        // add discriminator
        data.setInput("ssf25", "010");
        schemas = _STAGING.schemaLookup(_ALGORITHM, _VERSION, data.getInputs()).execute().body();
        assertThat(schemas).hasSize(1).extracting("id").contains("nasopharynx");

        // test with the CsStaging class
        schemas = _STAGING.schemaLookup(_ALGORITHM, _VERSION, new CsSchemaLookup("C111", "8000", "010").getInputs()).execute().body();
        assertThat(schemas).hasSize(1).extracting("id").contains("nasopharynx");
    }

    @Test
    public void testSchemaById() throws IOException {
        StagingSchema schema = _STAGING.schemaById(_ALGORITHM, _VERSION, "brain").execute().body();

        assertThat(schema).isNotNull();
        assertThat(schema.getAlgorithm()).isEqualTo("cs");
        assertThat(schema.getVersion()).isEqualTo("02.05.50");
        assertThat(schema.getId()).isEqualTo("brain");

        // verify the inputs
        StagingSchemaInput input = schema.getInputs().stream().filter(i -> "site".equals(i.getKey())).findFirst().orElse(null);
        assertThat(input).isNotNull();
        assertThat(input.getName()).isEqualTo("Primary Site");
        assertThat(input.getDescription()).isEqualTo("Code for the primary site of the tumor being reported using either ICD-O-2 or ICD-O-3.");
        assertThat(input.getNaaccrItem()).isEqualTo(400);
        assertThat(input.getNaaccrXmlId()).isEqualTo("primarySite");
        assertThat(input.getUsedForStaging()).isTrue();
        assertThat(input.getTable()).isEqualTo("primary_site");

        // verify the outputs
        StagingSchemaOutput output = schema.getOutputs().stream().filter(i -> "csver_derived".equals(i.getKey())).findFirst().orElse(null);
        assertThat(output).isNotNull();
        assertThat(output.getName()).isEqualTo("CS Version Derived");
        assertThat(output.getDescription()).isEqualTo("Collaborative Staging (CS) version used to derive the CS output fields.");
        assertThat(output.getNaaccrItem()).isEqualTo(2936);
        assertThat(output.getNaaccrXmlId()).isEqualTo("csVersionDerived");
        assertThat(output.getDefault()).isEqualTo("020550");
    }

    @Test
    public void testSchemaInvolvedTables() throws IOException {
        List<StagingTable> tables = _STAGING.involvedTables(_ALGORITHM, _VERSION, "brain").execute().body();

        assertThat(tables).isNotEmpty();
    }

    @Test
    public void testListTables() throws IOException {
        List<StagingTable> tables = _STAGING.tables(_ALGORITHM, _VERSION, "ssf1").execute().body();

        assertThat(tables).isNotEmpty();
    }

    @Test
    public void testTableById() throws IOException {
        StagingTable table = _STAGING.tableById(_ALGORITHM, _VERSION, "primary_site").execute().body();

        assertThat(table).isNotNull();
        assertThat(table.getAlgorithm()).isEqualTo("cs");
        assertThat(table.getVersion()).isEqualTo("02.05.50");
        assertThat(table.getId()).isEqualTo("primary_site");
    }

    @Test
    public void testTableInvoledSchemas() throws IOException {
        List<StagingSchema> schemas = _STAGING.involvedSchemas(_ALGORITHM, _VERSION, "extension_baa").execute().body();

        assertThat(schemas).isNotEmpty();
    }

    @Test
    public void testStaging() throws IOException {
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
        StagingData output = _STAGING.stage(_ALGORITHM, _VERSION, data.getInput()).execute().body();

        assertThat(output).isNotNull();
        assertThat(output.getResult()).isEqualTo(StagingData.Result.STAGED);
        assertThat(output.getErrors()).isEmpty();
        assertThat(output.getPath()).hasSize(37);

        // check output
        assertThat(output.getOutput("schema_number")).isEqualTo("129");
        assertThat(output.getSchemaId()).isEqualTo("urethra");
        assertThat(output.getOutput("csver_derived")).isEqualTo("020550");

        // AJCC 6
        assertThat(output.getOutput("ajcc6_t")).isEqualTo("T1");
        assertThat(output.getOutput("stor_ajcc6_t")).isEqualTo("10");
        assertThat(output.getOutput("ajcc6_tdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("stor_ajcc6_tdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("ajcc6_n")).isEqualTo("N1");
        assertThat(output.getOutput("stor_ajcc6_n")).isEqualTo("10");
        assertThat(output.getOutput("ajcc6_ndescriptor")).isEqualTo("c");
        assertThat(output.getOutput("stor_ajcc6_ndescriptor")).isEqualTo("c");
        assertThat(output.getOutput("ajcc6_m")).isEqualTo("M1");
        assertThat(output.getOutput("stor_ajcc6_m")).isEqualTo("10");
        assertThat(output.getOutput("ajcc6_mdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("stor_ajcc6_mdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("ajcc6_stage")).isEqualTo("IV");
        assertThat(output.getOutput("stor_ajcc6_stage")).isEqualTo("70");

        // AJCC 7
        assertThat(output.getOutput("ajcc7_t")).isEqualTo("T1");
        assertThat(output.getOutput("stor_ajcc7_t")).isEqualTo("100");
        assertThat(output.getOutput("ajcc7_tdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("stor_ajcc7_tdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("ajcc7_n")).isEqualTo("N1");
        assertThat(output.getOutput("stor_ajcc7_n")).isEqualTo("100");
        assertThat(output.getOutput("ajcc7_ndescriptor")).isEqualTo("c");
        assertThat(output.getOutput("stor_ajcc7_ndescriptor")).isEqualTo("c");
        assertThat(output.getOutput("ajcc7_m")).isEqualTo("M1");
        assertThat(output.getOutput("stor_ajcc7_m")).isEqualTo("100");
        assertThat(output.getOutput("ajcc7_mdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("stor_ajcc7_mdescriptor")).isEqualTo("c");
        assertThat(output.getOutput("ajcc7_stage")).isEqualTo("IV");
        assertThat(output.getOutput("stor_ajcc7_stage")).isEqualTo("700");

        // Summary Stage
        assertThat(output.getOutput("t77")).isEqualTo("L");
        assertThat(output.getOutput("n77")).isEqualTo("RN");
        assertThat(output.getOutput("m77")).isEqualTo("D");
        assertThat(output.getOutput("ss77")).isEqualTo("D");
        assertThat(output.getOutput("stor_ss77")).isEqualTo("7");
        assertThat(output.getOutput("t2000")).isEqualTo("L");
        assertThat(output.getOutput("n2000")).isEqualTo("RN");
        assertThat(output.getOutput("m2000")).isEqualTo("D");
        assertThat(output.getOutput("ss2000")).isEqualTo("D");
        assertThat(output.getOutput("stor_ss2000")).isEqualTo("7");
    }

    @Test
    public void testStagingWithErrors() throws IOException {
        StagingData data = new StagingData();
        data.setInput("site", "C181");
        data.setInput("hist", "8093");
        data.setInput("year_dx", "2015");
        data.setInput("extension", "670");

        // perform the staging
        StagingData output = _STAGING.stage(_ALGORITHM, _VERSION, data.getInput()).execute().body();

        assertThat(output).isNotNull();
        assertThat(output.getResult()).isEqualTo(StagingData.Result.STAGED);
        assertThat(output.getErrors()).hasSize(9);
    }

    @Test
    public void testStagingGlossary() throws IOException {
        Set<KeywordMatch> matches = _STAGING.schemaGlossary("eod_public", "2.0", "breast", null, true).execute().body();
        assertThat(matches).hasSize(26);
        matches = _STAGING.schemaGlossary("eod_public", "2.0", "breast", EnumSet.of(Category.STAGING), true).execute().body();
        assertThat(matches).hasSize(1);

        matches = _STAGING.tableGlossary("eod_public", "2.0", "cea_pretx_lab_value_33864", null, true).execute().body();
        assertThat(matches).hasSize(20);
        matches = _STAGING.tableGlossary("eod_public", "2.0", "cea_pretx_lab_value_33864", EnumSet.of(Category.STAGING), true).execute().body();
        assertThat(matches).isEmpty();
    }

}
