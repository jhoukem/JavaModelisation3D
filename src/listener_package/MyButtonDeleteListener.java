package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import affichages.FModelisation;
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
		chooser.setApproveButtonText("Supprimer");
		chooser.setApproveButtonToolTipText("Supprime l'élément sélectionné de la base de données");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			String s = chooser.getSelectedFile().getName();
			File selection = chooser.getSelectedFile();
			MyFileManager f = new MyFileManager();

			if (s.substring(s.length()-4).equals(".gts")){//si c'est bien un fichier gts
				this.maBase.open();
				ResultSet rs;
				try {
					rs = maBase.executeQry("select * from FichiersGts where path ='"+s+"'");	
					rs.next();//si on n'a pas d'exception c'est que le resultset a un resultat en donc que l'objet est présent ds la abse
					rs.getString("path");
					int cpt = lib.getJt().getTabCount();//On regarde le nbre d'onglet ouverts
					
					for(int i = 0 ; i < cpt; i++){//pr chaque onglet
						if( ( (FModelisation)lib.getJt().getComponentAt(i)).getFichier().equals(s) ){//si il c'est le fichier qu'on veut delete, on ferme l'onglet
							((FModelisation)lib.getJt().getComponentAt(i)).getGts().close();//on ferme le fichier ouvert en lecture
						}
					}
					if(f.delete(selection.getAbsolutePath())){//Si on a réussi a supprimer le fichier phsique on retire sa référence dans la BDD
						maBase.executeStmt("delete from FichiersGts where path = '"+s+"'");
						for(int i = 0 ; i < cpt; i++){//pr chaque onglet
							if( ( (FModelisation)lib.getJt().getComponentAt(i)).getFichier().equals(s) ){//si il c'est le fichier qu'on veut delete, on ferme l'onglet
								lib.getJt().removeTabAt(i);
								i--;//si on supprime les tab sont décalées vers la gauches, on en tient compte
								cpt--;//ici en reculant la taille et le i
							}
						}
					}
					else
						JOptionPane.showMessageDialog(null,"Le fichier à deja été ouvert relancez le programme et supprimez le sans l'afficher","Attention",JOptionPane.WARNING_MESSAGE);				
				}					
				 catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Le fichier n'apparait pas dans la base de données !","Erreur",JOptionPane.ERROR_MESSAGE);
				
				}
				finally{
					maBase.close();				
					lib.majTree();
				}

				
			}
			else
				JOptionPane.showMessageDialog(null,"Erreur ! "+s+" n'est pas un fichier 'gts'");		
		}
	}

}