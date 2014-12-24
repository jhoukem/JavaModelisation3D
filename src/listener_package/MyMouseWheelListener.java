package listener_package;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import affichages.FModelisation;

public class MyMouseWheelListener implements MouseWheelListener{

protected FModelisation fM;

public MyMouseWheelListener(FModelisation f) {
	this.fM= f;
}

	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int zoom = e.getWheelRotation();
        //si la roulette avance, on zoom
		if(zoom < 0){	
			if(fM.getZoom()<20000000)
			fM.setZoom(fM.getZoom()*1.5);	
			
		}
		
		else if (zoom > 0){
			//sinon on dezoom
			fM.setZoom(fM.getZoom()*0.5);
			
		}	
		fM.repaint();		
}
	
	
	
}
