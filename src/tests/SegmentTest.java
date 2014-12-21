package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import affichages.Point;
import affichages.Segment;
import exceptions.SegmentException;

public class SegmentTest {

	@Test
	public void testSegment() throws SegmentException {
		Point p1 = new Point(0,0,0);
		Point p2 = new Point(1,1,1);
		Segment s1 = new Segment(p1, p2);
		assertEquals(s1.toString(), "[(0.0, 0.0, 0.0), (1.0, 1.0, 1.0)]");
		assertEquals(s1.getDebut(), p1);
		assertEquals(s1.getFin(), p2);
	}

}
