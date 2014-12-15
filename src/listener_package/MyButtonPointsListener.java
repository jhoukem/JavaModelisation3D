package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.FModelisation;
import affichages.Menu;

public class MyButtonPointsListener implements ActionListener{
	FModelisation fm;
	Menu m;
	public MyButtonPointsListener(FModelisation f, Menu menu){
	this.fm=f;
	this.m=menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.fm.setOpt(3);
		this.fm.repaint();
		this.m.point.setEnabled(false);
		this.m.segment.setEnabled(true);
		this.m.face.setEnabled(true);
	}


}
