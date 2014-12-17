package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.GtsReader;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;

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
			this.fM.setZoom(10);
			this.fM.setInfos(fM.getGts().getInfos());
			this.fM.setNumsgmts(fM.getGts().getNumsgmts());
			this.fM.setNumfces(fM.getGts().getNumfces());
			this.fM.setPts(fM.getGts().getPoints());
			try {
				this.fM.setFces();
			} catch (SegmentException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				fM.setTranslation(fM.getVectorCenter());
			} catch (VectorException | MatriceNotCorrespondingException
					| SegmentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
			this.fM.setxSize(fM.getWidth());	
			this.fM.setySize(fM.getHeight());	
			this.fM.repaint();
	
		
	}
}
