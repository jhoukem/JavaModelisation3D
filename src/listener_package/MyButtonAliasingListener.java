package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyButtonAliasingListener implements ActionListener {

	JTabbedPaneWithCloseIcons jt;
	Outils outils;
	
	public MyButtonAliasingListener(JTabbedPaneWithCloseIcons j, Outils o) {
		this.outils = o;
		this.jt = j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Performance")){
			outils.alia.setText("Qualité");
			((FModelisation)jt.getSelectedComponent()).setAliasing(true);
		}
		else{
			outils.alia.setText("Performance");
			((FModelisation)jt.getSelectedComponent()).setAliasing(false);
		}
		((FModelisation)jt.getSelectedComponent()).repaint();
			
		
	}

}
