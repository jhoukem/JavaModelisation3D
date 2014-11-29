package affichages;

import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;


import exceptions.SegmentException;

public class FModelisation extends JPanel implements MouseWheelListener {

	private static final long serialVersionUID = 1L;
	int xSize, ySize;
	GtsReader gts;
	ArrayList<Face> f ;
	int[] x;
	int[] y;
	Face[] fces;
	Point[] pts;
	Segment[] sgmts;
	public static int zoom=10;
	public FModelisation() throws SegmentException {
		try {
			
		this.addMouseWheelListener(this);
		gts = new GtsReader("goblet.gts");
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
		int zoom= e.getWheelRotation();
		System.out.println("entree");
		if(zoom<0){
			if(this.zoom+10 != 0)
				this.zoom+=10;	
			else
				this.zoom+=8;	
		}
		else{
			if(this.zoom-10 != 0)
				this.zoom+=-10;
			else
				this.zoom+=-8;	
		}
			
		
		this.repaint();
			
	}
}