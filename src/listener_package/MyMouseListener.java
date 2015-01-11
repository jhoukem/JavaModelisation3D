package listener_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import affichages.FModelisation;

public class MyMouseListener implements MouseListener{
	protected FModelisation fM;
	int lastOpt=0;
	public MyMouseListener(FModelisation f) {
		this.fM= f;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		fM.setIn(true);		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		fM.setIn(false);		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		fM.setLastXPos(e.getX());
		fM.setLastYPos(e.getY());
		lastOpt = fM.getOpt();
		if(fM.needPerf() && fM.isAliasing())
			fM.setOpt(3);
		fM.repaint();
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		fM.setOpt(lastOpt);
		fM.repaint();
	}


	
}
