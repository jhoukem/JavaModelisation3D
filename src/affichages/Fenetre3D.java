package affichages;

import java.awt.BorderLayout;

import javax.swing.JFrame;



public class Fenetre3D extends JFrame{


	public Fenetre3D(String fichier) {
		super("3D Project");
		FModelisation md;
		try {
			md = new FModelisation(fichier);
			Librairie lib = new Librairie();
			Descripteur d = new Descripteur();
			this.setLayout(new BorderLayout());
			this.setSize(this.getWidth(),this.getHeight());
			this.getContentPane().add(md,BorderLayout.CENTER);
			//this.getContentPane().add(lib,BorderLayout.WEST);
			//this.getContentPane().add(d,BorderLayout.NORTH);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}