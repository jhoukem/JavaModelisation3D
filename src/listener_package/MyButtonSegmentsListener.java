package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyButtonSegmentsListener implements ActionListener {
	JTabbedPaneWithCloseIcons jt ;
	Outils m;
	public MyButtonSegmentsListener(JTabbedPaneWithCloseIcons jt , Outils menu){
	this.jt = jt;
	this.m = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((FModelisation)jt.getSelectedComponent()).setOpt(2);
		((FModelisation)jt.getSelectedComponent()).repaint();
		this.m.point.setEnabled(true);
		this.m.segment.setEnabled(false);
		this.m.face.setEnabled(true);
		
	}

}
