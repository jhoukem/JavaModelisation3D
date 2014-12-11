
package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sqlite.SelectGts;
import exceptions.SegmentException;


public class Librairie extends JPanel implements ListSelectionListener{

	private static final long serialVersionUID = 1L;
	String tab[] ; 
	FModelisation fM;
	JLabel t;
	JList l;
	SelectGts sgts;
	
	public Librairie(FModelisation m){	
			this.fM=m;
		sgts=new SelectGts();	
		tab = sgts.getList();	
		sgts.close();
		System.out.println(tab[0]);
	l = new JList(tab);
	l.setVisible(true);
	
	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	this.setBackground(Color.gray);
	t = new JLabel("Librairie");
	l.addListSelectionListener(this);
	this.add(t);
	
	this.add(l);

	

	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			
			fM.setFigure(l.getSelectedValue().toString());
			fM.repaint();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
