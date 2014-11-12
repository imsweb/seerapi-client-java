/*
 * Copyright (C) 2014 Information Management Services, Inc.
 */
package com.imsweb.seerapi.client.staging;

/**
 * For some reason, the className was being stored for every TmnStringRange in MongoDB.  I think it was getting confused
 * because the TnmTableRow has a Map<String, List<TnmStringRange>>. Since the string range was buried two levels deep it thought it
 * needed to add the className.  The workaround I used was to add "@Entity(noClassnameStored = true)" even though you don't normally
 * need to add the @Entity to an @Embedded class.  It seems to fix the problem.
 */

public class StagingStringRange {

    private String _low;
    private String _high;

    /**
     * Construct a BasicString range that matches any string
     */
    public StagingStringRange() {
    }

    /**
     * Construct a BasicStringRange with a low and high bound
     * @param low
     * @param high
     */
    public StagingStringRange(String low, String high) {
        if (low == null || high == null)
            throw new IllegalStateException("Invalid range");
        if (low.length() != high.length())
            throw new IllegalStateException("Range strings must be the same length: '" + low + "-" + high + "'");
        if (low.compareTo(high) > 0)
            throw new IllegalStateException("Low value of range is greater than high value: '" + low + "-" + high + "'");

        _low = low;
        _high = high;
    }

    /**
     * If low and high are both null, then this range matches all strings
     * @return
     */
    private boolean matchesAll() {
        return _low == null && _high == null;
    }

    public boolean contains(String value) {
        return (matchesAll() || (value != null && _low.compareTo(value) <= 0 && _high.compareTo(value) >= 0));
    }
}
