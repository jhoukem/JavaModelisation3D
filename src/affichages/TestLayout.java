package affichages;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exceptions.SegmentException;

public class TestLayout extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestLayout(){
		FModelisation f = null;
		try {
			 f = new FModelisation("cone.gts",true);
		} catch (SegmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JButton annule = new JButton("Annuler");
		JButton valide = new JButton("Valider");
		JTextArea title = new JTextArea("titre");
		JTextField des= new JTextField("description");
		JTextField keyWord= new JTextField("motcle");
		JPanel all = new JPanel();
		
		all.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;                                        // 2 colonne
		c.gridy = 0;                                        // 1 ligne
		c.gridwidth = 1;    		//largeur
		c.gridheight = 4;
		c.insets = new Insets(10,10,10,10);
		all.add(f,c);//ajoutC au mileu sur 4 col
		c.gridy = 4;
		c.gridwidth = 3;  
		c.gridheight = 1;
		all.add(title,c);
		c.gridy = 5;
		all.add(des,c);
		c.gridy = 6;
		all.add(keyWord,c);
		c.gridy = 7;
		
		this.add(all);
		
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
        this.setVisible(true);
       // this.setResizable(false);
        this.setAlwaysOnTop(true);
	}
	
	public static void main(String args[]){
		new TestLayout();
	}

}

