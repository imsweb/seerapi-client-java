package com.imsweb.seerapi.client.hcpcs.mph;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import retrofit2.Call;

import com.imsweb.seerapi.client.BadRequestException;
import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.mph.MphInput;
import com.imsweb.seerapi.client.mph.MphInputPair;
import com.imsweb.seerapi.client.mph.MphOutput;
import com.imsweb.seerapi.client.mph.MphRule;
import com.imsweb.seerapi.client.mph.MphService;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MphTest {

    private static MphService _MPH;

    @BeforeAll
    public static void setup() {
        _MPH = new SeerApi.Builder().connect().mph();
    }

    @Test
    void testMissingAllInput() {
        Call<MphOutput> call = _MPH.mph(new MphInputPair());
        assertThrows(BadRequestException.class, call::execute);
    }

    @Test
    void testMissingSite() throws IOException {
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

        Call<MphOutput> call = _MPH.mph(new MphInputPair(input1, input2));
        assertThrows(BadRequestException.class, call::execute);
    }

    @Test
    void testResults() throws IOException {
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
        assertNotNull(result);
        assertEquals(MphOutput.Result.SINGLE_PRIMARY, result.getResult());
        assertEquals("mph_2007_to_2017_breast", result.getGroupId());
        assertEquals("M13", result.getStep());
        assertEquals("Tumors that do not meet any of the criteria are abstracted as a single primary.", result.getReason());
        assertEquals(10, result.getAppliedRules().size());
        assertTrue(result.getAppliedRules().get(0).getQuestion().startsWith("Are there tumors in sites with ICD-O-3 topography codes"));

        // next test questionable (setting laterality to unknown for one of the diseases)
        input2.setLaterality("9");
        result = _MPH.mph(new MphInputPair(input1, input2)).execute().body();
        assertNotNull(result);
        assertEquals(MphOutput.Result.QUESTIONABLE, result.getResult());
        assertEquals("mph_2007_to_2017_breast", result.getGroupId());
        assertEquals("M7", result.getStep());
        assertEquals("Unable to apply Rule M7 of MPH 2007-2017 Breast. Valid and known laterality should be provided.", result.getReason());
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
        assertNotNull(result);
        assertEquals(MphOutput.Result.MULTIPLE_PRIMARIES, result.getResult());
        assertEquals("mph_2007_to_2017_breast", result.getGroupId());
        assertEquals("M5", result.getStep());
        assertEquals("Tumors diagnosed more than five (5) years apart are multiple primaries.", result.getReason());
        assertEquals(2, result.getAppliedRules().size());
        assertEquals("Are there tumors diagnosed more than five (5) years apart?", result.getAppliedRules().get(1).getQuestion());
    }

    @Test
    void testLenientMode() throws IOException {
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
        assertNotNull(result);
        assertEquals(9, result.getAppliedRules().size());
        assertEquals(MphOutput.Result.MULTIPLE_PRIMARIES, result.getResult());
        assertEquals("M12", result.getStep());
    }

    @Test
    void testInvalidInput() throws IOException {
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

        Call<MphOutput> call = _MPH.mph(new MphInputPair(i1, i2));
        assertThrows(BadRequestException.class, call::execute);
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(MphRule.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(MphInput.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(MphInputPair.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
        MatcherAssert.assertThat(MphOutput.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }

}