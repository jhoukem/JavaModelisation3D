package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.Outils;

public class MyButtonSegmentsListener implements ActionListener {
	FModelisation fm;
	Outils m;
	public MyButtonSegmentsListener(FModelisation f, Outils menu){
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
