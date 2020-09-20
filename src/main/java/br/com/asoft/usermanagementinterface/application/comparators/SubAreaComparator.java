package br.com.asoft.usermanagementinterface.application.comparators;

import java.util.Comparator;

import br.com.asoft.usermanagementinterface.model.SubArea;

public class SubAreaComparator implements Comparator<SubArea> {
	public int compare(SubArea subArea1, SubArea subArea2) {
		return subArea1.getId().compareTo(subArea2.getId());
	}
}
