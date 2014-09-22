/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.Set;

import com.imsweb.seerapi.client.publishable.PublishableSearch;

public class RxSearch extends PublishableSearch {

    /**
     * RX-specific
     */
    private Rx.Type _type;
    private Set<String> _category;
    private Boolean _doNotCode;

    public RxSearch() {
        super();
    }

    public RxSearch(String query) {
        setQuery(query);
    }

    public RxSearch(String query, Rx.Type type) {
        setQuery(query);
        setType(type);
    }

    public Rx.Type getType() {
        return _type;
    }

    public void setType(Rx.Type type) {
        _type = type;
    }

    public Set<String> getCategory() {
        return _category;
    }

    public void setCategory(Set<String> category) {
        _category = category;
    }

    public Boolean getDoNotCode() {
        return _doNotCode;
    }

    public void setDoNotCode(Boolean doNotCode) {
        _doNotCode = doNotCode;
    }
}
