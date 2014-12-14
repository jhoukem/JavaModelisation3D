package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import affichages.Librairie;
import sqlite.AddGts;

public class MyButtonAjoutListener  implements ActionListener {

	AddGts agts; 
	Librairie lib;
	public MyButtonAjoutListener(Librairie l){
		this.lib=l;
		this.agts = new AddGts();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
		String s = chooser.getSelectedFile().getName();
		if (s.substring(s.length()-4).equals(".gts"))
			agts.addThis(s);
		else
			 JOptionPane.showMessageDialog(null,"Erreur ! "+s+" n'est pas un fichier \"gts\"");
			 
		agts.close();
		
	}
	}

}