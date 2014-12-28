package affichages;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormulaireRecherche extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton valide = new JButton("Valider");
	JTextField rche = new JTextField("Recherche");
	Librairie lib;
	
	
	public FormulaireRecherche(){
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(rche);
		
	}
}
