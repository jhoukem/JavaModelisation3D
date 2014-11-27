package affichage;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import exceptions.SegmentException;



public class Fenetre3D extends JFrame{

	
public Fenetre3D() throws SegmentException{
	super("3D Project");
	FModelisation md = new FModelisation();
	Librairie lib = new Librairie();
	Descripteur d = new Descripteur();
	this.setLayout(new BorderLayout());
	this.setSize(this.getWidth(),this.getHeight());
	

	this.getContentPane().add(md,BorderLayout.CENTER);
	//this.getContentPane().add(lib,BorderLayout.WEST);
	//this.getContentPane().add(d,BorderLayout.CENTER);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
}
	
	
	
	
}
