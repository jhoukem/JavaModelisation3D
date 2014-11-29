package affichages;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import maths_package.Matrice;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;

public class FModelisation extends JPanel {

	private static final long serialVersionUID = 1L;
	int xSize, ySize;
	GtsReader gts;
	ArrayList<Face> f ;
	Face[] fces;
	Point[] pts;
	Segment[] sgmts;

	public FModelisation() throws SegmentException {
		try {
		gts = new GtsReader("tie.gts");
		pts = gts.getPoints();
		sgmts = gts.getSegments();
		fces = gts.getFaces();
		
		
		f=new ArrayList<Face>();
		for(int i=0;i<fces.length;i++){
			f.add(fces[i]);
		}
		Collections.sort(f);
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
			for(int j =0;j<3;j++){
				g.drawPolygon((int)(f.get(i).xpoints*100+xSize), (int)(f.get(i).ypoints*100+ySize), 3);	
			}
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

	
	
	

}
