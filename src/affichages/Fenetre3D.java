package affichages;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Fenetre3D extends JFrame{

	

	public Fenetre3D(String fichier) {
		super("3D Project");
		FModelisation md;
		
		
		try {
			md = new FModelisation(fichier);
			
			this.setLayout(new BorderLayout());

			
			
			
			this.setSize(this.getWidth(),this.getHeight());
			this.getContentPane().add(md,BorderLayout.CENTER);
			
			this.add(new Menu(md), BorderLayout.NORTH);
			this.add(new Librairie(md),BorderLayout.WEST);
			//this.getContentPane().add(lib,BorderLayout.WEST);
			//this.getContentPane().add(d,BorderLayout.NORTH);
			this.pack();
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
