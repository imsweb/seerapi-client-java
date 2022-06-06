/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.rx;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.shared.Changelog;

public class RxChangelog extends Changelog {

    @JsonProperty("adds")
    private List<RxChangelogEntry> _adds;
    @JsonProperty("deletes")
    private List<RxChangelogEntry> _deletes;
    @JsonProperty("mods")
    private List<RxChangelogEntry> _mods;

    public List<RxChangelogEntry> getAdds() {
        return _adds;
    }

    public void setAdds(List<RxChangelogEntry> adds) {
        _adds = adds;
    }

    public List<RxChangelogEntry> getDeletes() {
        return _deletes;
    }

    public void setDeletes(List<RxChangelogEntry> deletes) {
        _deletes = deletes;
    }

    public List<RxChangelogEntry> getMods() {
        return _mods;
    }

    public void setMods(List<RxChangelogEntry> mods) {
        _mods = mods;
    }

}
