package listener_package;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import affichages.FModelisation;

public class MyButtonRotListener implements ActionListener {

	protected FModelisation fM;
	protected JButton rot;
	protected JButton trans;
	
	public MyButtonRotListener(JButton trans,JButton rot,FModelisation f) {
		this.fM= f;
		this.trans=trans;
		this.rot=rot;
	}
	@Override
	public void actionPerformed(ActionEvent e) {			
			this.fM.setRot(true);		
			rot.setEnabled(false);
			trans.setEnabled(true);
	}
	
	
}
