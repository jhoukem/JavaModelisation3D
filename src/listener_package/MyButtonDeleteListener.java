package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import affichages.Librairie;
import sqlite.MyFileManager;
import sqlite.GtsBase;

public class MyButtonDeleteListener  implements ActionListener {

	GtsBase maBase; 
	Librairie lib;
	public MyButtonDeleteListener(Librairie l){
		this.lib=l;
		this.maBase = new GtsBase();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser("./gts_files");

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			String s = chooser.getSelectedFile().getName();
			File selection = chooser.getSelectedFile();
			MyFileManager f = new MyFileManager();

			if (s.substring(s.length()-4).equals(".gts")){
				this.maBase.open();
				ResultSet rs;
				try {
					rs = maBase.executeQry("select * from FichiersGts where path ='"+s+"'");	
					rs.next();
					rs.getString("path");
					maBase.executeStmt("delete from FichiersGts where path = '"+s+"'");
					f.delete(selection.getAbsolutePath());
					JOptionPane.showMessageDialog(null,"Le fichier '"+s+"' à bien été supprimé de la base !");
					
				}
					
									
				 catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Erreur ! Le fichier n'apparait pas dans la base de données !");
				
				}
				finally{
					maBase.close();				
					lib.getListMaj();
				}

				
			}
			else
				JOptionPane.showMessageDialog(null,"Erreur ! "+s+" n'est pas un fichier 'gts'");		
		}
	}

}