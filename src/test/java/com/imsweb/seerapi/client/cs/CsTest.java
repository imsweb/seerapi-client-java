/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.imsweb.seerapi.client.SeerApi;

public class CsTest {

    @Test
    public void testCsVersions() throws IOException {
        List<CsVersion> versions = SeerApi.connect().csVersions();

        Assert.assertEquals(1, versions.size());
        for (CsVersion version : versions) {
            Assert.assertTrue(version.getVersion().length() > 0);
            Assert.assertTrue(version.getTimestamp().length() > 0);
            Assert.assertEquals(153, version.getNumSchemas().longValue());
        }
    }

    @Test
    public void testCsSchemas() throws IOException {
        List<CsSchemaName> schemas = SeerApi.connect().csSchemas("latest");

        Assert.assertEquals(153, schemas.size());
        for (CsSchemaName schema : schemas) {
            Assert.assertTrue(schema.getName().length() > 0);
            Assert.assertTrue(schema.getSchemaNumber() > 0);
        }
    }

    @Test
    public void testCsSchemaExists() throws IOException {
        CsSchemaExistence exists = SeerApi.connect().csSchemaExists("latest", "C481", "8240");
        Assert.assertTrue(exists.isValid());
        Assert.assertTrue(exists.needsDescriminator());
        Assert.assertNotNull(exists.getTable());
        Assert.assertEquals(102, exists.getTable().getTableNumber().longValue());
        Assert.assertEquals("C481", exists.getSite());
        Assert.assertEquals("8240", exists.getHistology());
    }

    @Test
    public void testCsSchema() throws IOException {
        CsSchema schemaById = SeerApi.connect().csSchema("latest", 105);
        Assert.assertNotNull(schemaById);
        Assert.assertEquals(105, schemaById.getSchemaNumber());
        Assert.assertTrue(schemaById.getSiteSpecificFactors().size() > 0);

        CsSiteSpecificFactor ssf = schemaById.getSiteSpecificFactors().get(0);
        Assert.assertEquals(1, ssf.getSsfNumber());
        Assert.assertEquals("Carbohydrate Antigen 125 (CA-125)", ssf.getName());
        Assert.assertFalse(ssf.isAlreadyCollected());
        Assert.assertFalse(ssf.isNeededForStaging());
        Assert.assertTrue(ssf.isClinicallySignificant());
        Assert.assertFalse(ssf.isRequiredPre2010());
        Assert.assertEquals("988", ssf.getDefaultValue());

        Assert.assertEquals("Peritoneum for Females Only", schemaById.getTitle());
        Assert.assertEquals("", schemaById.getSubtitle());
        Assert.assertTrue(schemaById.getSiteSummary().startsWith("C48.1-C48.2"));
        Assert.assertEquals(8, schemaById.getNotes().size());
        Assert.assertEquals(40, schemaById.getNumTables());
        Assert.assertEquals("08/07/2013", schemaById.getRevisionDate());

        // get the same schema using site/hist/ssf25 and compare to the original schema
        CsSchema schemaBySiteHist = SeerApi.connect().csSchema("latest", "C481", "8240", "002");
        Assert.assertEquals(schemaById.getSchemaNumber(), schemaBySiteHist.getSchemaNumber());
        Assert.assertEquals(schemaById.getName(), schemaBySiteHist.getName());
    }

    @Test
    public void testCsCodeValidity() throws IOException {
        CsCodeValidity validity = SeerApi.connect().csValidCode("latest", 112, 3, "0");

        Assert.assertEquals(112, validity.getSchemaNumber().longValue());
        Assert.assertEquals(3, validity.getTableNumber().longValue());
        Assert.assertEquals("0", validity.getCode());
        Assert.assertTrue(validity.isValid());
        Assert.assertFalse(validity.isObsolete());
    }

    @Test
    public void testCsTable() throws IOException {
        CsTable table = SeerApi.connect().csTable("latest", 112, 3);

        Assert.assertNotNull(table);
        Assert.assertEquals("cpa", table.getTableId());
        Assert.assertEquals(112, table.getTableNumber().longValue());
        Assert.assertEquals("CS Tumor Size/Ext Eval", table.getTitle());
        Assert.assertEquals("", table.getSubtitle());
        Assert.assertEquals(0, table.getNotes().size());
        Assert.assertEquals("1-1-1", table.getPattern());
        Assert.assertEquals(3, table.getHeaders().size());
        Assert.assertEquals(8, table.getRows().size());

        CsTableRow row = table.getRows().get(0);
        Assert.assertEquals(3, row.getCells().size());

        Assert.assertEquals(0, table.getFootnotes().size());
        Assert.assertEquals("ACTIVE", table.getUsage());
        Assert.assertEquals("CURRENT", table.getCurrency());
        Assert.assertEquals("INPUT", table.getRole());
        Assert.assertEquals("08/10/2009", table.getRevisionDate());
    }

