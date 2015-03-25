package org.bin2.goose.signaling;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;

/**
 * Created by benoi_000 on 3/25/2015.
 */
public class UserRtcInfo {
    private final String id;
    private String dsp;
    private final Collection<String> candidates;

    public UserRtcInfo(String id) {
        this.id = id;
        this.candidates = Sets.newConcurrentHashSet();
    }

    public void setDSP(String dsp) {
        this.dsp = dsp;
    }

    public void addCandidate(String value) {
        this.candidates.add(value);
    }

    public String getId() {
        return id;
    }

    public String getDsp() {
        return dsp;
    }

    public Collection<String> getCandidates() {
        return candidates;
    }

    public Collection<String> getMessages() {
        List<String> result = Lists.newArrayList();
        if (dsp!=null) result.add(dsp);
        result.addAll(candidates);
        return result;
    }
}
