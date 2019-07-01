package com.backend.psoft.comparators;

import com.backend.psoft.model.Subject;

import java.util.Comparator;

public class SubjectsComparatorLikes implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((Subject)o1).
                compareToLikes(((Subject)o2));
    }
}
