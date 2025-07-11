package com.imsweb.seerapi.client.naaccr;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"version", "year_implemented", "date_of_publication"})
public class NaaccrVersion {

    private String version;
    private Integer yearImplemented;
    private OffsetDateTime dateOfPublication;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("year_implemented")
    public Integer getYearImplemented() {
        return yearImplemented;
    }

    public void setYearImplemented(Integer yearImplemented) {
        this.yearImplemented = yearImplemented;
    }

    @JsonProperty("date_of_publication")
    public OffsetDateTime getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(OffsetDateTime dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

}