    @Test
    public void testCsCalculate() throws IOException {
        CsInput input = new CsInput();
        input.setSite("C659");
        input.setHistology("8130");
        input.setDiagnosisYear("2010");
        input.setCsVersionOriginal("020200");
        input.setBehavior("3");
        input.setGrade("4");
        input.setAge("076");
        input.setLvi("0");
        input.setTumorSize("065");
        input.setExtension("700");
        input.setExtensionEval("3");
        input.setLymphNodes("000");
        input.setLymphNodesEval("0");
        input.setLymphNodesPositive("98");
        input.setLymphNodesExamined("00");
        input.setMetsAtDx("00");
        input.setMetsEval("0");
        input.setSsf1("888");
        input.setSsf2("000");

        // inputs will default to "988" in the API, but I am setting them here for code coverage purposes
        input.setSsf3("988");
        input.setSsf4("988");
        input.setSsf5("988");
        input.setSsf6("988");
        input.setSsf7("988");
        input.setSsf8("988");
        input.setSsf9("988");
        input.setSsf10("988");
        input.setSsf11("988");
        input.setSsf12("988");
        input.setSsf13("988");
        input.setSsf14("988");
        input.setSsf15("988");
        input.setSsf16("988");
        input.setSsf17("988");
        input.setSsf18("988");
        input.setSsf19("988");
        input.setSsf20("988");
        input.setSsf21("988");
        input.setSsf22("988");
        input.setSsf23("988");
        input.setSsf24("988");
        input.setSsf25("988");

        CsResult result = SeerApi.connect().csCalculate("latest", input);

        Assert.assertNotNull(result);
        Assert.assertEquals(0, result.getResult().longValue());
        Assert.assertEquals(0, result.getError().longValue());
        Assert.assertEquals(5, result.getInvolvedTables().size());

        CsInvolvedTable involved = result.getInvolvedTables().get(0);
        Assert.assertEquals(127, involved.getTableNumber().longValue());
        Assert.assertEquals("Histology Inclusion Table AJCC 7th ed.", involved.getTitle());
        Assert.assertEquals("", involved.getSubtitle());
        Assert.assertEquals("HISTOINC", involved.getRole());

        Assert.assertEquals(1, result.getProblemCodes().size());
        Assert.assertEquals("", result.getMessages());
        Assert.assertEquals(127, result.getSchemaNumber().longValue());
        Assert.assertEquals("020550", result.getCsVersionDerived());
        Assert.assertEquals("KidneyRenalPelvis", result.getSchemaName());

        Assert.assertEquals("p", result.getStorageAjcc7TDescriptor());
        Assert.assertEquals("400", result.getStorageAjcc7T());
        Assert.assertEquals("c", result.getStorageAjcc7NDescriptor());
        Assert.assertEquals("000", result.getStorageAjcc7N());
        Assert.assertEquals("c", result.getStorageAjcc7MDescriptor());
        Assert.assertEquals("000", result.getStorageAjcc7M());
        Assert.assertEquals("700", result.getStorageAjcc7StageGroup());

        Assert.assertEquals("p", result.getDisplayAjcc7TDescriptor());
        Assert.assertEquals("T4", result.getDisplayAjcc7T());
        Assert.assertEquals("c", result.getDisplayAjcc7NDescriptor());
        Assert.assertEquals("N0", result.getDisplayAjcc7N());
        Assert.assertEquals("c", result.getDisplayAjcc7MDescriptor());
        Assert.assertEquals("M0", result.getDisplayAjcc7M());
        Assert.assertEquals("IV", result.getDisplayAjcc7StageGroup());

        Assert.assertEquals("p", result.getStorageAjcc6TDescriptor());
        Assert.assertEquals("40", result.getStorageAjcc6T());
        Assert.assertEquals("c", result.getStorageAjcc6NDescriptor());
        Assert.assertEquals("00", result.getStorageAjcc6N());
        Assert.assertEquals("c", result.getStorageAjcc6MDescriptor());
        Assert.assertEquals("00", result.getStorageAjcc6M());
        Assert.assertEquals("70", result.getStorageAjcc6StageGroup());

        Assert.assertEquals("p", result.getDisplayAjcc6TDescriptor());
        Assert.assertEquals("T4", result.getDisplayAjcc6T());
        Assert.assertEquals("c", result.getDisplayAjcc6NDescriptor());
        Assert.assertEquals("N0", result.getDisplayAjcc6N());
        Assert.assertEquals("c", result.getDisplayAjcc6MDescriptor());
        Assert.assertEquals("M0", result.getDisplayAjcc6M());
        Assert.assertEquals("IV", result.getDisplayAjcc6StageGroup());

        Assert.assertEquals("7", result.getStorageSs1977());
        Assert.assertEquals("7", result.getStorageSs2000());

        Assert.assertEquals("D", result.getDisplayT1977());
        Assert.assertEquals("NONE", result.getDisplayN1977());
        Assert.assertEquals("NONE", result.getDisplayM1977());
        Assert.assertEquals("D", result.getDisplaySs1977());

        Assert.assertEquals("D", result.getDisplayT2000());
        Assert.assertEquals("NONE", result.getDisplayN2000());
        Assert.assertEquals("NONE", result.getDisplayM2000());
        Assert.assertEquals("D", result.getDisplaySs2000());
    }

}
