/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import com.imsweb.seerapi.client.publishable.PublishableSearch;

public class GlossarySearch extends PublishableSearch {

    public GlossarySearch() {
        super();
    }

    public GlossarySearch(String query) {
        super();

        setQuery(query);
    }

}
