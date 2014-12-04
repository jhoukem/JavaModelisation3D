package listener_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import affichages.FModelisation;

public class MyMouseListener implements MouseListener{
	protected FModelisation fM;

	public MyMouseListener(FModelisation f) {
		this.fM= f;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		fM.setLastXPos(e.getX());
		fM.setLastYPos(e.getY());
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	
}
