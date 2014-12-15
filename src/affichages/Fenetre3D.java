package affichages;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import listener_package.MyKeyboardListener;
import listener_package.MyTabListener;



public class Fenetre3D extends JFrame{

	JTabbedPaneWithCloseIcons jt;
	JSplitPane sp;
	public Fenetre3D() {
		super("3D Project");
		FModelisation md;
		jt = new JTabbedPaneWithCloseIcons();
		

		try {
			Librairie lib = new Librairie(jt);			
			md = new FModelisation();
			Menu m =new Menu(md,lib);
			
				
			
			jt.addChangeListener(new MyTabListener(m,jt));
			
			//this.setLayout(new BorderLayout());
			
			
			
			
			
			
			//this.add(jt,BorderLayout.CENTER);			
			this.add(m, BorderLayout.NORTH);
			sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lib, jt);
			sp.resetToPreferredSizes();
		
			this.add(sp,BorderLayout.CENTER);
			
			
			
			this.addKeyListener(new MyKeyboardListener(lib,sp));
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) screenSize.getWidth();
			int height = (int) screenSize.getHeight();
			this.setSize(width-height/2,height-width/8);
			
			this.setLocationRelativeTo(null);
			this.setResizable(true);
			this.setVisible(true);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.requestFocus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
