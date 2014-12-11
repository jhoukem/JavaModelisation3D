
package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Librairie extends JPanel{

	private static final long serialVersionUID = 1L;
	String tab[] ; 
	
	JLabel t;
	
	
	public Librairie(){	
		tab = new String[10];
		tab[0]= "test 1";
		tab[1]= "test 2";
		tab[2]= "test 3";
	JList l = new JList(tab);
	l.setVisible(true);
	this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
	this.setBackground(Color.gray);
	t = new JLabel("Librairie");

	this.add(t);
	
	this.add(l);

	

	}
}
