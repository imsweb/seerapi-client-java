/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

public interface GlossaryService {

    //
    //    /**
    //     * Return a list of all glossary versions and information about them
    //     * @return a list of GlossaryVersion objects
    //     */
    //    public List<GlossaryVersion> glossaryVersions() {
    //        WebTarget target = createTarget("/glossary/versions");
    //
    //        return getBuilder(target).get(new GenericType<List<GlossaryVersion>>() {});
    //    }
    //
    //    /**
    //     * Return a complete glossary entity based in identifier
    //     * @param version Glossary version
    //     * @param id Glossary identifier
    //     * @return a Glossary object
    //     */
    //    public Glossary glossaryById(String version, String id) {
    //        WebTarget target = createTarget("/glossary/{version}/id/{id}").resolveTemplate("version", version).resolveTemplate("id", id);
    //
    //        return getBuilder(target).get(Glossary.class);
    //    }
    //
    //    /**
    //     * Return a list of matching glossaries
    //     * @param version Glossary version
    //     * @param search GlossarySearch object
    //     * @return a GlossarySearchResults object
    //     */
    //    public GlossarySearchResults glossarySearch(String version, GlossarySearch search) {
    //        WebTarget target = createTarget("/glossary/{version}").resolveTemplate("version", version);
    //
    //        target = target.queryParam("q", search.getQuery())
    //                .queryParam("mode", search.getMode())
    //                .queryParam("status", search.getStatus())
    //                .queryParam("assigned_to", search.getAssignedTo())
    //                .queryParam("modified_from", search.getModifiedFrom())
    //                .queryParam("modified_to", search.getModifiedTo())
    //                .queryParam("published_from", search.getPublishedFrom())
    //                .queryParam("published_to", search.getPublishedTo())
    //                .queryParam("been_published", search.getBeenPublished())
    //                .queryParam("hidden", search.getHidden())
    //                .queryParam("count", search.getCount())
    //                .queryParam("offset", search.getOffset())
    //                .queryParam("order", search.getOrderBy())
    //                .queryParam("output_type", search.getOutputType());
    //
    //        // list parameters need to passed as an object array to get multiple query parameters; otherwise there is a single query
    //        // parameter with a list of values, which the API won't understand
    //        if (search.getCategory() != null)
    //            target = target.queryParam("category", search.getCategory().toArray());
    //
    //        return getBuilder(target).get(GlossarySearchResults.class);
    //    }
    //
    //    /**
    //     * Return the changelog entries for the passed database version
    //     * @param version Glossary version
    //     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
    //     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
    //     * @param count if not null, limit the number returned
    //     * @return a list of GlossaryChangelogResults objects
    //     */
    //    public GlossaryChangelogResults glossaryChangelogs(String version, String fromDate, String toDate, Integer count) {
    //        WebTarget target = createTarget("/glossary/{version}/changelog")
    //                .resolveTemplate("version", version)
    //                .queryParam("from", fromDate)
    //                .queryParam("to", toDate)
    //                .queryParam("count", count);
    //
    //        return getBuilder(target).get(GlossaryChangelogResults.class);
    //    }

}
