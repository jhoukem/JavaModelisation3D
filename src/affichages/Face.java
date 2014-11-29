package affichages;
import java.awt.Polygon;

import javax.swing.JPanel;

import exceptions.SegmentException;

@SuppressWarnings("serial")
public class Face implements Comparable<Face> {
	
	double[] xpoints=new double[3];
	double[] ypoints=new double[3];
	double[] zpoints=new double[3];

	public Face(Segment s1, Segment s2, Segment s3) throws SegmentException {
		
		

		xpoints[0] =  s1.getDebut().getX();
		xpoints[1] =  s1.getFin().getX();
		ypoints[0] =  s1.getDebut().getY();
		ypoints[1] =  s1.getFin().getY();
		zpoints[0] =  s1.getDebut().getZ();
		zpoints[1] =  s1.getFin().getZ();

		if(s2.getDebut().equalsCoord(s1.getDebut()) || s2.getDebut().equalsCoord(s1.getFin()) ){
			xpoints[2]= s2.getFin().getX();
			ypoints[2]= s2.getFin().getY();
			zpoints[2]= s2.getFin().getZ();

		}
		else{		
			xpoints[2]= s2.getDebut().getX();
			ypoints[2]= s2.getDebut().getY();	
			zpoints[2]= s2.getDebut().getZ();
		}


	}
	//methode de math pour calculer la distance entre deux pts a voir avec benoit

	public double distanceToCamera() {
		Point b= barycentre();
		return Math.sqrt(Math.pow((Camera.getInstance().getX()-b.getX()),2)+Math.pow((Camera.getInstance().getY()-b.getY()),2)+Math.pow((Camera.getInstance().getZ()-b.getZ()),2));
	}

	//renvoie le barycentre d'une face 
	public Point barycentre(){
		double x =  this.getX1() + this.getX2() + this.getX3(); 
		double y =  this.getY1() + this.getY2() + this.getY3(); 
		double z =  this.getZ1() + this.getZ2() + this.getZ3(); 
		return new Point(x/3,y/3,z/3);
	}

	private double getX3() {
		return xpoints[2];
	}

	private double getX2() {
		return xpoints[1];	
	}

	private double getX1() {
		return xpoints[0];	
	}

	private double getY3() {
		return ypoints[2];
	}

	private double getY2() {
		return ypoints[1];	
	}

	private double getY1() {
		return ypoints[0];	
	}

	private double getZ3() {
		return zpoints[2];
	}

	private double getZ2() {
		return zpoints[1];	
	}

	private double getZ1() {
		return zpoints[0];	
	}

	@Override
	public int compareTo(Face a) {
		return (int)(this.distanceToCamera() - a.distanceToCamera());
	}


}
