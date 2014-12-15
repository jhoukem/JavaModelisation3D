package listener_package;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;


public class MyListSelectionListener implements ListSelectionListener  {
	JList l;
	JTabbedPaneWithCloseIcons jt;
	public MyListSelectionListener(JList lib, JTabbedPaneWithCloseIcons j){
		this.l = lib;
		this.jt = j;
	}
	
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
			try {

				jt.addTab(l.getSelectedValue().toString(),new FModelisation(l.getSelectedValue().toString())/*,jt.getTabCount()*/);



			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		
	}

}
