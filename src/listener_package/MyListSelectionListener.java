package listener_package;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;


public class MyListSelectionListener implements ListSelectionListener  {
	JList l;
	JTabbedPaneWithCloseIcons jt;
	Outils m;
	public MyListSelectionListener(JList lib, JTabbedPaneWithCloseIcons j,Outils me){
		this.l = lib;
		this.jt = j;
		this.m=me;
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(jt.getTabCount()==1)			
			m.enableBoutons(true,false);
		
		if(e.getValueIsAdjusting()){			
			try {
				jt.addTab(l.getSelectedValue().toString(),new FModelisation(l.getSelectedValue().toString()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
