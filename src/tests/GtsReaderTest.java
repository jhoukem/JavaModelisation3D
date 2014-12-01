package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import affichages.Face;
import affichages.GtsReader;
import affichages.Point;
import affichages.Segment;
import exceptions.SegmentException;

public class GtsReaderTest {



	@Test
	public void testInfos() throws SegmentException {
		GtsReader g = new GtsReader("tetrahedron.gts");
		int[] infos = new int[]{4,6,4};
		for(int i=0; i< infos.length; i++)
			assertEquals(infos[i], g.getInfos()[i]);
	}

	@Test
	public void testPoints() throws SegmentException {
		GtsReader g = new GtsReader("tetrahedron.gts");
		Point[] pts = new Point[]{new Point(-1.63299, -0.942809, -0.666667), new Point(0, 1.88562, -0.666667), new Point(1.63299, -0.942809, -0.666667), new Point(0, 0, 2)};
		for(int i=0; i< pts.length; i++)
			assertEquals(pts[i].toString(), g.getPoints()[i].toString());
	}

	@Test
	public void testSegments() throws SegmentException {
		GtsReader g = new GtsReader("tetrahedron.gts");
		Point[] pts = new Point[]{new Point(-1.63299, -0.942809, -0.666667), new Point(0, 1.88562, -0.666667), new Point(1.63299, -0.942809, -0.666667), new Point(0, 0, 2)};
		Segment[] sgmts = new Segment[]{new Segment(pts[1],pts[0]), new Segment(pts[2],pts[1]), new Segment(pts[1],pts[3]), new Segment(pts[0],pts[2]), new Segment(pts[3],pts[0]),new Segment(pts[2],pts[3])};
		for(int i=0; i< sgmts.length; i++)
			assertEquals(sgmts[i].toString(), g.getSegments()[i].toString());

	}

	@Test
	public void testFaces() throws SegmentException {
		GtsReader g = new GtsReader("tetrahedron.gts");
		Point[] pts = new Point[]{new Point(-1.63299, -0.942809, -0.666667), new Point(0, 1.88562, -0.666667), new Point(1.63299, -0.942809, -0.666667), new Point(0, 0, 2)};
		Segment[] sgmts = new Segment[]{new Segment(pts[1],pts[0]), new Segment(pts[2],pts[1]), new Segment(pts[1],pts[3]), new Segment(pts[0],pts[2]), new Segment(pts[3],pts[0]),new Segment(pts[2],pts[3])};
		Face[] fces = new Face[]{new Face(sgmts[0],sgmts[1],sgmts[3]), new Face(sgmts[4],sgmts[2],sgmts[0]), new Face(sgmts[2],sgmts[5],sgmts[1]), new Face(sgmts[5],sgmts[4],sgmts[3])};
		for(int i=0; i< fces.length; i++)
			assertEquals(fces[i].toString(), g.getFaces()[i].toString());
	}
}
