package affichages;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import listener_package.MyButtonAjoutListener;
import exceptions.SegmentException;

public class Formulaire extends JDialog implements ActionListener{

	JButton annule = new JButton("Annuler");
	JButton valide = new JButton("Valider");
	JTextField title = new JTextField("titre");
	JTextArea des= new JTextArea("description");
	JTextField keyWord= new JTextField("motcle");
	public boolean isValid = true;
	

	public Formulaire(Fenetre3D fenetre, String s){
		super(fenetre,"test",true);
		FModelisation f = null;
		try {
			f = new FModelisation(s,false);
		} catch (SegmentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  
		}
		annule = new JButton("Annuler");
		annule.addActionListener(this);
		valide = new JButton("Valider");
		valide.addActionListener(this);
		title = new JTextField("titre");
		des = new JTextArea("description");
	
		new JTextField("Motcle");
		JPanel textFieldPanel = new JPanel();
		JPanel all = new JPanel();
		JPanel north = new JPanel();
		JPanel middle = new JPanel();

		JPanel south = new JPanel();

		textFieldPanel.setLayout(new BoxLayout(textFieldPanel,BoxLayout.Y_AXIS));
		textFieldPanel.add(title);
		textFieldPanel.add(keyWord);
		middle.setLayout(new GridLayout(3,1));
		
		middle.add(title);
		middle.add(keyWord);
		middle.add(des);

		south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
		south.add(annule);
		south.add(valide);


		north.setLayout(new BoxLayout(north,BoxLayout.X_AXIS));

		//north.add(textFieldPanel);
		north.add(f);

		all.setLayout(new BoxLayout(all,BoxLayout.Y_AXIS));
		all.add(north);
		all.add(middle);
		all.add(south);
		this.getContentPane().setLayout(new BorderLayout());
		//this.add(north, BorderLayout.NORTH);
		this.getContentPane().add(all,BorderLayout.CENTER);

		this.setSize(300,400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		// this.setResizable(false);
		this.setAlwaysOnTop(true);
		
	}

	public String getTitle(){
	return title.getText();	
	}
	public String getDes(){
	return des.getText();
	}

	public String getKeyWord(){
	return keyWord.getText();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Annuler"))
			this.isValid = false;
		else
			this.isValid = true;
		this.dispose();		
	}

}
