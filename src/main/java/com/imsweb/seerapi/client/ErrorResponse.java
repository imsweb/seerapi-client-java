package com.imsweb.seerapi.client;

import org.codehaus.jackson.annotate.JsonProperty;

public class ErrorResponse {

    @JsonProperty("code")
    protected Integer _id;
    @JsonProperty("message")
    protected String _message;

    /**
     * Default constructor
     */
    public ErrorResponse() {
    }

    /**
     * Constucts with an identifier and message
     */
    public ErrorResponse(Integer id, String message) {
        this._id = id;
        this._message = message;
    }

    /**
     * Return the error identifier
     * @return an error identifier
     */
    public Integer getId() {
        return _id;
    }

    /**
     * Return the error message
     * @return an error message
     */
    public String getMessage() {
        return _message;
    }
}
