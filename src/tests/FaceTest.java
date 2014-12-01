package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import affichages.Face;
import affichages.Point;
import affichages.Segment;
import exceptions.SegmentException;


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
	public void distanceToCamera(){
	
	}
	
	@Test
	public void testGetVecteurNormal(){
		
	}
	
	@Test 
	public void testGetRadian(){
		
	}
	
	@Test
	public void testSetPolygonColor(){
		
	}
}
