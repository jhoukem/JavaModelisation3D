package listener_package;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import affichages.FModelisation;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Outils;
import affichages.Progression;


public class MyListSelectionListener implements ListSelectionListener  {
	JList<String> l;
	JTabbedPaneWithCloseIcons jt;
	Outils m;
	public MyListSelectionListener(JList<String> lib, JTabbedPaneWithCloseIcons j,Outils me){
		this.l = lib;
		this.jt = j;
		this.m=me;
	}
	
	
	@Override
	public void valueChanged(final ListSelectionEvent e) {
		
		final Progression progress = new Progression();
	    
		
		new Thread(new Runnable() {
	        public void run(){
	        	
	        	if(jt.getTabCount()==1)	{		
	    			m.MajButtons();
	    			
	    		}
	    		
	    		if(!e.getValueIsAdjusting()){	
	    			progress.go();
	    			try {
	    				String s = l.getSelectedValue().toString();
	    				jt.addTab(s.substring(0,s.length()-4),new FModelisation(s,true));
	    			} catch (Exception e1) {
	    				//e1.printStackTrace();
	    			}
	    			progress.end();
	    		}
	    		
	        }
	    }).start();
		
		
		
		
	}

}
