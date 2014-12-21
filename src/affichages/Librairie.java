
package affichages;


import java.awt.Color;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import sqlite.GtsBase;


public class Librairie extends JPanel {

	private static final long serialVersionUID = 1L;
	String tab[] ; 
	JTree test = new JTree(tab);	
	JLabel t;
	JList<String> l;
	GtsBase maBase;
	JScrollPane jsp;
	JTabbedPaneWithCloseIcons jt;
	

	public Librairie( JTabbedPaneWithCloseIcons j){	
		this.jt=j;
		getList();		
	}

	public void getList(){
		
		this.setToolTipText("La Librairie permet de choisir un fichier � visualiser");
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(new Color(36,66,124));
		t = new JLabel("Librairie");
		t.setForeground(Color.white);
		this.add(t);
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
		l.setToolTipText("Cliquez sur un fichier pour le charger");
		this.jsp = new JScrollPane(l); 
		this.add(jsp);
	}
	
	public void getListMaj(){	
		maBase.open();
		try {
			tab = maBase.getList(maBase.executeQry("select * from FichiersGts"));
			l.setListData(tab);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		finally{
			maBase.close();	
		}		
	}
	
	
	public JTabbedPaneWithCloseIcons getJt() {
		return jt;
	}

}
