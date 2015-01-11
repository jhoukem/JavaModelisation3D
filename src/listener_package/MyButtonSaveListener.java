package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import sqlite.GtsBase;
import sqlite.MyFileManager;
import affichages.Librairie;

public class MyButtonSaveListener implements ActionListener {

	Librairie lib;
	public MyButtonSaveListener(Librairie l) {
		this.lib = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		File selection = null ;
		MyFileManager f = new MyFileManager();

		JFileChooser chooser = new JFileChooser("./data/gts_files");
		chooser.setApproveButtonText("Selectionner");
		chooser.setDialogTitle("Selectionner");
		chooser.setApproveButtonToolTipText("Choisissez l'élément à sauvegarder");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			String s = chooser.getSelectedFile().getName();
			GtsBase maBase = new GtsBase();
			if (s.substring(s.length()-4).equals(".gts")){//si c'est bien un fichier gts
				maBase.open();
				ResultSet rs;
				try {
					rs = maBase.executeQry("select * from FichiersGts where path ='"+s+"'");	
					rs.next();//si on n'a pas d'exception c'est que le resultset a un resultat en donc que l'objet est présent ds la abse
					rs.getString("path");
					selection = chooser.getSelectedFile();	
					chooser.setApproveButtonText("Importer");
					chooser.setApproveButtonToolTipText("Sauvegarde l'élément sélectionné dans la base de données");
					chooser.setSelectedFile(selection);
					chooser.setDialogTitle("Sauvegarder");
					chooser.setCurrentDirectory(null);
					returnVal = chooser.showSaveDialog(null);
					if (returnVal != JFileChooser.CANCEL_OPTION) {	
						if(f.copier(selection.getAbsolutePath(), chooser.getSelectedFile().toString()))
							JOptionPane.showMessageDialog(null,"Le fichier "+s+" a bien été copié ","Information",JOptionPane.INFORMATION_MESSAGE);
						else 
							JOptionPane.showMessageDialog(null,"Le fichier n'a pas pu être copié","Erreur",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception e2){
					JOptionPane.showMessageDialog(null,"Le fichier n'appartient pas à la base de donnée et n'a donc pas pu être copié","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				finally{
					maBase.close();
				}
			}
			else
				JOptionPane.showMessageDialog(null,"Ce fichier n'est pas un fichier au format 'gts'","Erreur",JOptionPane.ERROR_MESSAGE);
		}
	}
}
