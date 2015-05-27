package fr.tse.fi2.hpp.labs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.tse.fi2.hpp.labs.beans.GridPoint;
import fr.tse.fi2.hpp.labs.beans.Route;

public class TestRoute {

	@Test
	public void testEqualsObject() {
		//fail("Not yet implemented");
		Route r1 = new Route(new GridPoint(5, 5), new GridPoint(10, 10));
		Route r2 = new Route(new GridPoint(5, 5), new GridPoint(10, 10));
		assertEquals(r1, r2);
	}

}
