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
    }

    @Test
    public void testCsSchema() throws IOException {
        CsSchema schemaById = SeerApi.connect().csSchema("latest", 105);
        Assert.assertNotNull(schemaById);
        Assert.assertEquals(105, schemaById.getSchemaNumber());
        Assert.assertTrue(schemaById.getSiteSpecificFactors().size() > 0);

        // get the same schema using site/hist/ssf25 and compare to the original schema
        CsSchema schemaBySiteHist = SeerApi.connect().csSchema("latest", "C481", "8240", "002");
        Assert.assertEquals(schemaById.getSchemaNumber(), schemaBySiteHist.getSchemaNumber());
        Assert.assertEquals(schemaById.getName(), schemaBySiteHist.getName());
    }

    @Test
    public void testCsCodeValidity() throws IOException {
        CsCodeValidity validity = SeerApi.connect().csValidCode("latest", 112, 3, "0");

        Assert.assertTrue(validity.isValid());
        Assert.assertFalse(validity.isObsolete());
    }

    @Test
    public void testCsTable() throws IOException {
        CsTable table = SeerApi.connect().csTable("latest", 112, 3);

        Assert.assertNotNull(table);
        Assert.assertEquals(112, table.getTableNumber().longValue());  // TODO this seems like it is returning the schema number in the table number field.  It's a problem in the API though.
        Assert.assertTrue(table.getTitle().length() > 0);
        Assert.assertTrue(table.getRows().size() > 0);
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

        CsResult result = SeerApi.connect().csCalculate("latest", input);

        Assert.assertNotNull(result);
        Assert.assertEquals(127, result.getSchemaNumber().longValue());
        Assert.assertEquals("020550", result.getCsVersionDerived());
        Assert.assertEquals("KidneyRenalPelvis", result.getSchemaName());
        Assert.assertEquals("IV", result.getDisplayAjcc7StageGroup());
    }

}
