package fr.tse.fi2.hpp.labs.beans;

import java.util.Comparator;

public class GridPoint implements Comparable<GridPoint>{
	/**
	 * Coordinates on the Grid
	 */
	int x, y;

	public GridPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPoint other = (GridPoint) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String rtn=this.x+"."+this.y;
		return rtn;
	}

	@Override
	public int compareTo(GridPoint o) {
		int num1 = (this.y-1)*600+this.x;//x_max=300 dans query 1
		int num2 = (o.getY()-1)*600+o.getX();//x_max=300 dans query 1
		
		return Integer.compare(num1, num2);
	}

	
	

}
