
package listener_package;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;



import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyButtonTransListener implements ActionListener {

	protected JButton rot;
	protected JButton trans;
	JTabbedPaneWithCloseIcons jt;
	
	public MyButtonTransListener(JButton trans,JButton rot, JTabbedPaneWithCloseIcons j) {
		this.trans=trans;
		this.rot=rot;
		this.jt=j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		((FModelisation)jt.getSelectedComponent()).setRot(false);
			//this.fM.setRot(false);		
			rot.setEnabled(true);
			trans.setEnabled(false);
	}
	
	
}

