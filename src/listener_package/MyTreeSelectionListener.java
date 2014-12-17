package listener_package;

import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

	/*
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(jt.getTabCount()==1)	{		
			m.MajButtons();

		}

		if(!e.getValueIsAdjusting()){	
			try {
				String s = l.getSelectedValue().toString();
				jt.addTab(s.substring(0,s.length()-4),new FModelisation(s));
			} catch (Exception e1) {
				//e1.printStackTrace();
			}
		}


	}
	 */

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if(jt.getTabCount()==1)	{		
			m.MajButtons();
		}
		System.out.println(l.getLeadSelectionPath());
		String c = l.getLastSelectedPathComponent().toString();
		if(c.substring(c.length()-4).equals(".gts")){
			System.out.println(l.getLastSelectedPathComponent());

			try {
				
				jt.addTab(c.substring(0,c.length()-4),new FModelisation(c));
			} catch (Exception e1) {
				//e1.printStackTrace();
			}




		}



	}

}



