/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.cs;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Simple Java object that contains all of the relevant information for a csCalculate result
 */
public class CsResult {

    @JsonProperty("schema_number")
    protected Integer _schemaNum;
    @JsonProperty("schema_name")
    protected String _schemaName;
    @JsonProperty("csver_derived")
    protected String _csVersionDerived;
    @JsonProperty("result")
    protected Integer _result;
    @JsonProperty("error")
    protected Long _error;
    @JsonProperty("messages")
    protected String _messages;

    // storage outputs
    @JsonProperty("stor_ajcc7_tdescriptor")
    protected String _storageAjcc7TDescriptor;
    @JsonProperty("stor_ajcc7_t")
    protected String _storageAjcc7T;
    @JsonProperty("stor_ajcc7_ndescriptor")
    protected String _storageAjcc7NDescriptor;
    @JsonProperty("stor_ajcc7_n")
    protected String _storageAjcc7N;
    @JsonProperty("stor_ajcc7_mdescriptor")
    protected String _storageAjcc7MDescriptor;
    @JsonProperty("stor_ajcc7_m")
    protected String _storageAjcc7M;
    @JsonProperty("stor_ajcc7_stage")
    protected String _storageAjcc7StageGroup;

    @JsonProperty("stor_ajcc6_tdescriptor")
    protected String _storageAjcc6TDescriptor;
    @JsonProperty("stor_ajcc6_t")
    protected String _storageAjcc6T;
    @JsonProperty("stor_ajcc6_ndescriptor")
    protected String _storageAjcc6NDescriptor;
    @JsonProperty("stor_ajcc6_n")
    protected String _storageAjcc6N;
    @JsonProperty("stor_ajcc6_mdescriptor")
    protected String _storageAjcc6MDescriptor;
    @JsonProperty("stor_ajcc6_m")
    protected String _storageAjcc6M;
    @JsonProperty("stor_ajcc6_stage")
    protected String _storageAjcc6StageGroup;

    @JsonProperty("stor_ss77")
    protected String _storageSs1977;
    @JsonProperty("stor_ss2000")
    protected String _storageSs2000;

    // display Outputs
    @JsonProperty("ajcc7_tdescriptor")
    protected String _displayAjcc7TDescriptor;
    @JsonProperty("ajcc7_t")
    protected String _displayAjcc7T;
    @JsonProperty("ajcc7_ndescriptor")
    protected String _displayAjcc7NDescriptor;
    @JsonProperty("ajcc7_n")
    protected String _displayAjcc7N;
    @JsonProperty("ajcc7_mdescriptor")
    protected String _displayAjcc7MDescriptor;
    @JsonProperty("ajcc7_m")
    protected String _displayAjcc7M;
    @JsonProperty("ajcc7_stage")
    protected String _displayAjcc7StageGroup;

    @JsonProperty("ajcc6_tdescriptor")
    protected String _displayAjcc6TDescriptor;
    @JsonProperty("ajcc6_t")
    protected String _displayAjcc6T;
    @JsonProperty("ajcc6_ndescriptor")
    protected String _displayAjcc6NDescriptor;
    @JsonProperty("ajcc6_n")
    protected String _displayAjcc6N;
    @JsonProperty("ajcc6_mdescriptor")
    protected String _displayAjcc6MDescriptor;
    @JsonProperty("ajcc6_m")
    protected String _displayAjcc6M;
    @JsonProperty("ajcc6_stage")
    protected String _displayAjcc6StageGroup;

    @JsonProperty("t77")
    protected String _displayT1977;
    @JsonProperty("n77")
    protected String _displayN1977;
    @JsonProperty("m77")
    protected String _displayM1977;
    @JsonProperty("ss77")
    protected String _displaySs1977;
    @JsonProperty("t2000")
    protected String _displayT2000;
    @JsonProperty("n2000")
    protected String _displayN2000;
    @JsonProperty("m2000")
    protected String _displayM2000;
    @JsonProperty("ss2000")
    protected String _displaySs2000;
    @JsonProperty("involved_table")
    protected List<CsInvolvedTable> _involvedTables;
    @JsonProperty("problem_codes")
    protected List<CsCodeValidity> _problemCodes;

