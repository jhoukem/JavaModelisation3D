
package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import listener_package.MyListSelectionListener;
import sqlite.GtsBase;
import sqlite.SelectGts;
import exceptions.SegmentException;


public class Librairie extends JPanel {

	private static final long serialVersionUID = 1L;
	String tab[] ; 

	JLabel t;
	JList<String> l;
	GtsBase maBase;
	JScrollPane jsp;
	JTabbedPaneWithCloseIcons jt;
	public Librairie( JTabbedPaneWithCloseIcons j){	

		this.jt=j;
		this.setToolTipText("La Librairie permet de choisir un fichier à visualiser");


		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(new Color(36,66,124));
		t = new JLabel("Librairie");
		t.setForeground(Color.white);
		this.add(t);
		getList();	
		l.setToolTipText("Cliquez sur un fichier pour le charger");
		//l.addListSelectionListener(new MyListSelectionListener(l, jt));
		this.jsp = new JScrollPane(l); 
		this.add(jsp);
	}

	public void getList(){
		maBase = new GtsBase();	
		maBase.open();
		try {
			tab = maBase.getList(maBase.executeQry("select * from FichiersGts"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			maBase.close();	
		}
		
		l = new JList<String>(tab);		
		l.setVisible(true);	
		this.add(l);
	}
	
	public void getListMaj(){
		maBase = new GtsBase();	
		maBase.open();
		try {
			tab = maBase.getList(maBase.executeQry("select * from FichiersGts"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			maBase.close();	
		}
		
		this.remove(l);
		l = new JList<String>(tab);
		/*l.setListData(tab);
		l.validate();
		l.revalidate();
		l.repaint();*/
		
		l.setVisible(true);	
		this.add(l);
	}
	
	
	
	


}
