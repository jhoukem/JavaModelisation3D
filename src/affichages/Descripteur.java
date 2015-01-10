package affichages;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sqlite.GtsBase;

public class Descripteur extends JPanel {
	
	JLabel title, des, dateAjout,nbFces,nbSgmts,nbPts,motCle;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Descripteur(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initInfos();
		this.add(title);
		this.add(des);
		this.add(motCle);
		this.add(dateAjout);
		this.add(nbFces);
		this.add(nbSgmts);
		this.add(nbPts);	
	  }

	private void initInfos() {
		title = new JLabel("Titre: ");
		des = new JLabel("Description: ");
		motCle = new JLabel("Mot Cle: ");
		dateAjout = new JLabel("Date Ajout: ");
		nbPts = new JLabel("Nombre de points: ");
		nbSgmts = new JLabel("Nombre de segments: ");
		nbFces = new JLabel("Nombre de faces: ");
		this.setVisible(false);
	}

	public void setInfos(String path) {
		GtsBase maBase = new GtsBase();
		try {
			maBase.open();
			ResultSet rs = maBase.executeQry("select * from FichiersGts where path='"+path+"'");
			rs.next();
			title.setText("Titre:                                      "+rs.getString("titre")); 
			des.setText("Description                          "+rs.getString("des")); 
			motCle.setText("Mot Clé:                                 "+rs.getString("motcle")); 
			dateAjout.setText("Date d'ajout à la librairie:  "+rs.getString("dateajout")); 
			nbPts.setText("Nombre de points:              "+rs.getString("nbpoints")); 
			nbSgmts.setText("Nombre de segments:       "+rs.getString("nbsegments")); 
			nbFces.setText("Nombre de faces:               "+rs.getString("nbfaces")); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			maBase.close();
			this.setVisible(true);
		}
		
	}
}
