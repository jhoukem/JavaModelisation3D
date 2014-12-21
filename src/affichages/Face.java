package affichages;
import java.awt.Color;

import maths_package.Matrice;
import exceptions.SegmentException;
import exceptions.VectorException;

public class Face implements Comparable<Face> {

	Segment s1, s2, s3;
	double[] xpoints=new double[3];
	double[] ypoints=new double[3];
	double[] zpoints=new double[3];
	private Color couleur;

	public Face(Segment s1, Segment s2, Segment s3) throws SegmentException {

		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
		couleur = new Color(255,255,255);
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
		try {
			setPolygonColor();
		} catch (VectorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String toString() {
		return "["+ s1.toString() + ", " + s2.toString() + ", " + s3.toString() + "]";
	}
	
	//methode de math pour calculer la distance entre les barycentre de deux face n�c�ssaire pr le tri

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

	public Matrice getVecteurNormal() throws VectorException {
		Matrice m1 = new Matrice(3,1);
		Matrice m2 = new Matrice(3,1);
		m1.setElem(0, 0, this.getX2() - this.getX1());
		m1.setElem(1, 0, this.getY2() - this.getY1());
		m1.setElem(2, 0, this.getZ2() - this.getZ1());
		m2.setElem(0, 0, this.getX3() - this.getX1());
		m2.setElem(1, 0, this.getY3() - this.getY1());
		m2.setElem(2, 0, this.getZ3() - this.getZ1());
		return Matrice.getProduitVectoriel(m1, m2);
	}

	public Color getCouleur(){
		return couleur;
	}

	public void setCouleur(Color c){
		this.couleur = c;
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

	public double getRadian() throws VectorException{
		Matrice m1 = new Matrice(new double[][] {{0},{0},{1}});
		Matrice m2 = getVecteurNormal();
		double num = Math.abs(m1.getElem(0,0)*m2.getElem(0,0)+m1.getElem(1,0)*m2.getElem(1,0)+m1.getElem(2,0)*m2.getElem(2,0));
		double den = Math.sqrt(Math.pow(m1.getElem(0, 0), 2)+Math.pow(m1.getElem(1, 0), 2)+Math.pow(m1.getElem(2, 0), 2)) * Math.sqrt(Math.pow(m2.getElem(0, 0), 2)+Math.pow(m2.getElem(1, 0), 2)+Math.pow(m2.getElem(2, 0), 2));
		return num/den;
	}

	public void setPolygonColor() throws VectorException{
		int x = (int) (255*(this.getRadian()));
		Color c = new Color(x/2,x/3, x/6);//permet d'obtenir une couleur marron
		setCouleur(c);
	}


	@Override//compare deux face a et b
	public int compareTo(Face a) {
		return -((Double)barycentre().getZ()).compareTo(a.barycentre().getZ()); 
	} 																				


}
