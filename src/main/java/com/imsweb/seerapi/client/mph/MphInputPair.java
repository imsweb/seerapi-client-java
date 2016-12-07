package com.imsweb.seerapi.client.mph;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MphInputPair {

    public MphInputPair() {
    }

    public MphInputPair(MphInput input1, MphInput input2) {
        _input1 = input1;
        _input2 = input2;
    }

    @JsonProperty("input1")
    private MphInput _input1;

    @JsonProperty("input2")
    private MphInput _input2;

    public MphInput getInput1() {
        return _input1;
    }

    public void setInput1(MphInput input1) {
        _input1 = input1;
    }

    public MphInput getInput2() {
        return _input2;
    }

    public void setInput2(MphInput input2) {
        _input2 = input2;
    }

}
