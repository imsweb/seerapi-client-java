package com.imsweb.seerapi.client.mph;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MphResult {

    /**
     * The possible result of determining if two tumors are single or multiple primaries.
     */
    public enum Result {
        // indicates the two tumors are the same primary
        SINGLE_PRIMARY,
        // indicates the two tumors are different primaries
        MULTIPLE_PRIMARIES,
        // indicates there is not enough information to make a proper determination
        QUESTIONABLE
    }

    @JsonProperty("result")
    private Result _result;

    public Result getResult() {
        return _result;
    }

    public void setResult(Result result) {
        _result = result;
    }
}
