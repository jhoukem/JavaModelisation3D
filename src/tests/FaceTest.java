package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import affichages.Face;
import affichages.Point;
import affichages.Segment;
import exceptions.SegmentException;
import exceptions.VectorException;


public class FaceTest  {
	
	@Test
	public void testBarycentre() throws SegmentException{
		Point p1 = new Point(3,0,0);
		Point p2 = new Point(0,3,0);
		Point p3 = new Point(0,0,3);
		Segment s1 = new Segment(p1,p2);
		Segment s2 = new Segment(p2,p3);
		Segment s3 = new Segment(p3,p1);
		Face f = new Face(s1,s2,s3);
		assertEquals(f.barycentre().toString(), new Point(1,1,1).toString());
	}
	
	@Test
	public void distanceToCamera() throws SegmentException{
		Point p1 = new Point(1,-2,1);
		Point p2 = new Point(1,1,-2);
		Point p3 = new Point(-2,1,1);
		Segment s1 = new Segment(p1,p2);
		Segment s2 = new Segment(p2,p3);
		Segment s3 = new Segment(p3,p1);
		Face f = new Face(s1,s2,s3);
		assertEquals(f.distanceToCamera(),100000,001);
	}
	
	@Test
	public void testGetVecteurNormal() throws SegmentException, VectorException{
			Point p1 = new Point(1,0,0);
			Point p2 = new Point(0,1,0);
			Point p3 = new Point(0,0,1);
			Segment s1 = new Segment(p1, p2);
			Segment s2 = new Segment(p2, p3);
			Segment s3 = new Segment(p3, p1);
			Face f = new Face (s1, s2, s3);
			assertEquals(f.getVecteurNormal().toString()," 1.0\n 1.0\n 1.0\n");
	}
	
	@Test 
	public void testGetRadian() throws SegmentException, VectorException{
		Point p1 = new Point(1,0,0);
		Point p2 = new Point(0,1,0);
		Point p3 = new Point(0,0,0);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p2, p3);
		Segment s3 = new Segment(p3, p1);
		Face f = new Face (s1, s2, s3);
		Point p4 = new Point(0,0,0);
		Point p5 = new Point(0,1,0);
		Point p6 = new Point(0,0,1);
		Segment s4 = new Segment(p4, p5);
		Segment s5 = new Segment(p5, p6);
		Segment s6 = new Segment(p6, p4);
		Face f2 = new Face (s4, s5, s6);
		assertEquals(f.getRadian(),1, 0.00001);
		assertEquals(f2.getRadian(),0, 0.00001);		
	}
	
	@Test
	public void testSetPolygonColor() throws SegmentException{
		Point p1 = new Point(1,0,0);
		Point p2 = new Point(0,1,0);
		Point p3 = new Point(0,0,0);
		Segment s1 = new Segment(p1, p2);
		Segment s2 = new Segment(p2, p3);
		Segment s3 = new Segment(p3, p1);
		Face f = new Face (s1, s2, s3);
		Point p4 = new Point(0,0,0);
		Point p5 = new Point(0,1,0);
		Point p6 = new Point(0,0,1);
		Segment s4 = new Segment(p4, p5);
		Segment s5 = new Segment(p5, p6);
		Segment s6 = new Segment(p6, p4);
		Face f2 = new Face (s4, s5, s6);
		Color c = new Color(0,0,0);
		Color c2 = new Color(255,255,255);
		assertEquals(f.getCouleur(), c);
		assertEquals(f2.getCouleur(), c2);
	}
}
