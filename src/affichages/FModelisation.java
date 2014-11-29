package affichages;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import maths_package.Matrice;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;

public class FModelisation extends JPanel implements MouseWheelListener {

	private static final long serialVersionUID = 1L;
	private int xSize, ySize;
	private GtsReader gts;
	private ArrayList<Face> f;
	private int[] infos;
	private int[][] numsgmts;
	private int[][] numfces;
	private Face[] fces;
	private Point[] pts;
	private Segment[] sgmts;
	private Matrice Matrix;
	private int zoom=10;
	
	public FModelisation() throws SegmentException {
		try {

			this.addMouseWheelListener(this);
			gts = new GtsReader("tie.gts");
			infos = gts.getInfos();
			numsgmts = gts.getNumsgmts();
			numfces = gts.getNumfces();
			pts = gts.getPoints();
			sgmts = gts.getSegments();
			fces = gts.getFaces();
			setTranslation();
		} catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		xSize = this.getWidth();
		ySize = this.getHeight();	

		for(int i=0;i<f.size();i++){
			int[] x= new int[3];
			int [] y= new int[3];
			for(int j =0;j<3;j++){
				x[j]= (int)(f.get(i).xpoints[j]*zoom+xSize/2);
				y[j]= (int)(f.get(i).ypoints[j]*zoom+ySize/2);
			}
			g.drawPolygon(x, y, x.length);
			//g.fillPolygon(x, y, x.length);
		}
	}

	
	public String ptsToString(){
		String res="";
		if(pts.length>0)
			res+="["+ pts[0].toString();
		for(int i=0; i<pts.length; i++){
			res+=", "+ pts[i].toString();
		}
		res+="]";
		return res;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int zoom = e.getWheelRotation();

		if(zoom < 0){	
			this.zoom=this.zoom*2;		
		}
		else{
			this.zoom=this.zoom/2+1;
		}	
		this.repaint();		
	}

	public void triFaces(){
		f=new ArrayList<Face>();
		for(int i=0;i<fces.length;i++){
			f.add(fces[i]);
		}
		Collections.sort(f);
	}
	
	public void setPtsToMatrix(){
		Matrix = new Matrice(4,pts.length);
		for(int i=0; i<pts.length; i++){
			Matrix.setElem(0,i,pts[i].getX());
			Matrix.setElem(1,i,pts[i].getY());
			Matrix.setElem(2,i,pts[i].getZ());
			Matrix.setElem(3,i,1.0);
		}
	}

	public void setMatrixToPts(){
		for(int i=0; i<Matrix.getnColonnes(); i++){
			pts[i]= new Point(Matrix.getElem(0,i),Matrix.getElem(1,i),Matrix.getElem(2,i));
		}
	}

	public void setTranslation() throws VectorException, MatriceNotCorrespondingException, SegmentException{
		setPtsToMatrix();
		double x=0.0;
		double y=0.0;
		double z=0.0;
		Matrice Vecteur;
		for(int i=0; i<fces.length; i++){
			x+=fces[i].barycentre().getX();
			y+=fces[i].barycentre().getY();
			z+=fces[i].barycentre().getZ();
		}
		Vecteur = new Matrice(new double[][] {{x/fces.length},{y/fces.length},{z/fces.length}});
		Matrix = Matrice.multiplier(Matrice.getTranslation(Vecteur), Matrix);
		setMatrixToPts();
		setSegments();
		setFaces();
		triFaces();
	}

	public void setRotationX(double r) throws MatriceNotCorrespondingException, SegmentException {
		setPtsToMatrix();
		Matrix = Matrice.multiplier(Matrice.getRotationX(r), Matrix);
		setMatrixToPts();
		setSegments();
		setFaces();
		triFaces();
	}

	public void setRotationY(double r) throws MatriceNotCorrespondingException, SegmentException {
		setPtsToMatrix();
		Matrix = Matrice.multiplier(Matrice.getRotationY(r), Matrix);
		setMatrixToPts();
		setSegments();
		setFaces();
		triFaces();
	}

	public void setRotationZ(double r) throws MatriceNotCorrespondingException, SegmentException {
		setPtsToMatrix();
		Matrix = Matrice.multiplier(Matrice.getRotationZ(r), Matrix);
		setMatrixToPts();
		setSegments();
		setFaces();
		triFaces();
	}

	public void setSegments() throws SegmentException{
		sgmts = new Segment[infos[1]];
		for(int i=0; i < infos[1]; i++){
			sgmts[i]=new Segment(pts[numsgmts[i][0]-1], pts[numsgmts[i][1]-1]);
		}
	}

	public void setFaces() throws SegmentException{
		fces = new Face[infos[2]];
		for(int i=0; i < infos[2]; i++){
			fces[i] = new Face(sgmts[numfces[i][0] - 1], sgmts[numfces[i][1] - 1], sgmts[numfces[i][2] - 1]);
		}
	}
}