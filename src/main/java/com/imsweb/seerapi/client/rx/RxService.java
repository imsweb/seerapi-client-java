/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

public interface RxService {

    //
    //    /**
    //     * Return a list of all Rx versions and information about them.  Note that by default the Rx entity does not include relevant glossary references.
    //     * @return a list of RxVersion objects
    //     */
    //    public List<RxVersion> rxVersions() {
    //        WebTarget target = createTarget("/rx/versions");
    //
    //        return getBuilder(target).get(new GenericType<List<RxVersion>>() {});
    //    }
    //
    //    /**
    //     * Return a complete Rx entity based in identifier
    //     * @param version Rx version
    //     * @param id Rx identifier
    //     * @return a Rx object
    //     */
    //    public Rx rxById(String version, String id) {
    //        return rxById(version, id, false);
    //    }
    //
    //    /**
    //     * Return a complete Rx entity based in identifier
    //     * @param version Rx version
    //     * @param id Rx identifier
    //     * @param includeGlossary if true, include the glossary
    //     * @return a Rx object
    //     */
    //
    //    public Rx rxById(String version, String id, boolean includeGlossary) {
    //        WebTarget target = createTarget("/rx/{version}/id/{id}")
    //                .resolveTemplate("version", version)
    //                .resolveTemplate("id", id)
    //                .queryParam("glossary", includeGlossary);
    //
    //        return getBuilder(target).get(Rx.class);
    //    }
    //
    //    /**
    //     * Return a list of matching Rx entities
    //     * @param version Rx version
    //     * @param search RxSearch object
    //     * @return a RxSearchResults object
    //     */
    //    public RxSearchResults rxSearch(String version, RxSearch search) {
    //        WebTarget target = createTarget("/rx/{version}").resolveTemplate("version", version);
    //
    //        target = target.queryParam("q", search.getQuery())
    //                .queryParam("type", search.getType())
    //                .queryParam("do_not_code", search.getDoNotCode())
    //                .queryParam("category", search.getCategory())
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
    //        return getBuilder(target).get(RxSearchResults.class);
    //    }
    //
    //    /**
    //     * Return the changelog entries for the passed database version
    //     * @param version Rx version
    //     * @param fromDate if not null, only include changes from this date forward (YYYY-MM-DD)
    //     * @param toDate if not null, only include changes prior to this date (YYYY-MM-DD)
    //     * @param count if not null, limit the number returned
    //     * @return a list of RxChangelogResults objects
    //     */
    //    public RxChangelogResults rxChangelogs(String version, String fromDate, String toDate, Integer count) {
    //        WebTarget target = createTarget("/rx/{version}/changelog")
    //                .resolveTemplate("version", version)
    //                .queryParam("from", fromDate)
    //                .queryParam("to", toDate)
    //                .queryParam("count", count);
    //
    //        return getBuilder(target).get(RxChangelogResults.class);
    //    }
    //

}
