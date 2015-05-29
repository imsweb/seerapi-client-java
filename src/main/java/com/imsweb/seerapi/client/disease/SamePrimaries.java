package com.imsweb.seerapi.client.disease;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SamePrimaries {

    @JsonProperty("disease1")
    protected String _disease1;
    @JsonProperty("disease2")
    protected String _disease2;
    @JsonProperty("year")
    protected Integer _year;
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

    public Integer getYear() {
        return _year;
    }

    public void setYear(Integer year) {
        _year = year;
    }

    public Boolean isSame() {
        return _isSame;
    }

    public Boolean getIsSame() {
        return _isSame;
    }

    public void setIsSame(Boolean isSame) {
        _isSame = isSame;
    }
}
