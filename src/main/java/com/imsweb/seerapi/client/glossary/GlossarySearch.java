/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.Set;

import com.imsweb.seerapi.client.publishable.PublishableSearch;

public class GlossarySearch extends PublishableSearch {

    /**
     * Glossary-specific parameters
     */
    private Set<Glossary.Category> _category;

    public Set<Glossary.Category> getCategory() {
        return _category;
    }

    public void setCategory(Set<Glossary.Category> category) {
        _category = category;
    }
}
