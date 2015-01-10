package affichages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import sqlite.GtsBase;
import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;


public class Formulaire extends JDialog implements ActionListener{
	private static final long serialVersionUID = 1L;
	private FormulaireInfo formInfo = new FormulaireInfo();
	private JLabel titreLabel, descriptionLabel, motClefLabel, imageFenetre;
	private JRadioButton couleur1, couleur2, couleur3, couleur4, couleur5, couleur6, couleur7, couleur8;
	private JTextField titre, motClef;
	private JTextArea description;
	public boolean isValid = false;
	FModelisation f;

	public Formulaire (Fenetre3D fenetre ,String path, String title){
		super(fenetre, title, true);
		this.setSize(750, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(Formulaire.DISPOSE_ON_CLOSE);
		f = null;
		try {
			f = new FModelisation(path,false);
		} catch (SegmentException e) {
			e.printStackTrace(); 
		}
		this.initComponent();
		//this.setOldText(path);

	}
	public Formulaire (Fenetre3D fenetre ,String path, String title, String s){
		super(fenetre, title, true);
		this.setSize(750, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(Formulaire.DISPOSE_ON_CLOSE);
		f = new FModelisation();
		try {
			f.setFigure(path, true);
			f.initZoom();
			f.repaint();
			f.setVisible(true);
			this.initComponent();
			this.setOldText(path);
		} catch (SegmentException | VectorException
				| MatriceNotCorrespondingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setOldText(String path) {
		String title="";
		String des="";
		String motcle="";
		GtsBase maBase = new GtsBase();
		try{
			maBase.open();
			ResultSet rs = maBase.executeQry("select * from FichiersGts where path ='"+path+"'");
			while(rs.next()){
				title = rs.getString("titre");
				des = rs.getString("des");
				motcle = rs.getString("motcle");
			}
			this.titre.setText(title);
			this.motClef.setText(motcle);
			this.description.setText(des);		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			maBase.close();
		}
	}
	public FormulaireInfo showFormulaire(){
		this.setVisible(true);
		return this.formInfo;
	}

	public String getCouleur(){
		return (couleur1.isSelected()) ? couleur1.getText() : 
			(couleur2.isSelected()) ? couleur2.getText() : 
				(couleur3.isSelected()) ? couleur3.getText() : 
					(couleur4.isSelected()) ? couleur4.getText() : 
						(couleur5.isSelected()) ? couleur5.getText() : 
							(couleur6.isSelected()) ? couleur6.getText() : 
								(couleur7.isSelected()) ? couleur7.getText() : 
									(couleur8.isSelected()) ? couleur8.getText() : 
										couleur1.getText();  
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Annuler")){
			this.isValid = false;
			this.dispose();			
		}
		else{
			String s = "";
			int p = 0;
			if(titre.getText().isEmpty() ){
				s="'Titre' ";
				p++;
			}
			if(motClef.getText().isEmpty()){
				s+="'Mot Cle'";
			p++;	
			}
			if(!s.isEmpty()){
				s+=".";
				this.setAlwaysOnTop(false);
				String m = "";
				if(p==2)
					m = "Les champs suivants sont obligatoire: "; 
				else
					m ="Le champ suivant est obligatoire: ";
				JOptionPane.showMessageDialog(null,"Enregistrement du fichier impossible ! "+m+s,"Erreur",JOptionPane.ERROR_MESSAGE);
				this.setAlwaysOnTop(true);
			}
			else {	
				this.isValid = true;
				formInfo = new FormulaireInfo(titre.getText(), description.getText(), motClef.getText(), getCouleur());
				this.dispose();
			}
			
		}

	}

	public void initComponent(){	
		JPanel panImage = new JPanel();
		panImage.setPreferredSize(new Dimension(150,80));
		panImage.setBackground(Color.white);
		panImage.setLayout(new BorderLayout());
		panImage.add(f);

		JPanel panTitre = new JPanel();
		panTitre.setBackground(Color.white);
		panTitre.setPreferredSize(new Dimension(220,80));
		titre = new JTextField();
		titre.setPreferredSize(new Dimension(120, 25));
		panTitre.setBorder(BorderFactory.createTitledBorder("Titre"));
		titreLabel = new JLabel("Titre pour la figure:");
		panTitre.add(titreLabel);
		panTitre.add(titre);

		JPanel panDescription = new JPanel();
		panDescription.setBackground(Color.white);
		panDescription.setPreferredSize(new Dimension(450,150));
		description = new JTextArea();
		description.setBorder(BorderFactory.createTitledBorder(""));
		description.setPreferredSize(new Dimension(300,100));
		panDescription.setBorder(BorderFactory.createTitledBorder("Description"));
		descriptionLabel = new JLabel("Description de l'objet:");
		panDescription.add(descriptionLabel);
		panDescription.add(description);

		JPanel panMotClef = new JPanel();
		panMotClef.setBackground(Color.white);
		panMotClef.setPreferredSize(new Dimension(220,80));
		motClef = new JTextField();
		motClef.setPreferredSize(new Dimension(120, 25));
		panMotClef.setBorder(BorderFactory.createTitledBorder("Mot clef"));
		motClefLabel = new JLabel("Mot clef");
		panMotClef.add(motClefLabel);
		panMotClef.add(motClef);

		JPanel panCouleur = new JPanel();
		panCouleur.setBackground(Color.white);
		panCouleur.setBorder(BorderFactory.createTitledBorder("Couleur de la figure"));
		panCouleur.setPreferredSize(new Dimension(440, 60));
		couleur1 = new JRadioButton("Marron");
		couleur1.setSelected(true);
		couleur2 = new JRadioButton("Vert");
		couleur3 = new JRadioButton("Bleu");
		couleur4 = new JRadioButton("Rouge");
		couleur5 = new JRadioButton("Jaune");
		couleur6 = new JRadioButton("Orange");
		couleur7 = new JRadioButton("Gris");
		couleur8 = new JRadioButton("Noir");
		ButtonGroup bg = new ButtonGroup();
		bg.add(couleur1);
		bg.add(couleur2);
		bg.add(couleur3);
		bg.add(couleur4);
		bg.add(couleur5);
		bg.add(couleur6);
		bg.add(couleur7);
		bg.add(couleur8);
		panCouleur.add(couleur1);
		panCouleur.add(couleur2);
		panCouleur.add(couleur3);
		panCouleur.add(couleur4);
		panCouleur.add(couleur5);
		panCouleur.add(couleur6);
		panCouleur.add(couleur7);
		panCouleur.add(couleur8);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		//content.setLayout(new GridLayout(2,2,5,5));
		content.setLayout(new BorderLayout());
		JPanel nord = new JPanel();

		nord.add(panTitre);
		nord.add(panMotClef);
		nord.setLayout(new GridLayout(1,1,5,5));
		content.add(panDescription, BorderLayout.CENTER);
		//content.add(panCouleur, BorderLayout.SOUTH);
		content.add(nord, BorderLayout.NORTH);


		JPanel control = new JPanel();
		JButton okBouton = new JButton("Enregistrer");

		okBouton.addActionListener(this);

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(this);


		control.add(okBouton);
		control.add(cancelBouton);
		this.getContentPane().add(panImage, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
		this.setAlwaysOnTop(true);
	}

	public int[] getInfos() {
		return f.getInfos();
	}


}

