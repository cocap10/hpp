package fr.tse.fi2.hpp.labs.utils;

import java.util.Comparator;
import java.util.HashMap;

import fr.tse.fi2.hpp.labs.beans.GridPoint;

public class ValueComparator implements Comparator<GridPoint> {
	HashMap<GridPoint, Float> base;
	public ValueComparator(HashMap<GridPoint, Float> b) {
		// TODO Auto-generated constructor stub
		this.base=b;
	}

	@Override
	public int compare(GridPoint o1, GridPoint o2) {
		if (base.get(o1) > base.get(o2)) {
            return -1;
        } else if (base.get(o1) < base.get(o2)) {
            return 1;
        } else {
            return 0;
        }
	}
	

}
