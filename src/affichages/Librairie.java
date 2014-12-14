
package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sqlite.SelectGts;
import exceptions.SegmentException;


public class Librairie extends JPanel implements ListSelectionListener{

	private static final long serialVersionUID = 1L;
	String tab[] ; 

	JLabel t;
	JList<String> l;
	SelectGts sgts;
	JScrollPane jsp;
	JTabbedPaneWithCloseIcons jt;
	public Librairie( JTabbedPaneWithCloseIcons j){	

		this.jt=j;
		this.setToolTipText("La Librairie permet de choisir un fichier à visualiser");
	
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(new Color(103,113,121));
		t = new JLabel("Librairie");
		t.setForeground(Color.white);
		this.add(t);
		getList();	
		this.jsp = new JScrollPane(l); 
		this.add(jsp);
	}

	public void getList(){
		sgts=new SelectGts("*");	
		tab = sgts.getList();	
		sgts.close();
		
		l = new JList<String>(tab);
		l.setToolTipText("Cliquez sur un fichier pour le charger");
		l.addListSelectionListener(this);
		
		/*l.setListData(tab);
		l.validate();
		l.revalidate();*/
		
		l.setVisible(true);	
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
		try {
			
			jt.addTab(l.getSelectedValue().toString(),new FModelisation(l.getSelectedValue().toString()),jt.getTabCount());
			
			

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	}
}
