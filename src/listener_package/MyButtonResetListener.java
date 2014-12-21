package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.GtsReader;
import affichages.JTabbedPaneWithCloseIcons;
import exceptions.SegmentException;

public class MyButtonResetListener implements ActionListener{
	FModelisation fM;
	JTabbedPaneWithCloseIcons jt;
	public MyButtonResetListener(JTabbedPaneWithCloseIcons j){
		this.fM = ((FModelisation)j.getSelectedComponent());
		this.jt = j;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
			try {
				this.fM = ((FModelisation)jt.getSelectedComponent());
				this.fM.setGts(new GtsReader(this.fM.getFichier()));
			} catch (SegmentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			this.fM.setZoom(0);
			this.fM.setInitialisation(true);
			this.fM.setPts(fM.getGts().getPoints());
			this.fM.setPtsToMatrix();
			try {
				this.fM.setFces();
			} catch (SegmentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
		
			this.fM.setxSize(fM.getWidth());	
			this.fM.setySize(fM.getHeight());	
			this.fM.repaint();
	
		
	}
}
