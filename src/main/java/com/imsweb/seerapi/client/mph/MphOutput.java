package com.imsweb.seerapi.client.mph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MphOutput {

    // The possible results of determining if two tumors are single or multiple primaries.
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
    @JsonProperty("reason")
    private String _reason;
    @JsonProperty("applied_rules")
    private List<MphRule> _appliedRules;
    @JsonProperty("group_id")
    private String _groupId;
    @JsonProperty("step")
    private String _step;

    public MphOutput() {
    }

    public Result getResult() {
        return _result;
    }

    public void setResult(Result result) {
        _result = result;
    }

    public String getReason() {
        return _reason;
    }

    public void setReason(String reason) {
        _reason = reason;
    }

    public List<MphRule> getAppliedRules() {
        return _appliedRules;
    }

    public void setAppliedRules(List<MphRule> appliedRules) {
        _appliedRules = appliedRules;
    }

    public String getGroupId() {
        return _groupId;
    }

    public void setGroupId(String groupId) {
        _groupId = groupId;
    }

    public String getStep() {
        return _step;
    }

    public void setStep(String step) {
        _step = step;
    }
}
