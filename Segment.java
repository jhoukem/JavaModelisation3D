package affichage;

import exceptions.SegmentException;


public class Segment {
	private Point p1;
	private Point p2;

	public Segment(Point p1, Point p2) throws SegmentException {
		if(p1 == p2)
			throw new SegmentException();
		this.p1=p1;
		this.p2=p2;
	}
	

	
	public String toString() {
		return "["+p1.toString() + ", " + p2.toString() + "]";
	}
	//renvoie le debut du segment
	public Point getDebut() {
		return p1;
	}
	
	//renvoie la fin du segment
	public Point getFin() {
		return p2;
	}

}
