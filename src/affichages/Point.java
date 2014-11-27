package affichage;

public class Point {
	
	private double x;
	private double y;
	private double z;
	
	public Point(double x, double y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	//renvoie l'abscysse du point
	public double getX() {
		return x;
	}
	
	//renvoie l'ordonnee du point
	public double getY() {
		return y;
	}
	
	//renvoie la cote du point 
	public double getZ() {
		return z;
	}
	
	//affiche les coordonnees du point
	public String toString(){
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
	//renvoie l'addition de 2 points 
	public static Point add(Point p1, Point p2){
		return new Point(p1.x + p2.x, p1.y + p2.y, p1.z + p2.z);
	}
	
	public boolean equalsCoord(double x1, double x2, double y1, double y2, double z1, double z2){
		return (x1 == x2 && y1==y2 && z1==z2);
	}
}
