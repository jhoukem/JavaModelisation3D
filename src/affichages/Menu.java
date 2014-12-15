package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;









import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;
import listener_package.MyButtonAjoutListener;
import listener_package.MyButtonFacesListener;
import listener_package.MyButtonPointsListener;
import listener_package.MyButtonResetListener;
import listener_package.MyButtonRotListener;
import listener_package.MyButtonSegmentsListener;
import listener_package.MyButtonTransListener;
import listener_package.MyListSelectionListener;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;

	JButton ajout;
	JButton rot;
	JButton trans;
	JButton reset;
	public JButton point;
	public JButton segment;
	public JButton face;

	JFileChooser fc;
	FModelisation fM ;

	private Librairie lib;
	public Menu(FModelisation f ,Librairie l){
		this.fM=f;
		this.setLib(l);
		lib.l.addListSelectionListener(new MyListSelectionListener(l.l, l.jt,this));
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
		this.setBackground(new Color(36,66,124));
		Icon img = new ImageIcon(this.getClass().getResource("c.png"));
		//ImageIcon = Iimg.
		//System.out.println(img.getImageLoadStatus());
		ImageIcon resultat = new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING));
		ajout = new JButton("Ajouter");
		segment = new JButton("Segments");
		face = new JButton("Face");
		point = new JButton("Point");
		rot = new JButton("Rotation");
		trans = new JButton(resultat);
		reset = new JButton("Reset");
		//trans.setIcon(resultat);
		enableBoutons(false,true);

		ajout.addActionListener(new MyButtonAjoutListener(getLib()));
		addListener(f);


		this.add(ajout);
		this.add(rot);
		this.add(trans);
		this.add(reset);
		this.add(face);
		this.add(segment);
		this.add(point);

	}

	public void addListener(FModelisation f){
		if(f.getFichier()!=null)	{	// si c'est la premiere FM qui est vide inutile de l'init	
			reset.addActionListener(new MyButtonResetListener(f));
	
			if(f.isRot()){
				rot.setEnabled(false);
				trans.setEnabled(true);
			}
			else{
				rot.setEnabled(true);
				trans.setEnabled(false);
			}
		trans.addActionListener(new MyButtonTransListener(trans,rot,f));
		rot.addActionListener(new MyButtonRotListener(trans,rot,f));
		point.addActionListener(new MyButtonPointsListener(f,this));
		segment.addActionListener(new MyButtonSegmentsListener(f,this));
		face.addActionListener(new MyButtonFacesListener(f,this));
		}
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

	public void enableBoutons(boolean b, boolean c) {		
		reset.setEnabled(b);
		point.setEnabled(b);
		segment.setEnabled(b);
		
		if(c){//permet de ne pas mettre certains boutons à jour
			face.setEnabled(b);	
			rot.setEnabled(b);
			trans.setEnabled(b);
		}
	}
}
