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
		
		if(fM.isIn()){//si on est dans la fenetre
			if(fM.isRot()){//si on est en mode rotation
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


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			else{
				try{
					int vitesse;//Permet de mesurer la vitesse de deplacement ainsi le mvt est proporionnel a la vitesse de deplacement
					if(e.getX()<fM.getLastXPos()){
						vitesse = fM.getLastXPos()-e.getX();
						fM.setxSize(fM.getxSize()-vitesse*2);
					}
					else if(e.getX()>fM.getLastXPos()){
						vitesse = e.getX()-fM.getLastXPos();
						fM.setxSize(fM.getxSize()+vitesse*2);
					}
					if(e.getY()<fM.getLastYPos()){
						vitesse = fM.getLastYPos()-e.getY();
						fM.setySize(fM.getySize()-vitesse*2);
					}
					else if(e.getY()>fM.getLastYPos()){
						vitesse = e.getY()-fM.getLastYPos();
						fM.setySize(fM.getySize()+vitesse*2);
					}

				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			}
			fM.setLastXPos(e.getX());
			fM.setLastYPos(e.getY());
			fM.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
