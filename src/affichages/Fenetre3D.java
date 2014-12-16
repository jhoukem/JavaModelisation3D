package affichages;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import listener_package.MyButtonAjoutListener;
import listener_package.MyButtonDeleteListener;
import listener_package.MyKeyboardListener;
import listener_package.MyTabListener;



public class Fenetre3D extends JFrame{

	JTabbedPaneWithCloseIcons jt;
	JSplitPane sp;
	public Fenetre3D() {
		super("3D Project");
		
		try {
			JPanel p= new JPanel();
			JPanel inP=new JPanel();
			p.setLayout(new BorderLayout());
			inP.setLayout(new BorderLayout());
			
			jt = new JTabbedPaneWithCloseIcons();
			Librairie lib = new Librairie(jt);			
			FModelisation md = new FModelisation();
			Outils m =new Outils(md,lib);
			JMenuBar menu = new JMenuBar();
			JMenu fichier = new JMenu("Fichier");
			JMenu aide = new JMenu("Aide");
			JMenuItem add = new JMenuItem("Ajouter un fichier gts");
			add.addActionListener(new MyButtonAjoutListener(lib));
			JMenuItem rm = new JMenuItem("Supprimer un fichier gts");
			rm.addActionListener(new MyButtonDeleteListener(lib));
			JMenuItem rc = new JMenuItem("Ctrl +H pour afficher/masquer la librairie");
			fichier.add(add);
			fichier.add(rm);
			aide.add(rc);
			
			menu.add(fichier);
			menu.add(aide);
			
			
			jt.addChangeListener(new MyTabListener(m,jt));
			//this.add(jt,BorderLayout.CENTER);			
			//this.add(m, BorderLayout.NORTH);
			sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lib, jt);
			sp.resetToPreferredSizes();
			
			inP.add(lib, BorderLayout.WEST);
			inP.add(m, BorderLayout.NORTH);
			inP.add(sp, BorderLayout.CENTER);
			
			p.add(menu,BorderLayout.NORTH);
			p.add(inP,BorderLayout.CENTER);
			
			this.add(p);
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
			this.addFocusListener(new FocusListener() {				
				@Override
				public void focusLost(FocusEvent e) {
					requestFocus();					
				}
				@Override
				public void focusGained(FocusEvent arg0) {	
				}
			});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
