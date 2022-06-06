/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.mph;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MphRule {

    @JsonProperty("group_id")
    private String _groupId;
    @JsonProperty("step")
    private String _step;
    @JsonProperty("question")
    private String _question;
    @JsonProperty("reason")
    private String _reason;
    @JsonProperty("notes")
    private List<String> _notes;
    @JsonProperty("examples")
    private List<String> _examples;

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

    public String getQuestion() {
        return _question;
    }

    public void setQuestion(String question) {
        _question = question;
    }

    public String getReason() {
        return _reason;
    }

    public void setReason(String reason) {
        _reason = reason;
    }

    public List<String> getNotes() {
        return _notes;
    }

    public void setNotes(List<String> notes) {
        _notes = notes;
    }

    public List<String> getExamples() {
        return _examples;
    }

    public void setExamples(List<String> examples) {
        _examples = examples;
    }
}
