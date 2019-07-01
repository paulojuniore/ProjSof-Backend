package com.backend.psoft.comparators;

import com.backend.psoft.model.Subject;

import java.util.Comparator;

public class SubjectsComparatorComments implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((Subject)o1).
                compareToComents(((Subject)o2));
    }
}
