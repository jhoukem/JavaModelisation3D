package affichages;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import listener_package.MyButtonAjoutListener;
import listener_package.MyButtonDeleteListener;
import listener_package.MyButtonRcheListener;
import listener_package.MyButtonSaveListener;
import listener_package.MyKeyboardListener;
import listener_package.MyMenuAffichageListener;
import listener_package.MyTabListener;



public class Fenetre3D extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTabbedPaneWithCloseIcons jt;
	public JTabbedPaneWithCloseIcons getJt() {
		return jt;
	}
	public void setJt(JTabbedPaneWithCloseIcons jt) {
		this.jt = jt;
	}
	JSplitPane sp;
	public Fenetre3D() {
		super("3D Project");
		
		try {
			JPanel p= new JPanel();
			JPanel inP=new JPanel();
			JPanel mode = new JPanel();
			p.setLayout(new BorderLayout());
			inP.setLayout(new BorderLayout());
			
			jt = new JTabbedPaneWithCloseIcons();
			Librairie lib = new Librairie(jt);	
			Descripteur des = new Descripteur();
			FModelisation md = new FModelisation();
			Outils m = new Outils(md,lib,jt,des,this);
			
			JMenuBar menu = new JMenuBar();
			JMenu fichier = new JMenu("Fichier");
			JMenu affichage = new JMenu("Affichage");
			JMenuItem add = new JMenuItem("Ajouter un fichier gts");
			add.addActionListener(new MyButtonAjoutListener(lib,this));
			JMenuItem rm = new JMenuItem("Supprimer un fichier gts");
			rm.addActionListener(new MyButtonDeleteListener(lib));
			JMenuItem rche = new JMenuItem("Rechercher un fichier gts");
			rche.addActionListener(new MyButtonRcheListener(lib,this));
			JMenuItem save = new JMenuItem("Sauvegarder un fichier gts");
			save.addActionListener(new MyButtonSaveListener(lib));
			JMenuItem rc1 = new JMenuItem("Afficher/Masquer la librairie     Ctrl+L");
			
			JMenuItem rc2 = new JMenuItem("Afficher/Masquer la description   Ctrl+D");
			
			
			fichier.add(add);
			fichier.add(rm);
			
			fichier.add(rche);
			fichier.add(save);
			affichage.add(rc1);
			affichage.add(rc2);
			menu.add(fichier);
			menu.add(affichage);
			
			
			jt.addChangeListener(new MyTabListener(m,jt,des));
			//this.add(jt,BorderLayout.CENTER);			
			//this.add(m, BorderLayout.NORTH);
			mode.setLayout(new BorderLayout());
			mode.add(jt,BorderLayout.CENTER);
			mode.add(des,BorderLayout.SOUTH);
			sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lib, mode);
			sp.resetToPreferredSizes();	
			rc1.addActionListener(new MyMenuAffichageListener(lib,sp,des));
			rc2.addActionListener(new MyMenuAffichageListener(lib,sp,des));
			
			inP.add(m, BorderLayout.NORTH);
			inP.add(sp, BorderLayout.CENTER);
			
			p.add(menu,BorderLayout.NORTH);
			p.add(inP,BorderLayout.CENTER);
			
			this.add(p);
			this.addKeyListener(new MyKeyboardListener(lib,sp,des));
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
