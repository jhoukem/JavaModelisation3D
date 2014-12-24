package listener_package;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;

public class MyTreeSelectionListener implements TreeSelectionListener {


	JTree l;
	JTabbedPaneWithCloseIcons jt;
	Outils m;

	public MyTreeSelectionListener(JTree lib, JTabbedPaneWithCloseIcons j,Outils me){
		this.l = lib;
		this.jt = j;
		this.m=me;
	}




	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if(e.isAddedPath()){
			try {	
				if(!e.getNewLeadSelectionPath().getLastPathComponent().toString().equals("Base de donnees")){
					if(jt.getTabCount()==1)	{		
						m.MajButtons();
					}
					String c = e.getNewLeadSelectionPath().getLastPathComponent().toString();
					jt.addTab(c.substring(0,c.length()-4),new FModelisation(c,true));
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}



