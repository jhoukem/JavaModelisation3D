package affichages;

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
	
	//public void paintComponent(Graphics g){
		//g.drawLine(x1, y1, x2, y2);
	//}
	
	public String toString() {
		return "["+p1.toString() + ", " + p2.toString() + "]";
	}
	//renvoie le début du segment
	public Point getDebut() {
		return p1;
	}
	
	//renvoie la fin du segment
	public Point getFin() {
		return p2;
	}

}
