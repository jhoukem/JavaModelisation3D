package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import affichages.Librairie;
import sqlite.FileCopy;
import sqlite.GtsBase;

public class MyButtonAjoutListener  implements ActionListener {

	GtsBase maBase; 
	Librairie lib;
	public MyButtonAjoutListener(Librairie l){
		this.lib=l;
		this.maBase = new GtsBase();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			String s = chooser.getSelectedFile().getName();
			File selection = chooser.getSelectedFile();
			FileCopy f = new FileCopy();

			if (s.substring(s.length()-4).equals(".gts")){
				int cpt=0;
				this.maBase.open();
				ResultSet rs;
				try {
					rs = maBase.executeQry("select max(id) from FichiersGts");
					rs.next();
					if(rs.getString("max(id)")==null){
						cpt=1;
					}
					else{
						cpt=1+Integer.parseInt(rs.getString("max(id)"));	
					}
					rs = maBase.executeQry("select * from FichiersGts where path ='"+s+"'");			
					rs.getString("path");
					JOptionPane.showMessageDialog(null,"Erreur ! Le fichier '"+s+"' existe déjà !");				
				} catch (SQLException e1) {
					try {
						maBase.executeStmt("insert into FichiersGts values('"+cpt+"','"+s+"')");
						f.copier(selection.getAbsolutePath(), "./gts_files/"+s);
						JOptionPane.showMessageDialog(null,"Le fichier '"+s+"' à bien été ajouté à la base !");
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}		
					//e1.printStackTrace();
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