package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.Menu;

public class MyButtonSegmentsListener implements ActionListener {
	FModelisation fm;
	Menu m;
	public MyButtonSegmentsListener(FModelisation f, Menu menu){
	this.fm=f;
	this.m = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.fm.setOpt(2);
		this.fm.repaint();
		this.m.point.setEnabled(true);
		this.m.segment.setEnabled(false);
		this.m.face.setEnabled(true);
		
	}

}
