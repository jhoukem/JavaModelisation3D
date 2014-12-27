package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;

public class MyButtonListener implements ActionListener {

	protected FModelisation fM;

	public MyButtonListener(FModelisation f) {
		this.fM= f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(fM.isRot())
			this.fM.setRot(false);
		else
			this.fM.setRot(true);
	
			
		
	}
	
	
}
