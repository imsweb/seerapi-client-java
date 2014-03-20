package com.imsweb.seerapi.client;

import org.codehaus.jackson.annotate.JsonProperty;

public class ErrorResponseConverter {

    @JsonProperty("code")
    protected Integer _id;
    @JsonProperty("message")
    protected String _message;

    /**
     * Constuctor
     */
    public ErrorResponseConverter(Integer id, String message) {
        this._id = id;
        this._message = message;
    }

}
