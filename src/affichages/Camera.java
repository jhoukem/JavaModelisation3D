package affichage;

import javax.swing.JPanel;

public class Camera extends JPanel {

	private static final long serialVersionUID = 1L;

	static private Camera theInstance = null;
	
	private int x;
	private int y;
	private int z;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}


	
	private Camera() {
		x=this.getWidth()/2;
		y= this.getHeight()/2;
		z=100000;
	}
	
	static public Camera getInstance() {
		if (theInstance == null) {
			theInstance = new Camera();
		}
		return theInstance;
	}
}
