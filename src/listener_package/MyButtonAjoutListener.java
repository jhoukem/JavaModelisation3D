package listener_package;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import sqlite.GtsBase;
import sqlite.MyFileManager;
import affichages.Fenetre3D;
import affichages.Formulaire;
import affichages.FormulaireInfo;
import affichages.GtsReader;
import affichages.Librairie;

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
		chooser.setDialogTitle("Importer");
		chooser.setApproveButtonToolTipText("Importe l'élément sélectionné dans la base de données");
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			String s = chooser.getSelectedFile().getName();
			File selection = chooser.getSelectedFile();
			MyFileManager f = new MyFileManager();

			if (s.substring(s.length()-4).equals(".gts")){
				GtsReader gt = new GtsReader();

				if(gt.isValid(selection.getAbsolutePath())){
					this.maBase.open();
					ResultSet rs = null;
					try {
						rs = maBase.executeQry("select * from FichiersGts where path ='"+s+"'");			
						rs.getString("path");
						JOptionPane.showMessageDialog(null,"Le fichier '"+s+"' existe déjà !","Erreur",JOptionPane.ERROR_MESSAGE);				
					} catch (SQLException e1) {
						try {
							int cpt = getMaxValue(rs);
							form = new Formulaire(fenetre, selection.getAbsolutePath());
					        FormulaireInfo formInfo = form.showFormulaire(); 
					        //JOptionPane jop = new JOptionPane();
					       // jop.showMessageDialog(null, formInfo.toString(), "Informations sur l'objet", JOptionPane.INFORMATION_MESSAGE);
					        if(form.isValid){
								if(f.copier(selection.getAbsolutePath(), "./gts_files/"+s)){
									maBase.executeStmt("insert into FichiersGts values('"+cpt+"','"+s+"','"+formInfo.getTitre()+
											"','"+formInfo.getDescription()+"','"+formInfo.getMotClef()+"','"+getTime() +"','"
											+form.getInfos()[0]+"','"+form.getInfos()[1]+"','"+form.getInfos()[2]+"')");
								
								}else
									JOptionPane.showMessageDialog(null,"Impossible de copier le fichier'"+s+"' vérifiez qu'il n'est pas ouvert ou utilisé par une autre application","Erreur",JOptionPane.ERROR_MESSAGE);
					        }
						} catch (SQLException e2) {
							e2.printStackTrace();
						}		
					}

					finally{
						maBase.close();				
						lib.majTree();
					}	

				}
				else
					JOptionPane.showMessageDialog(null,"Le fichier '"+s+"' est corrompu !","Erreur",JOptionPane.ERROR_MESSAGE);
			}
			else	
				JOptionPane.showMessageDialog(null,"Le fichier '"+s+"' n'est pas un fichier 'gts'","Erreur",JOptionPane.ERROR_MESSAGE);		
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

	public String getTime(){
		String format = "dd/MM/yy H:mm:ss";
		SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
		java.util.Date date = new java.util.Date(); 
		return formater.format( date );
		//System.out.println( formater.format( date ) ); 
	}
}

