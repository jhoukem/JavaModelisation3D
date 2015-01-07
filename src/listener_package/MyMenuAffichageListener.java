package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;

import affichages.Descripteur;
import affichages.Librairie;

public class MyMenuAffichageListener implements ActionListener {

	private Librairie lib;
	private JSplitPane jp;
	Descripteur des;
	
	public  MyMenuAffichageListener(Librairie l, JSplitPane s, Descripteur d){
		this.lib = l;
		this.jp = s;
		this.des = d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Afficher/Masquer la librairie     Ctrl+L")){
			if(lib.isVisible())
				lib.setVisible(false);	
			else{
				lib.setVisible(true);
				lib.revalidate();
				jp.resetToPreferredSizes();
			}
		}
		else{
			if(des.isVisible())
				des.setVisible(false);
			else
				des.setVisible(true);
		}
	}
}
