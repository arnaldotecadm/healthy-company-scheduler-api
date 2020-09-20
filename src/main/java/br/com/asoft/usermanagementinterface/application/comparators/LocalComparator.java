package br.com.asoft.usermanagementinterface.application.comparators;

import java.util.Comparator;

import br.com.asoft.usermanagementinterface.model.Local;

public class LocalComparator implements Comparator<Local> {
	public int compare(Local local1, Local local2) {
		return local1.getId().compareTo(local2.getId());
	}
}
