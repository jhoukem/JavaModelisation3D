package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;




import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;
import listener_package.MyButtonAjoutListener;
import listener_package.MyButtonResetListener;
import listener_package.MyButtonRotListener;
import listener_package.MyButtonTransListener;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;

	JButton ajout;
	JButton rot;
	JButton trans;
	JButton reset;
	JFileChooser fc;
	FModelisation fM ;
	
	private Librairie lib;
	public Menu(FModelisation f ,Librairie l){
		this.fM=f;
		this.setLib(l);
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
	this.setBackground(new Color(103,113,121));
	ImageIcon img = new ImageIcon("rotation.png");
	ImageIcon resultat = new ImageIcon(img.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
	ajout = new JButton("Ajouter");
	
	rot = new JButton("Rotation",resultat);
	trans = new JButton("Translation");
	reset = new JButton("Reset");
	rot.setEnabled(false);
	ajout.addActionListener(new MyButtonAjoutListener(getLib()));
	addListener(f);
	

	this.add(ajout);
	this.add(rot);
	this.add(trans);
	this.add(reset);

	}
	
	public void addListener(FModelisation f){
		if(f.isRot()){
			rot.setEnabled(false);
			trans.setEnabled(true);
		}
		else{
			rot.setEnabled(true);
			trans.setEnabled(false);
		}
		
		
		reset.addActionListener(new MyButtonResetListener(f));
		trans.addActionListener(new MyButtonTransListener(trans,rot,f));
		rot.addActionListener(new MyButtonRotListener(trans,rot,f));
	}
	
	public FModelisation getfM() {
		return fM;
	}
	public void setfM(FModelisation fM) {
		this.fM = fM;
	}

	public Librairie getLib() {
		return lib;
	}

	public void setLib(Librairie lib) {
		this.lib = lib;
	}
}
