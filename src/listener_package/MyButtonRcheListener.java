package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import exceptions.SegmentException;
import affichages.FModelisation;
import affichages.Fenetre3D;
import affichages.FormulaireRecherche;
import affichages.JTabbedPaneWithCloseIcons;
import affichages.Librairie;
import affichages.FormulaireRechercheInfo;
import affichages.Progression;
public class MyButtonRcheListener implements ActionListener {

	Librairie lib;
	Fenetre3D fenetre;
	JTabbedPaneWithCloseIcons jt;
	public MyButtonRcheListener(Librairie l, Fenetre3D f) {
		this.lib = l;
		this.fenetre = f;
		jt = fenetre.getJt();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final Progression progress = new Progression();
		new Thread(new Runnable() {
	        public void run(){
		FormulaireRecherche fr = new FormulaireRecherche(fenetre, "Recherche d'un fichier gts");
		FormulaireRechercheInfo fri = fr.getFormInfos();
		
		if(fr.isValid){
			String s = fri.getSelection();
			try {			
				progress.go();
				jt.addTab(s.substring(0,s.length()-4),new FModelisation(s,true));
				progress.end();		
			} catch (SegmentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	        }
	    }).start();
	}

}
