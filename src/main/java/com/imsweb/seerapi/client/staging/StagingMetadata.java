package com.imsweb.seerapi.client.staging;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonPropertyOrder({"name", "start", "end"})
@JsonDeserialize(using = StagingMetadataDeserializer.class)
public class StagingMetadata {

    private String _name;
    private Integer _start;
    private Integer _end;

    public StagingMetadata() {
    }

    public StagingMetadata(String name) {
        _name = name;
    }

    public StagingMetadata(String name, Integer start) {
        _name = name;
        _start = start;
    }

    public StagingMetadata(String name, Integer start, Integer end) {
        _name = name;
        _start = start;
        _end = end;
    }

    @JsonProperty("name")
    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    @JsonProperty("start")
    public Integer getStart() {
        return _start;
    }

    public void setStart(Integer start) {
        _start = start;
    }

    @JsonProperty("end")
    public Integer getEnd() {
        return _end;
    }

    public void setEnd(Integer end) {
        _end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        StagingMetadata that = (StagingMetadata)o;
        return Objects.equals(_name, that._name) &&
                Objects.equals(_start, that._start) &&
                Objects.equals(_end, that._end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _start, _end);
    }
}
