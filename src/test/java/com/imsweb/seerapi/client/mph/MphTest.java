package com.imsweb.seerapi.client.mph;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.imsweb.seerapi.client.BadRequestException;
import com.imsweb.seerapi.client.SeerApi;

import static org.junit.Assert.assertEquals;

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

    @Test(expected = BadRequestException.class)
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

        _MPH.mph(new MphInputPair(input1, input2)).execute();
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
        MphResult result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(MphResult.Result.SINGLE_PRIMARY, result.getResult());

        // next test questionable (setting laterality to unknown for one of the diseases)
        input2.setLaterality("9");
        result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertEquals(MphResult.Result.QUESTIONABLE, result.getResult());

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
        assertEquals(MphResult.Result.MULTIPLE_PRIMARIES, result.getResult());
    }

}