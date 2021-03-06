package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SamePrimaries {

    @JsonProperty("disease1")
    protected String _disease1;
    @JsonProperty("disease2")
    protected String _disease2;
    @JsonProperty("year1")
    protected Integer _year1;
    @JsonProperty("year2")
    protected Integer _year2;
    @JsonProperty("is_same")
    protected Boolean _isSame;

    public String getDisease1() {
        return _disease1;
    }

    public void setDisease1(String disease1) {
        _disease1 = disease1;
    }

    public String getDisease2() {
        return _disease2;
    }

    public void setDisease2(String disease2) {
        _disease2 = disease2;
    }

    public Integer getYear1() {
        return _year1;
    }

    public void setYear1(Integer year1) {
        _year1 = year1;
    }

    public Integer getYear2() {
        return _year2;
    }

    public void setYear2(Integer year2) {
        _year2 = year2;
    }

    public Boolean getIsSame() {
        return _isSame;
    }

    public void setIsSame(Boolean isSame) {
        _isSame = isSame;
    }
}
