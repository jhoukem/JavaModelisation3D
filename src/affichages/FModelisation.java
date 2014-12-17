package affichages;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import listener_package.MyMouseListener;
import listener_package.MyMouseMotionListener;
import listener_package.MyMouseWheelListener;
import maths_package.Matrice;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;

public class FModelisation extends JPanel {

	private static final long serialVersionUID = 1L;
	private String fichier;
	private int xSize, ySize;
	private GtsReader gts;
	private ArrayList<Face> f;
	private int[] infos;
	private int[][] numsgmts;
	private int[][] numfces;
	private Point[] pts;
	private Matrice Matrix;
	private int zoom=10;
	private boolean isRot = true;
	private int k=0;
	private boolean isIn=true;
	private int opt =1;
	
	
	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}

	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}
	
	public boolean isRot() {
		return isRot;
	}

	public void setRot(boolean isRot) {
		this.isRot = isRot;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	private int lastXPos;
	
	public int getLastXPos() {
		return lastXPos;
	}

	public void setLastXPos(int lastXPos) {
		this.lastXPos = lastXPos;
	}

	private int lastYPos;
	public int getLastYPos() {
		return lastYPos;
	}

	public void setLastYPos(int lastYPos) {
		this.lastYPos = lastYPos;
	}

	
	
	public FModelisation(String fichier) throws SegmentException {
		try {
			this.setBackground(new Color(142,162,198));
			this.addMouseMotionListener(new MyMouseMotionListener(this));
			this.addMouseWheelListener(new MyMouseWheelListener(this));
			this.addMouseListener(new MyMouseListener(this));
			setFigure(fichier);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public FModelisation() {
	
	}

	public void setFigure(String fichier) throws SegmentException,VectorException, MatriceNotCorrespondingException {
		this.setFichier(fichier);
		setGts(new GtsReader(fichier));
		setInfos(getGts().getInfos());
		setNumsgmts(getGts().getNumsgmts());
		setNumfces(getGts().getNumfces());
		setPts(getGts().getPoints());
		setFces();
		setTranslation(getVectorCenter());
	}

	//fonction dessinant les faces de la figure
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		if(k==0){
		xSize = this.getWidth();
		ySize = this.getHeight();	
		k++;
		}
		Collections.sort(f);
		for(int i=0;i<f.size();i++){
			int[] x= new int[3];
			int [] y= new int[3];
			for(int j =0;j<3;j++){
				x[j]= (int)(f.get(i).xpoints[j]*zoom+xSize/2);
				y[j]= (int)(f.get(i).ypoints[j]*zoom+ySize/2);
			}
			g.setColor(f.get(i).getCouleur());
			if(opt==1)
				g.fillPolygon(x, y, x.length);
			else if(opt == 2)
				g.drawPolygon(x, y,x.length);
			else{
				for(int j =0;j<3;j++){
					g.fillOval(x[j], y[j], 2, 2);
				}
			}
		}
	}


	public String ptsToString(){
		String res="";
		if(getPts().length>0)
			res+="["+ getPts()[0].toString();
		for(int i=0; i<getPts().length; i++){
			res+=", "+ getPts()[i].toString();
		}
		res+="]";
		return res;
	}

	//fonction permettant de cr�er la matrice homog�ne des points de la figure
	public void setPtsToMatrix(){
		Matrix = new Matrice(4,pts.length);
		for(int i=0; i<pts.length; i++){
			for(int j=0; j<3; j++){
			Matrix.setElem(0,i,pts[i].getX());
			Matrix.setElem(1,i,pts[i].getY());
			Matrix.setElem(2,i,pts[i].getZ());
			Matrix.setElem(3,i,1.0);
			}
		}
	}
	
	//fonction permettant de r�cup�rer les points dans la matrice
	public void setMatrixToPts(){
		for(int i=0; i<Matrix.getnColonnes(); i++){
			pts[i]= new Point(Matrix.getElem(0, i), Matrix.getElem(1, i), Matrix.getElem(2, i));
		}
	}

	//fonction permettant de translater la figure par rapport a un vecteur
	public void setTranslation(Matrice vector) throws VectorException, MatriceNotCorrespondingException, SegmentException{
		setPtsToMatrix();
		Matrix = Matrice.multiplier(Matrice.getTranslation(vector), Matrix);
		setMatrixToPts();
		setFces();
	}
	
	//fonction permettant d'obtenir le barycentre de la figure
	public Matrice getVectorCenter(){
		double x = 0;
		double y = 0;
		double z = 0;
		for(int i=0; i<getFces().size(); i++){
			x+=f.get(i).barycentre().getX();
			y+=f.get(i).barycentre().getY();
			z+=f.get(i).barycentre().getZ();
		}
		return new Matrice(new double[][]{{x/getFces().size()},{y/getFces().size()},{z/getFces().size()}});
	}

	//fonction permettant d'obtenir une rotation d'angle r autour de l'axe des X
	public void setRotationX(double r) throws MatriceNotCorrespondingException, SegmentException {
		setPtsToMatrix();
		Matrix = Matrice.multiplier(Matrice.getRotationX(r), Matrix);
		setMatrixToPts();
		setFces();
	}

	//fonction permettant d'obtenir une rotation d'angle r autour de l'axe des Y
	public void setRotationY(double r) throws MatriceNotCorrespondingException, SegmentException {
		setPtsToMatrix();
		Matrix = Matrice.multiplier(Matrice.getRotationY(r), Matrix);
		setMatrixToPts();
		setFces();
	}

	public List<Face> getFces() {
		return f;
	}

	public void setFces() throws SegmentException {
		f = new ArrayList<Face>();
		for(int i=0; i< getInfos()[2]; i++)
			
			f.add(new Face(new Segment(pts[numsgmts[numfces[i][0]-1][0]-1],pts[numsgmts[numfces[i][0]-1][1]-1]),new Segment(pts[numsgmts[numfces[i][1]-1][0]-1],pts[numsgmts[numfces[i][1]-1][1]-1]),new Segment(pts[numsgmts[numfces[i][2]-1][0]-1],pts[numsgmts[numfces[i][2]-1][1]-1])));
	}

	public GtsReader getGts() {
		return gts;
	}

	public void setGts(GtsReader gts) {
		this.gts = gts;
	}

	public int[] getInfos() {
		return infos;
	}

	public void setInfos(int[] infos) {
		this.infos = infos;
	}

	public int[][] getNumsgmts() {
		return numsgmts;
	}

	public void setNumsgmts(int[][] numsgmts) {
		this.numsgmts = numsgmts;
	}

	public int[][] getNumfces() {
		return numfces;
	}

	public void setNumfces(int[][] numfces) {
		this.numfces = numfces;
	}

	public Point[] getPts() {
		return pts;
	}

	public void setPts(Point[] pts) {
		this.pts = pts;
	}

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	

}
