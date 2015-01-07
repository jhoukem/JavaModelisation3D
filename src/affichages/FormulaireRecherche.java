package affichages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;
import sqlite.GtsBase;


public class FormulaireRecherche extends JDialog implements ActionListener, ListSelectionListener{
	private static final long serialVersionUID = 1L;
	private FormulaireRechercheInfo formRechInfo = new FormulaireRechercheInfo();
	private JLabel  nbrPointLabel,nbrFaceLabel, nbrSegmentLabel, motClefLabel, imageRecherche;
	private JTextField motClef, nbrFace, nbrSegment, nbrPoint;
	private JList <String>liste;
	public boolean isValid = false;
	private FModelisation fm;
	private String selected; 
	private String lastSelected="";
	FModelisation f;



	public FormulaireRecherche (Fenetre3D fenetre, String title){

		super(fenetre, title, true);
		this.setSize(750, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(Formulaire.DISPOSE_ON_CLOSE);
		this.initComponent();

	}

	public FormulaireRechercheInfo showFormulaireRech(){
		this.setVisible(true);
		return this.formRechInfo;
	}



	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Annuler")){
			this.isValid = false;
			this.dispose();
		}else if (e.getActionCommand().equals("Ouvrir")){
			if(liste.getSelectedValue()!=null){
				this.isValid = true;
				this.dispose();		
			}
			else 
				JOptionPane.showMessageDialog(null,"Aucun fichier n'est sélectionné !","Erreur",JOptionPane.ERROR_MESSAGE);	
		}
		else{
			if(!motClef.getText().isEmpty() && !lastSelected.equals(motClef.getText()))
				majListe();
			else if(motClef.getText().isEmpty())
				JOptionPane.showMessageDialog(null,"Vous n'avez pas renseigné de mot clef !","Erreur",JOptionPane.INFORMATION_MESSAGE);		
			lastSelected = motClef.getText();
		}
	}


	private void majListe() {
		GtsBase maBase = new GtsBase();
		try{
			ArrayList<String> l = new ArrayList<String>();
			maBase.open();
			ResultSet rs = maBase.executeQry("select * from FichiersGts where motcle like '%"+motClef.getText()+"%' "
					+ "or des like '%"+motClef.getText()+"%' or titre like '%"+motClef.getText()+"%'");
			while(rs.next()){
				l.add(rs.getString("path"));	
			}
			String tab[] = new String[l.size()];
			for(int j = 0; j < tab.length; j++){
				tab[j] = l.get(j);
			}
			liste.setListData(tab);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			maBase.close();
		}
	}

	public void initComponent(){
		imageRecherche = new JLabel(new ImageIcon("icon/recherche.jpg"));
		imageRecherche.setPreferredSize(new Dimension(180,80));
		JPanel panImage2 = new JPanel();
		panImage2.setPreferredSize(new Dimension(180,70));
		panImage2.setBackground(Color.white);
		panImage2.setLayout(new BorderLayout());
		try {
			fm = new FModelisation("sphere5.gts",true);
			panImage2.add(fm);
		} catch (SegmentException e) {
			e.printStackTrace();
		}

		JPanel panRechMultiCritere = new JPanel();
		panRechMultiCritere.setBackground(Color.white);
		panRechMultiCritere.setPreferredSize(new Dimension(150,75));
		panRechMultiCritere.setBorder(BorderFactory.createTitledBorder("Critères de recherche optionnel"));
		nbrFaceLabel = new JLabel("Nombre de face de l'objet:");
		nbrSegmentLabel = new JLabel("Nombre de segment de l'objet:");
		nbrPointLabel = new JLabel("Nombre de point de l'objet:");
		nbrPoint = new JTextField();
		nbrSegment = new JTextField();
		nbrFace = new JTextField();
		nbrPoint.setPreferredSize(new Dimension(120, 25));
		nbrSegment.setPreferredSize(new Dimension(120, 25));
		nbrFace.setPreferredSize(new Dimension(120, 20));
		panRechMultiCritere.add(nbrPointLabel);
		panRechMultiCritere.add(nbrPoint);
		panRechMultiCritere.add(nbrSegmentLabel);
		panRechMultiCritere.add(nbrSegment);
		panRechMultiCritere.add(nbrFaceLabel);
		panRechMultiCritere.add(nbrFace);
		panRechMultiCritere.setLayout(new GridLayout(3,2,5,5));


		JPanel panRechMotClef = new JPanel();
		panRechMotClef.setBackground(Color.white);
		panRechMotClef.setPreferredSize(new Dimension(220,80));
		motClef = new JTextField();
		motClef.setPreferredSize(new Dimension(120, 25));
		//motClef.setHorizontalAlignment(motClef.RIGHT);
		panRechMotClef.setBorder(BorderFactory.createTitledBorder("Critères de recherche"));
		motClefLabel = new JLabel("Mot clef");
		panRechMotClef.add(motClefLabel);
		panRechMotClef.add(motClef);


		JPanel panJList = new JPanel();
		panJList.setBackground(Color.white);
		panJList.setBorder(BorderFactory.createTitledBorder("Résultats de la recherche"));
		panJList.setPreferredSize(new Dimension(140,80));
		//JList <String>liste = new JList<String>();
		String obj [] = {""};
		liste = new JList<String>(obj);

		liste.addListSelectionListener(this);
		panJList.add(liste);

		JPanel content2 = new JPanel();
		content2.setBackground(Color.white);
		content2.setLayout(new BorderLayout());



		content2.add(panRechMotClef, BorderLayout.NORTH);
		content2.add(panImage2, BorderLayout.CENTER);


		JPanel control2 = new JPanel();
		JButton okBouton2 = new JButton("Ouvrir");
		okBouton2.addActionListener(this);

		JButton cancelBouton2 = new JButton("Annuler");
		cancelBouton2.addActionListener(this);

		JButton RechercheBouton2 = new JButton("Rechercher");
		RechercheBouton2.addActionListener(this);

		control2.add(okBouton2);
		control2.add(cancelBouton2);
		control2.add(RechercheBouton2);
		//this.getContentPane().add(panImage2, BorderLayout.WEST);
		this.getContentPane().add(content2, BorderLayout.CENTER);
		this.getContentPane().add(control2, BorderLayout.SOUTH);
		this.getContentPane().add(panJList, BorderLayout.EAST);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}

	@Override
	public void valueChanged(final ListSelectionEvent e) {

		final Progression progress = new Progression();
		new Thread(new Runnable() {
			public void run(){
				if(!e.getValueIsAdjusting()){
					try{
						selected = liste.getSelectedValue();
						if(selected != null ){
							if(!selected.isEmpty()){
								try {
									progress.go();
									fm.show();
									fm.setFigure(selected, true);
									fm.initZoom();
									fm.repaint();
									progress.end();
								} catch (SegmentException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (VectorException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (MatriceNotCorrespondingException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							
						}
						else
							fm.hide();
					}
						catch(NullPointerException e2){
							e2.printStackTrace();
								
						}
				}
			}
		}).start();
	}
	public FormulaireRechercheInfo getFormInfos(){
		return new FormulaireRechercheInfo(motClef.getText(), nbrFace.getText(), nbrSegment.getText(), nbrPoint.getText(),selected);
	}
}

