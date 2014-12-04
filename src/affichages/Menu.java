package affichages;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.multi.MultiButtonUI;

import listener_package.MyButtonListener;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;

	public Menu(FModelisation f){
		
	
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
	this.setBackground(Color.LIGHT_GRAY);

	ImageIcon img = new ImageIcon("rotation.png");
	ImageIcon resultat = new ImageIcon(img.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
	Button bt1 = new Button("Ouvrir");
	this.add(bt1);
	JButton bt2 = new JButton("",resultat);
	this.add(bt2);
	Button bt3 = new Button("Translation");
	this.add(bt3);
	bt2.addActionListener(new MyButtonListener(f));
	}
}
