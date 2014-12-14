
package listener_package;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


import affichages.FModelisation;
import affichages.Menu;

public class MyButtonTransListener implements ActionListener {

	protected FModelisation fM;
	protected JButton rot;
	protected JButton trans;
	
	
	public MyButtonTransListener(JButton trans,JButton rot,FModelisation f) {
		this.fM= f;
		this.trans=trans;
		this.rot=rot;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			this.fM.setRot(false);		
			rot.setEnabled(true);
			trans.setEnabled(false);
	}
	
	
}

