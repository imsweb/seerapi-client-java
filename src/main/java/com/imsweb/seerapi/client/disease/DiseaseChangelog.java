/*
 * Copyright (C) 2012 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.disease;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.shared.Changelog;

public class DiseaseChangelog extends Changelog {

    @JsonProperty("adds")
    private List<DiseaseChangelogEntry> _adds;
    @JsonProperty("deletes")
    private List<DiseaseChangelogEntry> _deletes;
    @JsonProperty("mods")
    private List<DiseaseChangelogEntry> _mods;

    public List<DiseaseChangelogEntry> getAdds() {
        return _adds;
    }

    public void setAdds(List<DiseaseChangelogEntry> adds) {
        _adds = adds;
    }

    public List<DiseaseChangelogEntry> getDeletes() {
        return _deletes;
    }

    public void setDeletes(List<DiseaseChangelogEntry> deletes) {
        _deletes = deletes;
    }

    public List<DiseaseChangelogEntry> getMods() {
        return _mods;
    }

    public void setMods(List<DiseaseChangelogEntry> mods) {
        _mods = mods;
    }

}
