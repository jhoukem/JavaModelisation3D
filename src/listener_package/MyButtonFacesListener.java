package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyButtonFacesListener implements ActionListener {
	
	JTabbedPaneWithCloseIcons jt;
	Outils m;
	public MyButtonFacesListener(JTabbedPaneWithCloseIcons j, Outils menu){
	this.jt = j;
	
	this.m = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((FModelisation)jt.getSelectedComponent()).setOpt(1);
		((FModelisation)jt.getSelectedComponent()).repaint();
		this.m.point.setEnabled(true);
		this.m.segment.setEnabled(true);
		this.m.face.setEnabled(false);
		
	}




}
