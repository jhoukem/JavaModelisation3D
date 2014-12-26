package listener_package;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import affichages.Descripteur;
import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyTabListener implements ChangeListener{

	Outils m;
	JTabbedPaneWithCloseIcons jt;
	Descripteur des;
	public MyTabListener(Outils mu, JTabbedPaneWithCloseIcons j,Descripteur d){
		this.m = mu;
		this.jt = j;
		this.des = d;
	}
	@Override
	public void stateChanged(ChangeEvent e) {		
		if(jt.getTabCount()==0){
			m.enableBoutons();
		des.setVisible(false);	
		}
		
		if (jt.getTabCount()>0){
		des.setInfos(((FModelisation) jt.getComponentAt(jt.getSelectedIndex())).getPath());
		m.addListener((FModelisation) jt.getComponentAt(jt.getSelectedIndex()), jt);
		m.MajButtons();
		}		
	}

}
