/*
 * Copyright (C) 2013 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.glossary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.imsweb.seerapi.client.shared.Changelog;

public class GlossaryChangelog extends Changelog {

    @JsonProperty("adds")
    private List<GlossaryChangelogEntry> _adds;
    @JsonProperty("deletes")
    private List<GlossaryChangelogEntry> _deletes;
    @JsonProperty("mods")
    private List<GlossaryChangelogEntry> _mods;

    public List<GlossaryChangelogEntry> getAdds() {
        return _adds;
    }

    public void setAdds(List<GlossaryChangelogEntry> adds) {
        _adds = adds;
    }

    public List<GlossaryChangelogEntry> getDeletes() {
        return _deletes;
    }

    public void setDeletes(List<GlossaryChangelogEntry> deletes) {
        _deletes = deletes;
    }

    public List<GlossaryChangelogEntry> getMods() {
        return _mods;
    }

    public void setMods(List<GlossaryChangelogEntry> mods) {
        _mods = mods;
    }

}
