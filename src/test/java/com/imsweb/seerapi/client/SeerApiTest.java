/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.WebApplicationException;

import org.junit.Assert;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import com.imsweb.seerapi.client.cs.CsCodeValidity;
import com.imsweb.seerapi.client.cs.CsInput;
import com.imsweb.seerapi.client.cs.CsResult;
import com.imsweb.seerapi.client.cs.CsSchema;
import com.imsweb.seerapi.client.cs.CsSchemaExistence;
import com.imsweb.seerapi.client.cs.CsSchemaName;
import com.imsweb.seerapi.client.cs.CsTable;
import com.imsweb.seerapi.client.cs.CsVersion;
import com.imsweb.seerapi.client.naaccr.NaaccrField;
import com.imsweb.seerapi.client.naaccr.NaaccrFieldName;
import com.imsweb.seerapi.client.naaccr.NaaccrVersion;
import com.imsweb.seerapi.client.siterecode.SiteRecode;

public class SeerApiTest {

    @Test(expected = BadRequestException.class)
    public void testBadParameterExceptiion() throws IOException {
        SeerApi.connect().siteRecode("C379", null);
    }

    @Test
    public void testExceptionMessages() throws IOException {
        String message = "";

        try {
            SeerApi.connect().siteRecode("C379", null);
        }
        catch (WebApplicationException e) {
            message = e.getMessage();
        }

        // the API call works out to:
        //     https://api.seer.cancer.gov/rest/recode/sitegroup?site=C379
        // and the full message returned should be
        //     {"code":400,"message":"Site and histology must be supplied"}
        Assert.assertEquals("Site and histology must be supplied", message);
    }

    @Test
    public void testSiteRecordVersion() throws IOException {
        String version = SeerApi.connect().siteRecodeVersion();
        Assert.assertNotNull(version);
        Assert.assertTrue(version.length() > 0);
    }

    @Test
    public void testSiteRecode() throws IOException {
        SiteRecode recode = SeerApi.connect().siteRecode("C379", "9650");

        Assert.assertEquals("C379", recode.getSite());
        Assert.assertEquals("9650", recode.getHist());
        Assert.assertEquals("33011", recode.getSiteGroup());
    }

    @Test
    public void testNaaccrVersions() throws IOException {
        List<NaaccrVersion> versions = SeerApi.connect().naaccrVersions();

        Assert.assertTrue(versions.size() > 0);
        for (NaaccrVersion version : versions) {
            Assert.assertTrue(version.getName().length() > 0);
            Assert.assertEquals(22824, version.getLength().longValue());
            Assert.assertTrue(version.getDescription().length() > 0);
            Assert.assertTrue(version.getStyle().length() > 0);
        }
    }

    @Test
    public void testNaaccrFieldNames() throws IOException {
        List<NaaccrFieldName> names = SeerApi.connect().naaccrFieldNames("latest");

        Assert.assertTrue(names.size() > 0);
        for (NaaccrFieldName name : names) {
            Assert.assertTrue(name.getItem() > 0);
            Assert.assertTrue(name.getName().length() > 0);
        }
    }

    @Test
    public void testNaaccrField() throws IOException {
        NaaccrField name = SeerApi.connect().naaccrField("latest", 521);

        Assert.assertNotNull(name);
        Assert.assertEquals(521, name.getItem().longValue());
        Assert.assertEquals(550, name.getStart().longValue());
        Assert.assertEquals(554, name.getEnd().longValue());

        Assert.assertEquals(2, name.getSubFields().size());
        Assert.assertEquals(522, name.getSubFields().get(0).getItem().longValue());
        Assert.assertEquals(523, name.getSubFields().get(1).getItem().longValue());
    }

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
        ReflectionAssert.assertReflectionEquals(schemaById, schemaBySiteHist);
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
