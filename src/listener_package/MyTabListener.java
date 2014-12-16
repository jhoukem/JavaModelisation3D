package listener_package;

import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyTabListener implements ChangeListener{

	Outils m;
	JTabbedPaneWithCloseIcons jt;
	public MyTabListener(Outils mu, JTabbedPaneWithCloseIcons j){
		this.m=mu;
		this.jt=j;
	}
	@Override
	public void stateChanged(ChangeEvent e) {		
		if(jt.getTabCount()==0)
			m.enableBoutons();
		
		if (jt.getTabCount()>0){
	
		//m.setfM((FModelisation) jt.getComponentAt(jt.getSelectedIndex()));
		m.addListener((FModelisation) jt.getComponentAt(jt.getSelectedIndex()), jt);//m.addListener(m.getfM(), jt);
		m.MajButtons();
		}		
	}

}
