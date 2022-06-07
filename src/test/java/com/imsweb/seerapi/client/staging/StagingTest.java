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
import com.imsweb.seerapi.client.staging.cs.CsStagingData;
import com.imsweb.seerapi.client.staging.cs.CsStagingData.CsInput;
import com.imsweb.seerapi.client.staging.cs.CsStagingData.CsStagingInputBuilder;
import com.imsweb.seerapi.client.staging.tnm.TnmStagingData;
import com.imsweb.seerapi.client.staging.tnm.TnmStagingData.TnmInput;
import com.imsweb.seerapi.client.staging.tnm.TnmStagingData.TnmOutput;
import com.imsweb.seerapi.client.staging.tnm.TnmStagingData.TnmStagingInputBuilder;

import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_M;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_MDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_N;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_NDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_T;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC6_TDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_M;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_MDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_N;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_NDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_T;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.AJCC7_TDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.CSVER_DERIVED;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SCHEMA_NUMBER;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS1977_M;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS1977_N;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS1977_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS1977_T;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS2000_M;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS2000_N;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS2000_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.SS2000_T;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_M;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_MDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_N;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_NDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_T;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC6_TDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_M;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_MDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_N;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_NDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_T;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_AJCC7_TDESCRIPTOR;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_SS1977_STAGE;
import static com.imsweb.seerapi.client.staging.cs.CsStagingData.CsOutput.STOR_SS2000_STAGE;
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

        tables = _STAGING.tables(_ALGORITHM, _VERSION, null, true).execute().body();
        assertThat(tables).isEmpty();
        tables = _STAGING.tables(_ALGORITHM, _VERSION, null, false).execute().body();
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
    public void testCsInputBuilder() {
        CsStagingData data1 = new CsStagingData();
        data1.setInput(CsInput.PRIMARY_SITE, "C680");
        data1.setInput(CsInput.HISTOLOGY, "8000");
        data1.setInput(CsInput.BEHAVIOR, "3");
        data1.setInput(CsInput.GRADE, "9");
        data1.setInput(CsInput.DX_YEAR, "2013");
        data1.setInput(CsInput.CS_VERSION_ORIGINAL, "020550");
        data1.setInput(CsInput.TUMOR_SIZE, "075");
        data1.setInput(CsInput.EXTENSION, "100");
        data1.setInput(CsInput.EXTENSION_EVAL, "9");
        data1.setInput(CsInput.LYMPH_NODES, "100");
        data1.setInput(CsInput.LYMPH_NODES_EVAL, "9");
        data1.setInput(CsInput.REGIONAL_NODES_POSITIVE, "99");
        data1.setInput(CsInput.REGIONAL_NODES_EXAMINED, "99");
        data1.setInput(CsInput.METS_AT_DX, "10");
        data1.setInput(CsInput.METS_EVAL, "9");
        data1.setInput(CsInput.LVI, "9");
        data1.setInput(CsInput.AGE_AT_DX, "060");
        data1.setSsf(1, "020");

        CsStagingData data2 = new CsStagingInputBuilder()
                .withInput(CsStagingData.CsInput.PRIMARY_SITE, "C680")
                .withInput(CsStagingData.CsInput.HISTOLOGY, "8000")
                .withInput(CsStagingData.CsInput.BEHAVIOR, "3")
                .withInput(CsStagingData.CsInput.GRADE, "9")
                .withInput(CsStagingData.CsInput.DX_YEAR, "2013")
                .withInput(CsStagingData.CsInput.CS_VERSION_ORIGINAL, "020550")
                .withInput(CsStagingData.CsInput.TUMOR_SIZE, "075")
                .withInput(CsStagingData.CsInput.EXTENSION, "100")
                .withInput(CsStagingData.CsInput.EXTENSION_EVAL, "9")
                .withInput(CsStagingData.CsInput.LYMPH_NODES, "100")
                .withInput(CsStagingData.CsInput.LYMPH_NODES_EVAL, "9")
                .withInput(CsStagingData.CsInput.REGIONAL_NODES_POSITIVE, "99")
                .withInput(CsStagingData.CsInput.REGIONAL_NODES_EXAMINED, "99")
                .withInput(CsStagingData.CsInput.METS_AT_DX, "10")
                .withInput(CsStagingData.CsInput.METS_EVAL, "9")
                .withInput(CsStagingData.CsInput.LVI, "9")
                .withInput(CsStagingData.CsInput.AGE_AT_DX, "060")
                .withSsf(1, "020")
                .build();

        assertThat(data1.getInput()).isEqualTo(data2.getInput());
    }

    @Test
    public void testCsStaging() throws IOException {
        CsStagingData data = new CsStagingInputBuilder()
                .withInput(CsInput.PRIMARY_SITE, "C680")
                .withInput(CsInput.HISTOLOGY, "8000")
                .withInput(CsInput.BEHAVIOR, "3")
                .withInput(CsInput.GRADE, "9")
                .withInput(CsInput.DX_YEAR, "2013")
                .withInput(CsInput.CS_VERSION_ORIGINAL, "020550")
                .withInput(CsInput.TUMOR_SIZE, "075")
                .withInput(CsInput.EXTENSION, "100")
                .withInput(CsInput.EXTENSION_EVAL, "9")
                .withInput(CsInput.LYMPH_NODES, "100")
                .withInput(CsInput.LYMPH_NODES_EVAL, "9")
                .withInput(CsInput.REGIONAL_NODES_POSITIVE, "99")
                .withInput(CsInput.REGIONAL_NODES_EXAMINED, "99")
                .withInput(CsInput.METS_AT_DX, "10")
                .withInput(CsInput.METS_EVAL, "9")
                .withInput(CsInput.LVI, "9")
                .withInput(CsInput.AGE_AT_DX, "060")
                .withSsf(1, "020")
                .build();

        // perform the staging
        StagingData output = _STAGING.stage("cs", "02.05.50", data.getInput()).execute().body();

        assertThat(output).isNotNull();
        assertThat(output.getResult()).isEqualTo(StagingData.Result.STAGED);
        assertThat(output.getErrors()).isEmpty();
        assertThat(output.getPath()).hasSize(37);

        // check output
        assertThat(output.getOutput(SCHEMA_NUMBER.toString())).isEqualTo("129");
        assertThat(output.getSchemaId()).isEqualTo("urethra");
        assertThat(output.getOutput(CSVER_DERIVED.toString())).isEqualTo("020550");

        // AJCC 6
        assertThat(output.getOutput(AJCC6_T.toString())).isEqualTo("T1");
        assertThat(output.getOutput(STOR_AJCC6_T.toString())).isEqualTo("10");
        assertThat(output.getOutput(AJCC6_TDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(STOR_AJCC6_TDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(AJCC6_N.toString())).isEqualTo("N1");
        assertThat(output.getOutput(STOR_AJCC6_N.toString())).isEqualTo("10");
        assertThat(output.getOutput(AJCC6_NDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(STOR_AJCC6_NDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(AJCC6_M.toString())).isEqualTo("M1");
        assertThat(output.getOutput(STOR_AJCC6_M.toString())).isEqualTo("10");
        assertThat(output.getOutput(AJCC6_MDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(STOR_AJCC6_MDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(AJCC6_STAGE.toString())).isEqualTo("IV");
        assertThat(output.getOutput(STOR_AJCC6_STAGE.toString())).isEqualTo("70");

        // AJCC 7
        assertThat(output.getOutput(AJCC7_T.toString())).isEqualTo("T1");
        assertThat(output.getOutput(STOR_AJCC7_T.toString())).isEqualTo("100");
        assertThat(output.getOutput(AJCC7_TDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(STOR_AJCC7_TDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(AJCC7_N.toString())).isEqualTo("N1");
        assertThat(output.getOutput(STOR_AJCC7_N.toString())).isEqualTo("100");
        assertThat(output.getOutput(AJCC7_NDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(STOR_AJCC7_NDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(AJCC7_M.toString())).isEqualTo("M1");
        assertThat(output.getOutput(STOR_AJCC7_M.toString())).isEqualTo("100");
        assertThat(output.getOutput(AJCC7_MDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(STOR_AJCC7_MDESCRIPTOR.toString())).isEqualTo("c");
        assertThat(output.getOutput(AJCC7_STAGE.toString())).isEqualTo("IV");
        assertThat(output.getOutput(STOR_AJCC7_STAGE.toString())).isEqualTo("700");

        // Summary Stage
        assertThat(output.getOutput(SS1977_T.toString())).isEqualTo("L");
        assertThat(output.getOutput(SS1977_N.toString())).isEqualTo("RN");
        assertThat(output.getOutput(SS1977_M.toString())).isEqualTo("D");
        assertThat(output.getOutput(SS1977_STAGE.toString())).isEqualTo("D");
        assertThat(output.getOutput(STOR_SS1977_STAGE.toString())).isEqualTo("7");
        assertThat(output.getOutput(SS2000_T.toString())).isEqualTo("L");
        assertThat(output.getOutput(SS2000_N.toString())).isEqualTo("RN");
        assertThat(output.getOutput(SS2000_M.toString())).isEqualTo("D");
        assertThat(output.getOutput(SS2000_STAGE.toString())).isEqualTo("D");
        assertThat(output.getOutput(STOR_SS2000_STAGE.toString())).isEqualTo("7");
    }

    @Test
    public void testTnmInputBuilder() {
        TnmStagingData data1 = new TnmStagingData();
        data1.setInput(TnmInput.PRIMARY_SITE, "C680");
        data1.setInput(TnmInput.HISTOLOGY, "8000");
        data1.setInput(TnmInput.BEHAVIOR, "3");
        data1.setInput(TnmInput.GRADE, "9");
        data1.setInput(TnmInput.DX_YEAR, "2013");
        data1.setInput(TnmInput.REGIONAL_NODES_POSITIVE, "99");
        data1.setInput(TnmInput.AGE_AT_DX, "060");
        data1.setInput(TnmInput.SEX, "1");
        data1.setInput(TnmInput.RX_SUMM_SURGERY, "8");
        data1.setInput(TnmInput.RX_SUMM_RADIATION, "9");
        data1.setInput(TnmInput.CLIN_T, "1");
        data1.setInput(TnmInput.CLIN_N, "2");
        data1.setInput(TnmInput.CLIN_M, "3");
        data1.setInput(TnmInput.PATH_T, "4");
        data1.setInput(TnmInput.PATH_N, "5");
        data1.setInput(TnmInput.PATH_M, "6");
        data1.setSsf(1, "020");

        TnmStagingData data2 = new TnmStagingData.TnmStagingInputBuilder()
                .withInput(TnmInput.PRIMARY_SITE, "C680")
                .withInput(TnmInput.HISTOLOGY, "8000")
                .withInput(TnmInput.BEHAVIOR, "3")
                .withInput(TnmInput.GRADE, "9")
                .withInput(TnmInput.DX_YEAR, "2013")
                .withInput(TnmInput.REGIONAL_NODES_POSITIVE, "99")
                .withInput(TnmInput.AGE_AT_DX, "060")
                .withInput(TnmInput.SEX, "1")
                .withInput(TnmInput.RX_SUMM_SURGERY, "8")
                .withInput(TnmInput.RX_SUMM_RADIATION, "9")
                .withInput(TnmInput.CLIN_T, "1")
                .withInput(TnmInput.CLIN_N, "2")
                .withInput(TnmInput.CLIN_M, "3")
                .withInput(TnmInput.PATH_T, "4")
                .withInput(TnmInput.PATH_N, "5")
                .withInput(TnmInput.PATH_M, "6")
                .withSsf(1, "020").build();

        assertThat(data1.getInput()).isEqualTo(data2.getInput());
    }

    @Test
    public void testTnmStaging() throws IOException {
        TnmStagingData data = new TnmStagingInputBuilder()
                .withInput(TnmInput.PRIMARY_SITE, "C680")
                .withInput(TnmInput.HISTOLOGY, "8000")
                .withInput(TnmInput.BEHAVIOR, "3")
                .withInput(TnmInput.DX_YEAR, "2016")
                .withInput(TnmInput.RX_SUMM_SURGERY, "2")
                .withInput(TnmInput.RX_SUMM_RADIATION, "4")
                .withInput(TnmInput.REGIONAL_NODES_POSITIVE, "02")
                .withInput(TnmInput.CLIN_T, "c0")
                .withInput(TnmInput.CLIN_N, "c1")
                .withInput(TnmInput.CLIN_M, "c0")
                .withInput(TnmInput.PATH_T, "p0")
                .withInput(TnmInput.PATH_N, "p1")
                .withInput(TnmInput.PATH_M, "p1")
                .build();

        // perform the staging
        StagingData output = _STAGING.stage("tnm", "1.9", data.getInput()).execute().body();

        // check output
        assertThat(output).isNotNull();
        assertThat(output.getResult()).isEqualTo(StagingData.Result.STAGED);
        assertThat(output.getSchemaId()).isEqualTo("urethra");
        assertThat(output.getErrors()).isEmpty();
        assertThat(output.getPath()).hasSize(25);
        assertThat(output.getOutput()).hasSize(10);

        // check outputs
        assertThat(output.getOutput(TnmOutput.DERIVED_VERSION.toString())).isEqualTo("1.9");
        assertThat(output.getOutput(TnmOutput.CLIN_STAGE_GROUP.toString())).isEqualTo("3");
        assertThat(output.getOutput(TnmOutput.PATH_STAGE_GROUP.toString())).isEqualTo("4");
        assertThat(output.getOutput(TnmOutput.COMBINED_STAGE_GROUP.toString())).isEqualTo("4");
        assertThat(output.getOutput(TnmOutput.COMBINED_T.toString())).isEqualTo("c0");
        assertThat(output.getOutput(TnmOutput.SOURCE_T.toString())).isEqualTo("1");
        assertThat(output.getOutput(TnmOutput.COMBINED_N.toString())).isEqualTo("c1");
        assertThat(output.getOutput(TnmOutput.SOURCE_N.toString())).isEqualTo("1");
        assertThat(output.getOutput(TnmOutput.COMBINED_M.toString())).isEqualTo("p1");
        assertThat(output.getOutput(TnmOutput.SOURCE_M.toString())).isEqualTo("2");
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
        assertThat(matches).hasSize(24);
        matches = _STAGING.tableGlossary("eod_public", "2.0", "cea_pretx_lab_value_33864", EnumSet.of(Category.STAGING), true).execute().body();
        assertThat(matches).isEmpty();
    }

    @Test
    public void testStagingMetadata() throws IOException {
        StagingSchema schema = _STAGING.schemaById("eod_public", "2.0", "brain").execute().body();
        assertThat(schema).isNotNull();

        StagingSchemaInput mgmt = schema.getInputs().stream().filter(i -> i.getKey().equals("mgmt")).findFirst().orElse(null);
        assertThat(mgmt).isNotNull();

        assertThat(mgmt.getMetadata()).extracting("name").containsExactlyInAnyOrder("COC_REQUIRED", "SEER_REQUIRED", "SSDI");
    }

}
