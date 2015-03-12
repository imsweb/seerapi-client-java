/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.Set;

import com.imsweb.seerapi.client.publishable.PublishableSearch;
import com.imsweb.seerapi.client.rx.Rx.DoNoCodeValue;
import com.imsweb.seerapi.client.rx.Rx.Type;

public class RxSearch extends PublishableSearch {

    /**
     * RX-specific
     */
    private Type _type;
    private Set<String> _category;
    private DoNoCodeValue _doNotCode;

    public RxSearch() {
        super();
    }

    public RxSearch(String query) {
        setQuery(query);
    }

    public RxSearch(String query, Type type) {
        setQuery(query);
        setType(type);
    }

    public Type getType() {
        return _type;
    }

    public void setType(Type type) {
        _type = type;
    }

    public Set<String> getCategory() {
        return _category;
    }

    public void setCategory(Set<String> category) {
        _category = category;
    }

    public DoNoCodeValue getDoNotCode() {
        return _doNotCode;
    }

    public void setDoNotCode(DoNoCodeValue doNotCode) {
        _doNotCode = doNotCode;
    }
}
