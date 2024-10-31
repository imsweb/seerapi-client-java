package com.imsweb.seerapi.client.hcpcs;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import retrofit2.Response;

import com.imsweb.seerapi.client.NotFoundException;
import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.hcpcs.Hcpcs.Category;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

class HcpcsServiceTest {

    private static HcpcsService _HCPCS;

    @BeforeAll
    public static void setup() {
        _HCPCS = new SeerApi.Builder().connect().hcpcs();
    }

    @Test
    void testGetProcedureByCode() throws IOException {
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
        assertThat(proc.getOral()).isFalse();
        assertThat(proc.getDateAdded()).isNotNull();
        assertThat(proc.getDateModified()).isNotNull();

        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> _HCPCS.getProcedure("bad_code").execute())
                .withMessage("HCPCS code '%s' does not exist.", "bad_code");

        // test the new description field and
        proc = _HCPCS.getProcedure("G6015").execute().body();
        assertThat(proc).isNotNull();
        assertThat(proc.getDescription()).contains("temporally modulated beams, binary, dynamic mlc, per treatment session");

        // verify multiple categories are supported
        proc = _HCPCS.getProcedure("A9545").execute().body();
        assertThat(proc).isNotNull();
        assertThat(proc.getCategories()).containsExactlyInAnyOrder(Category.IMMUNOTHERAPY, Category.RADIOPHARMACEUTICAL);

    }

    @Test
    void testSearch() throws IOException {
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
        assertThat(_HCPCS.search(params).execute().body()).isEmpty();

        // test searching
        params.clear();
        params.put("q", "Ixempra");
        results = _HCPCS.search(params).execute().body();
        assertThat(results).hasSize(2);
        assertThat(results).extracting("hcpcsCode").contains("J9207", "C9240");

        assertThat(Objects.requireNonNull(results).get(0).getScore()).isPositive();

        // test categories
        params.put("category", Category.CHEMOTHERAPY.toString());
        results = _HCPCS.search(params).execute().body();
        assertThat(results).hasSize(2);
        assertThat(results).extracting("hcpcsCode").contains("J9207", "C9240");

        params.put("category", Category.IMMUNOTHERAPY.toString());
        assertThat(_HCPCS.search(params).execute().body()).isEmpty();
    }

    @Test
    void testBeans() {
        MatcherAssert.assertThat(Hcpcs.class, CoreMatchers.allOf(hasValidBeanConstructor(), hasValidGettersAndSetters()));
    }
}