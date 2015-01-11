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
		if(e.getActionCommand().equals("Aliasing: OFF")){
			outils.alia.setText("Aliasing: ON");
			((FModelisation)jt.getSelectedComponent()).setAliasing(false);
		}
		else{
			outils.alia.setText("Aliasing: OFF");
			((FModelisation)jt.getSelectedComponent()).setAliasing(true);
		}
		((FModelisation)jt.getSelectedComponent()).repaint();
			
		
	}

}
