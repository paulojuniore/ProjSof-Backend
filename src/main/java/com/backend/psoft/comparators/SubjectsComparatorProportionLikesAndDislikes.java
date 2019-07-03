package com.backend.psoft.comparators;

import java.util.Comparator;

import com.backend.psoft.model.Subject;

public class SubjectsComparatorProportionLikesAndDislikes implements Comparator<Subject> {

	@Override
	public int compare(Subject arg0, Subject arg1) {
		return arg0.compareToProportionLikesAndDislikes(arg1);
	}

}
