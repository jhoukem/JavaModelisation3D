package affichage;

import java.awt.Graphics;

import javax.swing.JPanel;

import exceptions.SegmentException;

public class Fenetre extends JPanel {

	private static final long serialVersionUID = 1L;
	int xSize, ySize;
	GtsReader gts;
	Segment[] sgmt;

	public Fenetre() throws SegmentException {
		gts = new GtsReader("head.gts");
		sgmt = gts.getSegments();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		xSize = this.getWidth();
		ySize = this.getHeight();
		// x
		//g.drawLine(0, ySize / 2, xSize, ySize / 2);
		// y
		//g.drawLine(xSize / 2, 0, xSize / 2, ySize);

		for (int i = 0; i < sgmt.length; i++) {
			g.drawLine((int) (sgmt[i].getDébut().getX() * 20) + xSize / 2,
					(int) (sgmt[i].getDébut().getY() * 20) + ySize / 2,
					(int) (sgmt[i].getFin().getX() * 20) + xSize / 2,
					(int) (sgmt[i].getFin().getY() * 20) + ySize / 2);
		}
	}
}
