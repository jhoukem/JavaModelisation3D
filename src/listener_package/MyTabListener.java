package listener_package;

import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Menu;

public class MyTabListener implements ChangeListener{

	Menu m;
	JTabbedPaneWithCloseIcons jt;
	public MyTabListener(Menu mu, JTabbedPaneWithCloseIcons j){
		this.m=mu;
		this.jt=j;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(jt.getTabCount()==0)
			m.enableBoutons(false,true);
		
		if (jt.getTabCount()>0){
		m.setfM((FModelisation) jt.getComponentAt(jt.getSelectedIndex()));
		m.addListener(m.getfM());
		}
	//	System.out.println(jt.getSelectedIndex());
		
	}

}
