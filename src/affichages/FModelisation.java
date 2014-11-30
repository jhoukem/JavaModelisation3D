package affichages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import maths_package.Matrice;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;

public class FModelisation extends JPanel implements MouseWheelListener,MouseListener,MouseMotionListener{

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
	private int lastXPos;
	private int lastYPos;

	public FModelisation(String fichier) throws SegmentException {
		try {
			this.addMouseMotionListener(this);
			this.addMouseWheelListener(this);
			this.addMouseListener(this);
			gts = new GtsReader(fichier);
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
		//	g.setColor(new Color(0,0,0));
			//g.drawPolygon(x, y, x.length);
			g.setColor(f.get(i).getCouleur());
			g.fillPolygon(x, y, x.length);
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
//si la roulette avance, on zoom
		if(zoom < 0){	
			this.zoom=this.zoom*2-(this.zoom/2);		
		}
		else{//sinon on dezoom
			this.zoom=this.zoom/2+1;
		}	
		this.repaint();		
	}


	@Override
	public void mouseDragged(MouseEvent e){
		try {
			if(e.getX()<lastXPos){
				setRotationY(0.09);				
			}
			else if(e.getX()>lastXPos){
				setRotationY(-0.09);			
			}
			if(e.getY()<lastYPos){
				setRotationX(-0.09);
			}
			else if(e.getY()>lastYPos){
				setRotationX(0.09);
			}
			lastXPos=e.getX();
			lastYPos=e.getY();

			repaint();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	@Override
	public void mouseMoved(MouseEvent e) {
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed(MouseEvent e) {
		lastXPos=e.getX();
		lastYPos=e.getY();
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		//	clicked=false;
	}
	public void triFaces(){
		f=new ArrayList<Face>();
		for(int i=0;i<fces.length;i++){
			f.add(fces[i]);
		}
		
		Collections.sort(f);
	/*Sert a voir le tri	for(int i=0;i<f.size();i++){
			System.out.println(f.get(i).barycentre().getZ());
		}*/
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
		double x = getFigureCenter().getX();
		double y = getFigureCenter().getY();
		double z = getFigureCenter().getZ();
		Matrice Vecteur = new Matrice(new double[][] {{x},{y},{z}});
		Matrix = Matrice.multiplier(Matrice.getTranslation(Vecteur), Matrix);
		setMatrixToPts();
		setSegments();
		setFaces();
		triFaces();
	}
	
	public Point getFigureCenter(){
		double x = 0;
		double y = 0;
		double z = 0;
		for(int i=0; i<fces.length; i++){
			x+=fces[i].barycentre().getX();
			y+=fces[i].barycentre().getY();
			z+=fces[i].barycentre().getZ();
		}
		return new Point(x/fces.length,y/fces.length,z/fces.length);
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