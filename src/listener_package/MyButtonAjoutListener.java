package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import affichages.Fenetre3D;
import affichages.Formulaire;
import affichages.Librairie;
import sqlite.MyFileManager;
import sqlite.GtsBase;

public class MyButtonAjoutListener  implements ActionListener {

	GtsBase maBase; 
	Librairie lib;
	Formulaire form;
	Fenetre3D fenetre;
	public MyButtonAjoutListener(Librairie l, Fenetre3D fenetre){
		this.lib=l;
		this.maBase = new GtsBase();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Importer");
		chooser.setApproveButtonToolTipText("Importe l'élément sélectionné dans la base de données");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			String s = chooser.getSelectedFile().getName();
			File selection = chooser.getSelectedFile();
			MyFileManager f = new MyFileManager();

			if (s.substring(s.length()-4).equals(".gts")){
				this.maBase.open();
				ResultSet rs = null;
				try {
					rs = maBase.executeQry("select * from FichiersGts where path ='"+s+"'");			
					rs.getString("path");
					JOptionPane.showMessageDialog(null,"Erreur ! Le fichier '"+s+"' existe déjà !");				
				} catch (SQLException e1) {
					try {
						int cpt = getMaxValue(rs);
						form = new Formulaire(fenetre,selection.getAbsolutePath());
						if(form.isValid){
						maBase.executeStmt("insert into FichiersGts values('"+cpt+"','"+s+"','"+form.getTitle()+"','"+form.getDes()+"','"+form.getKeyWord()+"')");
						f.copier(selection.getAbsolutePath(), "./gts_files/"+s);
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}		
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

	public int getMaxValue(ResultSet rs){
		int cpt = 0;
		try {
			rs = maBase.executeQry("select max(id) from FichiersGts");
			rs.next();
			if(rs.getString("max(id)")==null){
				cpt=1;
			}
			else{
				cpt=1+Integer.parseInt(rs.getString("max(id)"));	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cpt;
	}
	
}

