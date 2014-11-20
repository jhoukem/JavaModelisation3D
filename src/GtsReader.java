package affichage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exceptions.SegmentException;

/**
 * @author Jean-Hugo Oukem & Benoit Barbe
 * 
 */
public class GtsReader {
	private int[] infos;
	private Point[] pts;
	private Segment[] sgmts;
	private Face[] fces;

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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			initInfos();
			extractPoints();
			extractSegments();
			extractFaces();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void extractPoints() throws IOException {
		this.pts = new Point[infos[0]];
		String line = entry.readLine();
		double x, y, z;

		for (int i = 0; i < infos[0]; i++) {
			x = Double.parseDouble(line.substring(0, line.indexOf(" ")));
			y = Double.parseDouble(line.substring(line.indexOf(" ") + 1,
					line.lastIndexOf(" ")));
			z = Double.parseDouble(line.substring(line.lastIndexOf(" ") + 1));
			this.pts[i] = new Point(x, y, z);
			if(i<infos[0]-1)
				line=entry.readLine();
		}
	}

	private void extractSegments() throws IOException, SegmentException {
		sgmts = new Segment[infos[1]];
		String line = entry.readLine();
		int pt1, pt2;

		for (int i = 0; i < infos[1]; i++) {
			pt1 = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			pt2 = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
			this.sgmts[i] = new Segment(pts[pt1 - 1], pts[pt2 - 1]);
			// pt1-1 et pt2-1 car le fichier considere le 1er idx a 1, soit idx = 0 en java
			if(i<infos[1]-1)
				line=entry.readLine();	
		}
	}

	private void extractFaces() throws IOException, SegmentException {
		fces = new Face[infos[2]];
		String line = entry.readLine();
		int seg1, seg2, seg3;
		for (int i = 0; i < infos[2]; i++) {
			seg1 = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			seg2 = Integer.parseInt(line.substring(line.indexOf(" ") + 1,
					line.lastIndexOf(" ")));
			seg3 = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
			// idem que pour segments
			fces[i] = new Face(sgmts[seg1 - 1], sgmts[seg2 - 1],
					sgmts[seg3 - 1]);
			line = entry.readLine();
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
	
	double getXmin(){
		double min=pts[0].getX();
		for(int i=0;i<pts.length;i++){
			if(pts[i].getX()<min)
				min=pts[i].getX();
		}
		return min;
	}
	
	double getYmin(){
		double min=pts[0].getY();
		for(int i=0;i<pts.length;i++){
			if(pts[i].getY()<min)
				min=pts[i].getY();
		}
		return min;
	}
	
	double getZmin(){
		double min=pts[0].getZ();
		for(int i=0;i<pts.length;i++){
			if(pts[i].getZ()<min)
				min=pts[i].getZ();
		}
		return min;
	}
	
	double getXmax(){
		double max=pts[0].getX();
		for(int i=0;i<pts.length;i++){
			if(pts[i].getX()>max)
				max=pts[i].getX();
		}
		return max;
	}
	
	double getYmax(){
		double max=pts[0].getY();
		for(int i=0;i<pts.length;i++){
			if(pts[i].getY()>max)
				max=pts[i].getY();
		}
		return max;
	}
	
	double getZmax(){
		double max=pts[0].getZ();
		for(int i=0;i<pts.length;i++){
			if(pts[i].getZ()>max)
				max=pts[i].getZ();
		}
		return max;
	}

	public static void main(String[] args) throws SegmentException {
		GtsReader g = new GtsReader("cube.gts");
		System.out.println(g);
	}
}
