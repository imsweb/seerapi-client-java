/*
 * Copyright (C) 2015 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

public interface StagingService {

    //    /**
    //     * Return a list of all supported staging algorithms
    //     * @return a list of StagingAlgorithm objects
    //     */
    //    public List<StagingAlgorithm> stagingAlgorithms() {
    //        WebTarget target = createTarget("/staging/algorithms");
    //
    //        return getBuilder(target).get(new GenericType<List<StagingAlgorithm>>() {});
    //    }
    //
    //    /**
    //     * Return a list of supported versions for the passed algorithm
    //     * @param algorithm an algorithm identifier
    //     * @return
    //     */
    //    public List<StagingVersion> stagingAlgorithmVersions(String algorithm) {
    //        WebTarget target = createTarget("/staging/{algorithm}/versions").resolveTemplate("algorithm", algorithm);
    //
    //        return getBuilder(target).get(new GenericType<List<StagingVersion>>() {});
    //    }
    //
    //    /**
    //     * Return a list of matching schemas
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param query an optional text query
    //     * @return a list of schemas
    //     */
    //    public List<StagingSchemaInfo> stagingSchemas(String algorithm, String version, String query) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/schemas")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version)
    //                .queryParam("q", query);
    //
    //        return getBuilder(target).get(new GenericType<List<StagingSchemaInfo>>() {});
    //    }
    //
    //    /**
    //     * Perform a schema lookup
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param data a StagingData object containing the input for the lookup
    //     * @return
    //     */
    //    public List<StagingSchemaInfo> stagingSchemaLookup(String algorithm, String version, SchemaLookup data) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/schemas/lookup")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version);
    //
    //        return getBuilder(target).post(Entity.json(data.getInputs()), new GenericType<List<StagingSchemaInfo>>() {});
    //    }
    //
    //    /**
    //     * Return a single schema definition by schema identifier
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param id a schema identifier
    //     * @return a schema object
    //     */
    //    public StagingSchema stagingSchemaById(String algorithm, String version, String id) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/schema/{id}")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version)
    //                .resolveTemplate("id", id);
    //
    //        return getBuilder(target).get(StagingSchema.class);
    //    }
    //
    //    /**
    //     * Return a list of tables which are involved in the specified schema
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param id a schema identifier
    //     */
    //    public List<StagingTable> stagingSchemaInvolvedTables(String algorithm, String version, String id) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/schema/{id}/tables")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version)
    //                .resolveTemplate("id", id);
    //
    //        return getBuilder(target).get(new GenericType<List<StagingTable>>() {});
    //    }
    //
    //    /**
    //     * Return a list of matching tables
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param query an optional text query
    //     * @return
    //     */
    //    public List<StagingTable> stagingTables(String algorithm, String version, String query) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/tables")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version)
    //                .queryParam("q", query);
    //
    //        return getBuilder(target).get(new GenericType<List<StagingTable>>() {});
    //    }
    //
    //    /**
    //     * Return a single table definition by table identifier
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param id a table identifier
    //     * @return
    //     */
    //    public StagingTable stagingTableById(String algorithm, String version, String id) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/table/{id}")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version)
    //                .resolveTemplate("id", id);
    //
    //        return getBuilder(target).get(StagingTable.class);
    //    }
    //
    //    /**
    //     * Return a list of schemas which the specified table is involved in
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param id a table identifier
    //     */
    //    public List<StagingSchema> stagingTableInvolvedSchemas(String algorithm, String version, String id) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/table/{id}/schemas")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version)
    //                .resolveTemplate("id", id);
    //
    //        return getBuilder(target).get(new GenericType<List<StagingSchema>>() {});
    //    }
    //
    //    /**
    //     * Stage the passed input
    //     * @param algorithm an algorithm identifier
    //     * @param version a version
    //     * @param data a StagingData object containing the input for the staging call
    //     * @return
    //     */
    //    public StagingData stagingStage(String algorithm, String version, StagingData data) {
    //        WebTarget target = createTarget("/staging/{algorithm}/{version}/stage")
    //                .resolveTemplate("algorithm", algorithm)
    //                .resolveTemplate("version", version);
    //
    //        return getBuilder(target).post(Entity.json(data.getInput()), StagingData.class);
    //    }

}
