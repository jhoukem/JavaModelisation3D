package affichages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

	private boolean aliasing = false;
	private boolean initialisation = true;
	private static final int AFFICHE_SEGMENTS = 2;
	public static final int AFFICHE_FACES = 1;
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
	private double zoom=0;
	private boolean isRot = true;
	private int k=0;
	private boolean isIn=true;
	private int opt =1;
	private Point centre;
	private double minX, minY, maxX, maxY;
	private String path;
	public String getPath() {
		return path;
	}

	//renvoie un tab de int avec respectivement nbpts nbsgmts et nbfcs
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

	public double getZoom() {
		return zoom;
	}

	public void setZoom(double zoom) {
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



	public FModelisation(String fichier, boolean b) throws SegmentException {
		try {
			this.path=fichier;
			this.setBackground(new Color(142,162,198));
			if(b){//permet de savoir si c'est un visualisation avant l'import ou apres
			this.addMouseMotionListener(new MyMouseMotionListener(this));
			this.addMouseWheelListener(new MyMouseWheelListener(this));
			this.addMouseListener(new MyMouseListener(this));
			}
			setFigure(fichier,b);

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public FModelisation() {

	}

	
	public void setFigure(String fichier, boolean b) throws SegmentException,VectorException, MatriceNotCorrespondingException {
		this.setFichier(fichier);
		setGts(new GtsReader(fichier,b));
		setInfos(getGts().getInfos());
		setNumsgmts(getGts().getNumsgmts());
		setNumfces(getGts().getNumfces());
		setPts(getGts().getPoints());
		setPtsToMatrix();
		setFces();
	}

	//fonction dessinant les faces de la figure
	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;
		if(aliasing){
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			}else{
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
			g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
			}
		if(k==0){
			xSize = this.getWidth();
			ySize = this.getHeight();
			k++;
		}
		if(initialisation){
			initZoom();
			initialisation = false;
		}
	//	System.out.println("x:" +xSize+" y :" +ySize);
		if(opt==AFFICHE_FACES) {
			try {
				setFces();
			} catch (SegmentException e) {
				e.printStackTrace();
			}
			Collections.sort(f);
			//System.out.println("Faces triées");
			for(int i=0;i<f.size();i++){
				int[] x= new int[3];
				int [] y= new int[3];
				for(int j =0;j<3;j++){
					x[j]= (int)(f.get(i).xpoints[j]*zoom+xSize/2);
					y[j]= (int)(f.get(i).ypoints[j]*zoom+ySize/2);
				}
				g.setColor(f.get(i).getCouleur());
				
				g.fillPolygon(x, y, x.length);
			}	
		}
		else if(opt == AFFICHE_SEGMENTS){
			for(int i=0;i<numsgmts.length;i++){
				int[] x= new int[2];
				int [] y= new int[2];
				for(int j =0;j<2;j++){
					x[j]= (int)(Matrix.getElem(0, numsgmts[i][j]-1)*zoom+xSize/2);
					y[j]= (int)(Matrix.getElem(1, numsgmts[i][j]-1)*zoom+ySize/2);
				}
				g.drawPolygon(x, y,x.length);
			}
		}		
		else{
			for(int i =0;i<Matrix.getnColonnes();i++){
				//g.fillOval((int)(Matrix.getElem(0, i)*zoom+xSize/2), (int) (Matrix.getElem(1, i)*zoom+ySize/2), 2, 2);
				g.drawLine((int)(Matrix.getElem(0, i)*zoom+xSize/2), (int) (Matrix.getElem(1, i)*zoom+ySize/2), (int)(Matrix.getElem(0, i)*zoom+xSize/2), (int) (Matrix.getElem(1, i)*zoom+ySize/2));
			}
		}
	//	System.out.println("Affiché");
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
		centre = getCenter();
		for(int i=0; i<pts.length; i++){
			for(int j=0; j<3; j++){
				Matrix.setElem(0,i,pts[i].getX()-centre.getX());
				Matrix.setElem(1,i,pts[i].getY()-centre.getY());
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

	//fonction permettant d'obtenir le centre de la figure
	public Point getCenter(){
		minX = pts[0].getX();
		minY = pts[0].getY();
	    maxX = pts[0].getX();
		maxY = pts[0].getY();
		for(Point p : pts){
			if(p.getX() < minX){
				minX = p.getX();
			}
			if(p.getY() < minY){
				minY = p.getY();
			}
			if(p.getX() > maxX){
				maxX = p.getX();
			}
			if(p.getY() > maxY){
				maxY = p.getY();
			}
		}
	//	System.out.println("minx : " + minX + " minY : " + maxX + " maxX : "+ maxX+ " maxY : "+ maxY);
		return new Point((minX+maxX)/2,(minY+maxY)/2,0);
	}

	public void initZoom(){
		while(((maxX-minX)*zoom+xSize/2) > 0.9*xSize && ((maxY-minY)*zoom+ySize/2) >0.9*ySize){
			zoom--;
		}
		while(((maxX-minX)*zoom+xSize/2) < 0.9*xSize && ((maxY-minY)*zoom+ySize/2) <0.9*ySize){
		//	System.out.println("minx : " + minX + " minY : " + maxX + " maxX : "+ maxX+ " maxY : "+ maxY);
			//System.out.println("zoom : "+zoom);
			zoom++;
		}
	}
	
	//fonction permettant d'obtenir une rotation d'angle r autour de l'axe des X
	public void setRotationX(double r) throws MatriceNotCorrespondingException, SegmentException {
		Matrix = Matrice.multiplier(Matrice.getRotationX(r), Matrix);
		//System.out.println("Calculée");
	}

	//fonction permettant d'obtenir une rotation d'angle r autour de l'axe des Y
	public void setRotationY(double r) throws MatriceNotCorrespondingException, SegmentException {
		Matrix = Matrice.multiplier(Matrice.getRotationY(r), Matrix);
	//	System.out.println("Calculée");
	}

	public List<Face> getFces() {
		return f;
	}

	public void setFces() throws SegmentException {
		f = new ArrayList<Face>();
		for(int i=0; i< getInfos()[2]; i++){
			Segment s1 = new Segment(new Point(Matrix.getElem(0, numsgmts[numfces[i][0]-1][0]-1),Matrix.getElem(1, numsgmts[numfces[i][0]-1][0]-1),Matrix.getElem(2, numsgmts[numfces[i][0]-1][0]-1)),new Point(Matrix.getElem(0, numsgmts[numfces[i][0]-1][1]-1),Matrix.getElem(1, numsgmts[numfces[i][0]-1][1]-1),Matrix.getElem(2, numsgmts[numfces[i][0]-1][1]-1)));
			Segment s2 = new Segment(new Point(Matrix.getElem(0, numsgmts[numfces[i][1]-1][0]-1),Matrix.getElem(1, numsgmts[numfces[i][1]-1][0]-1),Matrix.getElem(2, numsgmts[numfces[i][1]-1][0]-1)),new Point(Matrix.getElem(0, numsgmts[numfces[i][1]-1][1]-1),Matrix.getElem(1, numsgmts[numfces[i][1]-1][1]-1),Matrix.getElem(2, numsgmts[numfces[i][1]-1][1]-1)));
			Segment s3 = new Segment(new Point(Matrix.getElem(0, numsgmts[numfces[i][2]-1][0]-1),Matrix.getElem(1, numsgmts[numfces[i][2]-1][0]-1),Matrix.getElem(2, numsgmts[numfces[i][2]-1][0]-1)),new Point(Matrix.getElem(0, numsgmts[numfces[i][2]-1][1]-1),Matrix.getElem(1, numsgmts[numfces[i][2]-1][1]-1),Matrix.getElem(2, numsgmts[numfces[i][2]-1][1]-1)));
			f.add(new Face(s1,s2,s3));
		}
		//	System.out.println("Faces rangées");
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
	
	public boolean needPerf(){
		if(this.getFces().size() > 30000)
			return true;
		return false;
	}
}
