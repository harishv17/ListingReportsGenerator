package com.harish.ListingReportsGenerator.comparator;

import java.util.Comparator;
import java.util.Map;

public class FrequencyComparator implements Comparator<Integer> {

    private final Map<Integer, Long> freqMap;

    public FrequencyComparator(Map<Integer, Long> freqMap) {
        this.freqMap = freqMap;
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return freqMap.get(o2).compareTo(freqMap.get(o1));
    }
}
