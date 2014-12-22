package listener_package;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import affichages.FModelisation;
import affichages.GtsReader;
import affichages.JTabbedPaneWithCloseIcons;

public class MyButtonRotListener implements ActionListener {

	
	protected JButton rot;
	protected JButton trans;
	JTabbedPaneWithCloseIcons jt;
	
	public MyButtonRotListener(JButton trans,JButton rot,JTabbedPaneWithCloseIcons j) {
		this.jt= j;
		this.trans=trans;
		this.rot=rot;
	}
	@Override
	public void actionPerformed(ActionEvent e) {	
		
		((FModelisation)jt.getSelectedComponent()).setRot(true);	
			rot.setEnabled(false);
			trans.setEnabled(true);		
		
	}
	
	
}
