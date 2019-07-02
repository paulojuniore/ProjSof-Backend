package com.backend.psoft.comparators;

import com.backend.psoft.model.Subject;

import java.util.Comparator;

public class SubjectsComparatorLikes implements Comparator<Subject> {

	@Override
	public int compare(Subject arg0, Subject arg1) {
		return arg0.compareToLikes(arg1);
	}
}
