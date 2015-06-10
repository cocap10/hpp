package fr.tse.fi2.hpp.labs.test;

import junit.framework.Assert;

import org.junit.Test;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.dispatcher.StreamingDispatcher;

public class TestParseline {

	private final String[] toTest = 
		{"3AD6E3E7183533095E90A48647018CE6,2D2CF56B35716D1AC18934BBEF4B8135,2013-01-01 00:05:00,2013-01-01 00:14:00,540,2.13,-73.952911,40.803532,-73.939079,40.826721,CSH,9.50,0.50,0.50,0.00,0.00,10.50",
			" 224B67C639FC310D8069A0BFC6699343,A6A101DE6CBD7F4E09CD135F4C6F8026,2013-01-01 00:06:00,2013-01-01 00:14:00,480,1.96,-73.964508,40.764778,-73.971794,40.744137,CRD,9.00,0.50,0.50,1.00,0.00,11.00"};
		

	@Test
	public void testParsing() {
		StreamingDispatcher dispatch = new StreamingDispatcher(
				"src/main/resources/data/1000Records.csv");
		for (String underTest : toTest) {
			DebsRecord res = dispatch.process(underTest);
			Assert.assertNotNull(res);			
		}
	}

}
