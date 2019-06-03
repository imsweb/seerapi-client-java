package com.imsweb.seerapi.client.hcpcs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.junit.BeforeClass;
import org.junit.Test;

import retrofit2.Response;

import com.imsweb.seerapi.client.NotFoundException;
import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.hcpcs.Hcpcs.Category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class HcpcsServiceTest {

    private static HcpcsService _HCPCS;

    @BeforeClass
    public static void setup() {
        _HCPCS = new SeerApi.Builder().connect().hcpcs();
    }

    @Test
    public void testGetProcedureByCode() throws IOException {
        Hcpcs proc = _HCPCS.getProcedure("S0087").execute().body();

        assertThat(proc).isNotNull();
        assertThat(proc.getHcpcsCode()).isEqualTo("S0087");
        assertThat(proc.getGenericName()).isEqualTo("Alemtuzumab");
        assertThat(proc.getBrandNames()).containsExactly("Campath");
        assertThat(proc.getStrength()).isEqualTo("30 mg");
        assertThat(proc.getFdaApprovalYear()).isEqualTo("2001");
        assertThat(proc.getFdaDiscontinuationYear()).isEqualTo("2012");
        assertThat(proc.getCmsApprovalDate()).isEqualTo("2002-01-01");
        assertThat(proc.getCmsDiscontinuationDate()).isEqualTo("2002-12-31");
        assertThat(proc.getCategories()).containsExactly(Category.IMMUNOTHERAPY);
        assertThat(proc.getMajorDrugClass()).isEqualTo("Monoclonal Antibody");
        assertThat(proc.getMinorDrugClass()).isEqualTo("CD52");
        assertThat(proc.getOral()).isEqualTo(false);
        assertThat(proc.getDateAdded()).isNotNull();
        assertThat(proc.getDateModified()).isNotNull();

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> _HCPCS.getProcedure("bad_code").execute())
                .withMessage("HCPCS code '%s' does not exist.", "bad_code");
    }

    @Test
    public void testSearch() throws IOException {
        Response<List<Hcpcs>> response = _HCPCS.search(new HashMap<>()).execute();
        assertThat(response.headers().get("X-Total-Count")).isNotNull();
        assertThat(Integer.valueOf(Objects.requireNonNull(response.headers().get("X-Total-Count")))).isGreaterThan(100);

        List<Hcpcs> results = response.body();
        assertThat(results).hasSize(25);  // default page size is 25

        Map<String, String> params = new HashMap<>();
        params.put("page", "2");

        // test second page
        assertThat(_HCPCS.search(params).execute().body()).hasSize(25);

        // test page out of range
        params.put("page", "1000");
        assertThat(_HCPCS.search(params).execute().body()).hasSize(0);

        // test searching
        params.clear();
        params.put("q", "Ixempra");
        results = _HCPCS.search(params).execute().body();
        assertThat(results).hasSize(2);
        assertThat(results).extracting("hcpcsCode").contains("J9207", "C9240");
        assertThat(Objects.requireNonNull(results).get(0).getScore()).isGreaterThan(0);

        // test categories
        params.put("category", Category.CHEMOTHERAPY.toString());
        results = _HCPCS.search(params).execute().body();
        assertThat(results).hasSize(2);
        assertThat(results).extracting("hcpcsCode").contains("J9207", "C9240");

        params.put("category", Category.IMMUNOTHERAPY.toString());
        assertThat(_HCPCS.search(params).execute().body()).hasSize(0);
    }
}