/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.ndc;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import retrofit2.Response;

import com.imsweb.seerapi.client.SeerApi;
import com.imsweb.seerapi.client.ndc.NdcSeerInfo.Category;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ConstantConditions")
public class NdcTest {

    private static NdcService _NDC;

    @BeforeClass
    public static void setup() {
        _NDC = new SeerApi.Builder().connect().ndc();
    }

    @Test
    public void testNdcByCode() throws IOException {
        NdcProduct product = _NDC.getByCode("0002-3227").execute().body();

        assertThat(product).isNotNull();
        assertThat(product.getNdc()).isEqualTo("0002-3227");
        assertThat(product.getTypeName()).isEqualTo("HUMAN PRESCRIPTION DRUG");
        assertThat(product.getProprietaryName()).isEqualTo("Strattera");
        assertThat(product.getProprietaryNameSuffix()).isNull();
        assertThat(product.getNonProprietaryName()).containsExactly("Atomoxetine hydrochloride");
        assertThat(product.getDosageFormName()).isEqualTo("CAPSULE");
        assertThat(product.getRouteName()).containsExactly("ORAL");
        assertThat(product.getStartMarketingDate()).isEqualTo("20021126");
        assertThat(product.getEndMarketingDate()).isNull();
        assertThat(product.getMarketingCategoryName()).isEqualTo("NDA");
        assertThat(product.getApplicationNumber()).isEqualTo("NDA021411");
        assertThat(product.getLabelerName()).isEqualTo("Eli Lilly and Company");
        assertThat(product.getDeaSchedule()).isNull();
        assertThat(product.getSubstances()).hasSize(1);

        NdcSubstance substance = product.getSubstances().get(0);
        assertThat(substance.getName()).isEqualTo("ATOMOXETINE HYDROCHLORIDE");
        assertThat(substance.getStrengthNumber()).isEqualTo("10");
        assertThat(substance.getStrengthUnit()).isEqualTo("mg/1");

        assertThat(product.getPharmClass()).containsExactly("Norepinephrine Reuptake Inhibitor [EPC]", "Norepinephrine Uptake Inhibitors [MoA]");

        assertThat(product.getPackages()).isNotEmpty();
        assertThat(product.getPackages().get(0).getCode()).isEqualTo("30");
        assertThat(product.getPackages().get(0).getDescription()).isEqualToIgnoringWhitespace("30 CAPSULE in 1 BOTTLE (0002-3227-30)");

        assertThat(product.getDateAdded()).isNotNull();
        assertThat(product.getDateModified()).isNotNull();
        assertThat(product.getDateRemoved()).isNull();

        // test one with "seer" added information (0002-4483)
        product = _NDC.getByCode("0002-4483").execute().body();
        assertThat(product.getNdc()).isEqualTo("0002-4483");
        assertThat(product.getProprietaryName()).isEqualTo("Verzenio");
        assertThat(product.getSeerInfo()).as("must have 'seerinfo'").isNotNull();
        assertThat(product.getSeerInfo().getCategories()).containsExactly(Category.CHEMOTHERAPY);
        assertThat(product.getSeerInfo().getMajorDrugClass()).contains("Cyclin Dependent");
        assertThat(product.getSeerInfo().getMinorDrugClass()).isEqualTo("CDK 4/6");

        // the subcategory is being removed but still exists in production; the key should be ignored
        product = _NDC.getByCode("0006-0072").execute().body();
        assertThat(product.getSeerInfo()).as("must have 'seerinfo'").isNotNull();
        assertThat(product.getSeerInfo().getCategories()).containsExactly(Category.ANCILLARY);
    }

    @Test
    public void testNdcSearch() throws IOException {
        NdcSearch search = new NdcSearch();
        search.setIncludeRemoved(true);

        Response<List<NdcProduct>> response = _NDC.search(search.paramMap()).execute();

        // hold onto total number (including "removed")
        Integer totalIncludingRemoved = Integer.valueOf(response.headers().get("X-Total-Count"));
        assertThat(totalIncludingRemoved).isGreaterThan(100000);

        List<NdcProduct> products = response.body();
        assertThat(products).hasSize(25);

        search.setQuery("daklinza");
        products = _NDC.search(search.paramMap()).execute().body();
        assertThat(products.size()).isGreaterThan(1);

        search.setRemovedSince("2019-06-03");
        products = _NDC.search(search.paramMap()).execute().body();
        assertThat(products).isEmpty();

        // test removed
        search = new NdcSearch();
        search.setIncludeRemoved(false);
        response = _NDC.search(search.paramMap()).execute();
        Integer totalExcludingRemoved = Integer.valueOf(response.headers().get("X-Total-Count"));

        assertThat(totalIncludingRemoved).isGreaterThan(totalExcludingRemoved);
    }

    @Test
    public void testNdcSearchByCategory() throws IOException {
        NdcSearch search = new NdcSearch();
        search.setCategory(Category.CHEMOTHERAPY);

        Response<List<NdcProduct>> response = _NDC.search(search.paramMap()).execute();

        assertThat(Integer.valueOf(response.headers().get("X-Total-Count"))).isGreaterThan(0);

        List<NdcProduct> products = response.body();
        assertThat(products).isNotEmpty();

        NdcProduct product = products.get(0);
        assertThat(product.getSeerInfo()).as("must have 'seerinfo'").isNotNull();
        assertThat(product.getSeerInfo().getCategories()).contains(Category.CHEMOTHERAPY);
    }

    @Test
    public void testNdcHasSeerInfo() throws IOException {
        NdcSearch search = new NdcSearch();
        search.setIncludeRemoved(true);

        // get count of all drugs
        Response<List<NdcProduct>> response = _NDC.search(search.paramMap()).execute();
        Integer totalCount = Integer.parseInt(response.headers().get("X-Total-Count"));
        assertThat(totalCount).isGreaterThan(0);

        // get count of seer-info drugs
        search.setHasSeerInfo(true);
        response = _NDC.search(search.paramMap()).execute();
        Integer withCount = Integer.parseInt(response.headers().get("X-Total-Count"));
        assertThat(withCount).isGreaterThan(0).isLessThan(totalCount);

        // get count of non seer-info drugs
        search.setHasSeerInfo(false);
        response = _NDC.search(search.paramMap()).execute();
        Integer withoutCount = Integer.parseInt(response.headers().get("X-Total-Count"));
        assertThat(withCount).isGreaterThan(0).isLessThan(totalCount);

        assertThat(totalCount).isEqualTo(withCount + withoutCount);
    }

}
