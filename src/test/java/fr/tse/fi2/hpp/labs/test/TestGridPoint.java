package fr.tse.fi2.hpp.labs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.tse.fi2.hpp.labs.beans.GridPoint;

public class TestGridPoint {

	@Test
	public void testEqualsObject() {
		//fail("Not yet implemented");
		GridPoint gp1=new GridPoint(5,5);
		GridPoint gp2= new GridPoint(5,5);
		assertEquals(gp1, gp2);
	
	}

}
