package affichages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import maths_package.Matrice;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;

/**
 * @author Jean-Hugo Oukem & Benoit Barbe
 * 
 */
public class GtsReader {
	private int[] infos;
	private Point[] pts;
	private Segment[] sgmts;
	private int[][] numsgmts;
	private Face[] fces;
	private int[][] numfces;
	private Matrice Matrix;
	private FileReader flux;
	private BufferedReader entry;
	private String path = "./gts_files/";

	/**
	 * 
	 * 
	 * @param filename
	 *            The name of the .gts file, contained in the folder "gts_files"
	 * @throws SegmentException 
	 */
	public GtsReader(String filename) throws SegmentException {
		infos = new int[3];
		// TODO exceptions a traiter si fichier non trouve
		path += filename;
		try {
			flux = new FileReader(path);
			entry = new BufferedReader(flux);
			initInfos();
			extractPoints();
			extractSegments();
			setSegments();
			extractFaces();
			setFaces();
			setPtsToMatrix();
			Matrix = setTranslation();
			setMatrixToPts();
			setSegments();
			setFaces();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void extractPoints() throws IOException {
		this.pts = new Point[infos[0]];
		String line = entry.readLine();
		double x, y, z;

		for (int i = 0; i < infos[0]; i++) {
			x = Double.parseDouble(line.substring(0, line.indexOf(" ")));
			y = Double.parseDouble(line.substring(line.indexOf(" ") + 1,line.lastIndexOf(" ")));
			z = Double.parseDouble(line.substring(line.lastIndexOf(" ") + 1));
			this.pts[i] = new Point(x, y, z);
			if(i<infos[0]-1)
				line=entry.readLine();
		}
	}

	public void extractSegments() throws IOException, SegmentException {
		
		numsgmts = new int[infos[1]][2];
		String line = entry.readLine();
		int pt1, pt2;

		for (int i = 0; i < infos[1]; i++) {
			pt1 = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			pt2 = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
			numsgmts[i][0]=pt1;
			numsgmts[i][1]=pt2;
			// pt1-1 et pt2-1 car le fichier considere le 1er idx a 1, soit idx = 0 en java
			if(i<infos[1]-1)
				line=entry.readLine();	
		}
	}
	
	public void setSegments() throws SegmentException{
		sgmts = new Segment[infos[1]];
		for(int i=0; i < infos[1]; i++){
			sgmts[i]=new Segment(pts[numsgmts[i][0]-1], pts[numsgmts[i][1]-1]);
		}
	}
	

	
	public void extractFaces() throws IOException, SegmentException {
		numfces = new int[infos[2]][3];
		String line = entry.readLine();
		int seg1, seg2, seg3;
		for (int i = 0; i < infos[2]; i++) {
			seg1 = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			seg2 = Integer.parseInt(line.substring(line.indexOf(" ") + 1,line.lastIndexOf(" ")));
			seg3 = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
			// idem que pour segments
			numfces[i][0]=seg1;
			numfces[i][1]=seg2;
			numfces[i][2]=seg3;
			line = entry.readLine();
		}
	}
	
	public void setFaces() throws SegmentException{
		fces = new Face[infos[2]];
		for(int i=0; i < infos[2]; i++){
			fces[i] = new Face(sgmts[numfces[i][0] - 1], sgmts[numfces[i][1] - 1], sgmts[numfces[i][2] - 1]);
		}
	}

	/**
	 * Initialise les infos qui contiennent dans l'ordre, le nombre de :
	 * points-segments-faces
	 * 
	 * @param firstLine
	 * @return
	 * @throws IOException
	 */
	public void initInfos() throws IOException {
		String line = entry.readLine() + " ";
		String n = "";
		for (int i = 0; i < infos.length; i++) {
			n = line.substring(0, line.indexOf(" ", 0));
			line = line.substring(line.indexOf(" ") + 1, line.length());
			infos[i] = Integer.parseInt(n);
		}
	}

	public Point[] getPoints() {
		return pts;
	}

	public Segment[] getSegments() {
		return sgmts;
	}

	public Face[] getFaces() {
		return fces;
	}
	
	public void setPts(Point[] pts) {
		this.pts = pts;
	}

	public void setSgmts(Segment[] sgmts) {
		this.sgmts = sgmts;
	}

	public void setFces(Face[] fces) {
		this.fces = fces;
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

	public Matrice getVecteurTranslation(){
		double x=0.0;
		double y=0.0;
		double z=0.0;
		for(int i=0; i<fces.length; i++){
			x+=fces[i].barycentre().getX();
			y+=fces[i].barycentre().getY();
			z+=fces[i].barycentre().getZ();
		}
		return new Matrice(new double[][] {{x/fces.length},{y/fces.length},{z/fces.length}});
	}
	
	public Matrice setTranslation() throws VectorException, MatriceNotCorrespondingException{
		return Matrice.multiplier(Matrice.getTranslation(getVecteurTranslation()), Matrix);
	}
	
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < pts.length; i++) {
			str += pts[i];
			str += "\n";
		}
		for (int i = 0; i < sgmts.length; i++) {
			str += sgmts[i] + "\n";
		}
		for (int i = 0; i < fces.length; i++) {
			str += fces[i] + "\n";
		}
		return str;
	}
	
}