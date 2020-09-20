package br.com.asoft.usermanagementinterface.application.comparators;

import java.util.Comparator;

import br.com.asoft.usermanagementinterface.model.Area;

public class AreaComparator implements Comparator<Area> {
	public int compare(Area area1, Area area2) {
		return area1.getId().compareTo(area2.getId());
	}
}
