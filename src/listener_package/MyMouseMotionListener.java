package listener_package;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import affichages.FModelisation;

public class MyMouseMotionListener implements MouseMotionListener{
	protected FModelisation fM;

	public MyMouseMotionListener(FModelisation f) {
		this.fM= f;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(fM.isRot()){
			try {
				if(e.getX()<fM.getLastXPos()){
					fM.setRotationY(0.05);
				}
				else if(e.getX()>fM.getLastXPos()){
					fM.setRotationY(-0.05);
				}
				if(e.getY()<fM.getLastYPos()){
					fM.setRotationX(-0.05);
				}
				else if(e.getY()>fM.getLastYPos()){
					fM.setRotationX(0.05);
				}
				fM.setLastXPos(e.getX());
				fM.setLastYPos(e.getY());


				fM.repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