    public Integer getSchemaNumber() {
        return _schemaNum;
    }

    public String getSchemaName() {
        return _schemaName;
    }

    public String getCsVersionDerived() {
        return _csVersionDerived;
    }

    public Integer getResult() {
        return _result;
    }

    public Long getError() {
        return _error;
    }

    public String getMessages() {
        return _messages;
    }

    public String getStorageAjcc7TDescriptor() {
        return _storageAjcc7TDescriptor;
    }

    public String getStorageAjcc7T() {
        return _storageAjcc7T;
    }

    public String getStorageAjcc7NDescriptor() {
        return _storageAjcc7NDescriptor;
    }

    public String getStorageAjcc7N() {
        return _storageAjcc7N;
    }

    public String getStorageAjcc7MDescriptor() {
        return _storageAjcc7MDescriptor;
    }

    public String getStorageAjcc7M() {
        return _storageAjcc7M;
    }

    public String getStorageAjcc7StageGroup() {
        return _storageAjcc7StageGroup;
    }

    public String getStorageAjcc6TDescriptor() {
        return _storageAjcc6TDescriptor;
    }

    public String getStorageAjcc6T() {
        return _storageAjcc6T;
    }

    public String getStorageAjcc6NDescriptor() {
        return _storageAjcc6NDescriptor;
    }

    public String getStorageAjcc6N() {
        return _storageAjcc6N;
    }

    public String getStorageAjcc6MDescriptor() {
        return _storageAjcc6MDescriptor;
    }

    public String getStorageAjcc6M() {
        return _storageAjcc6M;
    }

    public String getStorageAjcc6StageGroup() {
        return _storageAjcc6StageGroup;
    }

    public String getStorageSs1977() {
        return _storageSs1977;
    }

    public String getStorageSs2000() {
        return _storageSs2000;
    }

    public String getDisplayAjcc7TDescriptor() {
        return _displayAjcc7TDescriptor;
    }

    public String getDisplayAjcc7T() {
        return _displayAjcc7T;
    }

    public String getDisplayAjcc7NDescriptor() {
        return _displayAjcc7NDescriptor;
    }

    public String getDisplayAjcc7N() {
        return _displayAjcc7N;
    }

    public String getDisplayAjcc7MDescriptor() {
        return _displayAjcc7MDescriptor;
    }

    public String getDisplayAjcc7M() {
        return _displayAjcc7M;
    }

    public String getDisplayAjcc7StageGroup() {
        return _displayAjcc7StageGroup;
    }

    public String getDisplayAjcc6TDescriptor() {
        return _displayAjcc6TDescriptor;
    }

    public String getDisplayAjcc6T() {
        return _displayAjcc6T;
    }

    public String getDisplayAjcc6NDescriptor() {
        return _displayAjcc6NDescriptor;
    }

    public String getDisplayAjcc6N() {
        return _displayAjcc6N;
    }

    public String getDisplayAjcc6MDescriptor() {
        return _displayAjcc6MDescriptor;
    }

    public String getDisplayAjcc6M() {
        return _displayAjcc6M;
    }

    public String getDisplayAjcc6StageGroup() {
        return _displayAjcc6StageGroup;
    }

    public String getDisplayT1977() {
        return _displayT1977;
    }

    public String getDisplayN1977() {
        return _displayN1977;
    }

    public String getDisplayM1977() {
        return _displayM1977;
    }

    public String getDisplaySs1977() {
        return _displaySs1977;
    }

    public String getDisplayT2000() {
        return _displayT2000;
    }

    public String getDisplayN2000() {
        return _displayN2000;
    }

    public String getDisplayM2000() {
        return _displayM2000;
    }

    public String getDisplaySs2000() {
        return _displaySs2000;
    }

    public List<CsInvolvedTable> getInvolvedTables() {
        return _involvedTables;
    }

    public List<CsCodeValidity> getProblemCodes() {
        return _problemCodes;
    }
}