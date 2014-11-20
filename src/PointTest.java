package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import affichage.Point;

public class PointTest {
	
	@Test
	public void testAdd(){
		Point p1 = new Point(1, 2, 3);
		Point p2 = new Point(3, 2, 1);
		Point p3 = new Point(1.5,1.5,1.5);
		assertEquals(Point.add(p1, p2).toString(), new Point(4,4,4).toString());
		assertEquals(Point.add(p1, p3).toString(), new Point(2.5,3.5,4.5).toString());
	}
}
