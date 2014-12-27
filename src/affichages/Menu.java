package affichages;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.multi.MultiButtonUI;

import listener_package.MyButtonRotListener;
import listener_package.MyButtonTransListener;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;

	JButton open;
	JButton rot;
	JButton trans;
	
	public Menu(FModelisation f){
		
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
	this.setBackground(Color.LIGHT_GRAY);

	ImageIcon img = new ImageIcon("rotation.png");
	ImageIcon resultat = new ImageIcon(img.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
	open = new JButton("Ouvrir");
	rot = new JButton("Rotation",resultat);
	trans = new JButton("Translation");
	
	
	trans.addActionListener(new MyButtonTransListener(trans,rot,f));
	rot.addActionListener(new MyButtonRotListener(trans,rot,f));
	
	
	this.add(open);
	this.add(rot);
	this.add(trans);
	

	}
}
