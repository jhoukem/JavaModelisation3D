package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sqlite.GtsBase;
import affichages.Descripteur;
import affichages.FModelisation;
import affichages.Fenetre3D;
import affichages.Formulaire;
import affichages.FormulaireInfo;
import affichages.JTabbedPaneWithCloseIcons;

public class MyButtonModifierListener implements ActionListener {

	JTabbedPaneWithCloseIcons jt;
	 Descripteur des;
	 Fenetre3D fenetre3d;
	public MyButtonModifierListener(Fenetre3D f,JTabbedPaneWithCloseIcons j, Descripteur d) {
		this.jt = j;
		this.des = d;
		this.fenetre3d = f;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new Thread(new Runnable() {
	        public void run(){
		
		String path = ((FModelisation) jt.getComponentAt(jt.getSelectedIndex())).getPath();
		Formulaire form = new Formulaire(null,path, "Modification des données", "modif");
		FormulaireInfo formInfo = form.showFormulaire(); 
		if(form.isValid){
			GtsBase maBase = new GtsBase();
			try{
				maBase.open();
				maBase.executeStmt("update fichiersGts set titre ='"+formInfo.getTitre()+
						"',des='"+formInfo.getDescription()+"',motcle='"+formInfo.getMotClef()+"' where path='"+path+"'");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				maBase.close();
			}
			des.setInfos(path);
			jt.setTitleAt(jt.getSelectedIndex(), formInfo.getTitre());
		}
	
	       }
	    }).start();
	}

}
