package affichage;
import javax.swing.JPanel;

import exceptions.SegmentException;

public class Face extends JPanel {
	private Segment s1;
	private Segment s2;
	private Segment s3;

	public Face(Segment s1, Segment s2, Segment s3) throws SegmentException {
		this.s1=s1;
		this.s2=s2;
		this.s3=s3;
	}
	
	//renvoie le premier segment de la face
	public Segment getS1() {
		return s1;
	}
	
	//renvoie le deuxieme segment de la face 
	public Segment getS2() {
		return s2;
	}
	
	//renvoie le troisieme segment de la face
	public Segment getS3() {
		return s3;
	}

	//renvoie le barycentre d'une face 
	public Point barycentre(){
		double x =  this.s1.getDébut().getX() + this.s2.getDébut().getX() + this.s3.getDébut().getX(); 
		double y =  this.s1.getDébut().getY() + this.s2.getDébut().getY() + this.s3.getDébut().getY(); 
		double z =  this.s1.getDébut().getZ() + this.s2.getDébut().getZ() + this.s3.getDébut().getZ(); 
		return new Point(x/3,y/3,z/3);
	}

}
