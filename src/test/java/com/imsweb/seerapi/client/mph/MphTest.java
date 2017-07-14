package com.imsweb.seerapi.client.mph;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.BadRequestException;
import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.mph.MphInput.HistologyMatchMode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MphTest {

    private static MphService _MPH;

    @BeforeClass
    public static void setup() {
        _MPH = new SeerApi.Builder().connect().mph();
    }

    @Test(expected = BadRequestException.class)
    public void testMissingAllInput() throws IOException {
        _MPH.mph(new MphInputPair()).execute();
    }

    @Test
    public void testMissingSite() throws IOException {
        MphInput input1 = new MphInput();
        // site is missing
        input1.setHistologyIcdO3("8000");
        input1.setBehaviorIcdO3("3");
        input1.setDateOfDiagnosisYear("2016");
        input1.setLaterality("1");

        MphInput input2 = new MphInput();
        input2.setPrimarySite("C501");
        input2.setHistologyIcdO3("8000");
        input2.setBehaviorIcdO3("3");
        input2.setDateOfDiagnosisYear("2016");
        input2.setLaterality("1");

        MphOutput result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(MphOutput.Result.QUESTIONABLE, result.getResult());
        assertEquals(
                "Unable to identify cancer group for first set of parameters. Valid primary site (C000-C999 excluding C809), histology (8000-9999), behavior (0-3, 6) and diagnosis year are required.",
                result.getReason());
    }

    @Test
    public void testResults() throws IOException {
        MphInput input1 = new MphInput();
        input1.setPrimarySite("C509");
        input1.setHistologyIcdO3("8000");
        input1.setBehaviorIcdO3("3");
        input1.setDateOfDiagnosisYear("2016");
        input1.setLaterality("1");

        MphInput input2 = new MphInput();
        input2.setPrimarySite("C501");
        input2.setHistologyIcdO3("8000");
        input2.setBehaviorIcdO3("3");
        input2.setDateOfDiagnosisYear("2016");
        input2.setLaterality("1");

        // first test single primary (only differs by site code)
        MphOutput result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(MphOutput.Result.SINGLE_PRIMARY, result.getResult());
        assertEquals("mp_2007_breast", result.getGroupId());
        assertEquals("M13", result.getStep());
        assertEquals("Tumors that do not meet any of the criteria are abstracted as a single primary.", result.getReason());
        assertEquals(10, result.getAppliedRules().size());
        assertTrue(result.getAppliedRules().get(0).getQuestion().startsWith("Are there tumors in sites with ICD-O-3 topography codes"));

        // next test questionable (setting laterality to unknown for one of the diseases)
        input2.setLaterality("9");
        result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(MphOutput.Result.QUESTIONABLE, result.getResult());
        assertEquals("mp_2007_breast", result.getGroupId());
        assertEquals("M7", result.getStep());
        assertEquals("Unable to apply Rule M7 of mp_2007_breast. Valid and known laterality should be provided.", result.getReason());
        assertEquals(4, result.getAppliedRules().size());
        assertEquals("Is there a tumor(s) in each breast?", result.getAppliedRules().get(3).getQuestion());

        // finally test difffernt diseases; set everything the same except for the DX year
        input1.setPrimarySite("C501");
        input1.setHistologyIcdO3("8000");
        input1.setBehaviorIcdO3("3");
        input1.setDateOfDiagnosisYear("2016");
        input1.setLaterality("1");
        input2.setPrimarySite("C501");
        input2.setHistologyIcdO3("8000");
        input2.setBehaviorIcdO3("3");
        input2.setDateOfDiagnosisYear("1998");
        input2.setLaterality("1");
        result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(MphOutput.Result.MULTIPLE_PRIMARIES, result.getResult());
        assertEquals("mp_2007_breast", result.getGroupId());
        assertEquals("M5", result.getStep());
        assertEquals("Tumors diagnosed more than five (5) years apart are multiple primaries.", result.getReason());
        assertEquals(2, result.getAppliedRules().size());
        assertEquals("Are there tumors diagnosed more than five (5) years apart?", result.getAppliedRules().get(1).getQuestion());
    }

    @Test
    public void testLenientMode() throws IOException {
        MphInput input1 = new MphInput();
        MphInput input2 = new MphInput();
        input1.setPrimarySite("C502");
        input1.setHistologyIcdO3("8500");
        input1.setBehaviorIcdO3("3");
        input1.setLaterality("1");
        input1.setDateOfDiagnosisYear("2015");
        input1.setDateOfDiagnosisMonth("8");
        input1.setDateOfDiagnosisDay("17");

        input2.setPrimarySite("C502");
        input2.setHistologyIcdO3("8000");
        input2.setBehaviorIcdO3("3");
        input2.setLaterality("1");
        input2.setDateOfDiagnosisYear("2015");
        input2.setDateOfDiagnosisMonth("10");
        input2.setDateOfDiagnosisDay("28");

        // not passing should default to STRICT
        MphOutput result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(9, result.getAppliedRules().size());
        assertEquals(MphOutput.Result.MULTIPLE_PRIMARIES, result.getResult());
        assertEquals("M12", result.getStep());

        // specify STRICT
        result = _MPH.mph(new MphInputPair(input1, input2), HistologyMatchMode.STRICT).execute().body();
        assertEquals(9, result.getAppliedRules().size());
        assertEquals(MphOutput.Result.MULTIPLE_PRIMARIES, result.getResult());
        assertEquals("M12", result.getStep());

        // specify LENIENT
        result = _MPH.mph(new MphInputPair(input1, input2), HistologyMatchMode.LENIENT).execute().body();
        assertEquals(10, result.getAppliedRules().size());
        assertEquals(MphOutput.Result.SINGLE_PRIMARY, result.getResult());
        assertEquals("M13", result.getStep());
    }

    @Test(expected = BadRequestException.class)
    public void testInvalidInput() throws IOException {
        MphInput i1 = new MphInput();
        MphInput i2 = new MphInput();

        // invalid primary site
        i1.setPrimarySite("C080");
        i2.setPrimarySite("D080");
        i1.setHistologyIcdO3("8000");
        i1.setBehaviorIcdO3("3");
        i2.setHistologyIcdO3("8100");
        i2.setBehaviorIcdO3("3");
        i1.setDateOfDiagnosisYear("2015");
        i2.setDateOfDiagnosisYear("2015");
        _MPH.mph(new MphInputPair(i1, i2)).execute().body();
    }

}