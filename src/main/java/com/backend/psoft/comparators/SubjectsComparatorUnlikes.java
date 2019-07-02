package com.backend.psoft.comparators;

import com.backend.psoft.model.Subject;

import java.util.Comparator;

public class SubjectsComparatorUnlikes implements Comparator<Subject> {

	@Override
	public int compare(Subject o1, Subject o2) {
		return o1.compareToUnlikes(o2);
	}

}
