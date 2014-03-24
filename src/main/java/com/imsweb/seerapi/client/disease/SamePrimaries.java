package com.imsweb.seerapi.client.disease;

import org.codehaus.jackson.annotate.JsonProperty;

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

    public String getDisease2() {
        return _disease2;
    }

    public Integer getYear() {
        return _year;
    }

    public Boolean isSame() {
        return _isSame;
    }
}
