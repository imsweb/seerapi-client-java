package com.imsweb.seerapi.client;

import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.imsweb.seerapi.client.disease.Disease;
import com.imsweb.seerapi.client.disease.SiteRange;
import com.imsweb.seerapi.client.disease.YearRange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObjectMapperTest {

    @Test
    void testMapper() throws IOException {
        Range range = new Range("10", "40");

        ObjectMapper mapper = SeerApi.getMapper();

        String json = mapper.writeValueAsString(range);

        assertTrue(json.contains("low"));
        assertFalse(json.contains("lowValue"));

        // now test reading that back in
        range = mapper.readValue(json, Range.class);

        assertEquals("10", range.getLowValue());
        assertEquals("40", range.getHighValue());
    }

    @Test
    void testDiseaseMapping() throws IOException {
        Disease partial = new Disease();

        partial.setType(Disease.Type.HEMATO);
        partial.setIcdO3Morphology("9840/3");
        partial.setIcdO2Morphology(Collections.singletonList("9840/3"));
        partial.setIcdO1Morphology(Collections.singletonList("9840/3"));
        partial.setIcdO3Effective(new YearRange(2001, null));
        partial.setIcdO2Effective(new YearRange(1992, 2000));
        partial.setIcdO1Effective(new YearRange(1978, 2001));
        partial.setPrimarySite(Collections.singletonList(new SiteRange("C421", "C421")));

        ObjectMapper mapper = SeerApi.getMapper();

        String json = mapper.writeValueAsString(partial);

        assertTrue(json.contains("icdO3_morphology"));
        assertFalse(json.contains("icdO3Morphology"));

        // now test reading that back in
        partial = mapper.readValue(json, Disease.class);

        assertEquals("9840/3", partial.getIcdO3Morphology());
    }

}