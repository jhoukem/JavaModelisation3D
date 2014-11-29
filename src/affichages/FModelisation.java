package affichages;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import exceptions.SegmentException;

public class FModelisation extends JPanel {

	private static final long serialVersionUID = 1L;
	int xSize, ySize;
	GtsReader gts;
	ArrayList<Face> f ;
	int[] x;
	int[] y;
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
			x= new int[3];
			y= new int[3];
			for(int j =0;j<3;j++){
				x[j]= (int)(f.get(i).xpoints[j]*10+xSize/2);
				y[j]= (int)(f.get(i).ypoints[j]*10+ySize/2);
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
}
