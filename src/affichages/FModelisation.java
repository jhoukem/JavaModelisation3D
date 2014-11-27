package affichages;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.SegmentException;

public class FModelisation extends JPanel {

	private static final long serialVersionUID = 1L;
	int xSize, ySize;
	GtsReader gts;
	ArrayList<Face> f ;
	Face[] face;
	int moi;

	public FModelisation() throws SegmentException {
		gts = new GtsReader("tie.gts");
		face = gts.getFaces();
		f=new ArrayList<Face>();
		for(int i=0;i<face.length;i++){
			f.add(face[i]);
		}
		Collections.sort(f);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);	
		xSize = this.getWidth();
		ySize = this.getHeight();	


		for(int i=0;i<f.size();i++){			
			g.fillPolygon(f.get(i));			
		}
	}
}
