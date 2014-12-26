package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import listener_package.MyButtonFacesListener;
import listener_package.MyButtonPointsListener;
import listener_package.MyButtonResetListener;
import listener_package.MyButtonRotListener;
import listener_package.MyButtonSegmentsListener;
import listener_package.MyButtonTransListener;
import listener_package.MyTreeSelectionListener;

public class Outils extends JToolBar{

	private static final long serialVersionUID = 1L;

	JButton rot;
	JButton trans;
	JButton reset;
	public JButton point;
	public JButton segment;
	public JButton face;
	
	JTabbedPaneWithCloseIcons j;
	FModelisation fM ;

	private Librairie lib;
	public Outils(FModelisation f ,Librairie l, JTabbedPaneWithCloseIcons jt){
		this.fM=f;
		this.j=jt;
		this.setLib(l);
		//lib.l.addListSelectionListener(new MyListSelectionListener(l.l, l.jt,this));
		lib.tree.addTreeSelectionListener(new MyTreeSelectionListener(lib.tree, jt, this));
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
		this.setBackground(new Color(36,66,124));
		Icon img = new ImageIcon(this.getClass().getResource("t.png"));
		ImageIcon tl = new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
		img = new ImageIcon(this.getClass().getResource("r.png"));
		ImageIcon rt = new ImageIcon(((ImageIcon) img).getImage().getScaledInstance(16, 16, Image.SCALE_AREA_AVERAGING));
		
		segment = new JButton("Segments");
		face = new JButton("Face");
		point = new JButton("Point");
		rot = new JButton(rt);
		trans = new JButton(tl);
		reset = new JButton("Reset");
		enableBoutons();
		//addListener(f,jt);


		this.add(rot);
		this.add(trans);
		this.add(reset);
		this.add(face);
		this.add(segment);
		this.add(point);

	}

	public void addListener(FModelisation f, JTabbedPaneWithCloseIcons jt){
		if(f.getFichier()!=null)	{	// si c'est la premiere FM qui est vide inutile de l'init	
			reset.addActionListener(new MyButtonResetListener(jt));
	/*
			if(f.isRot()){
				rot.setEnabled(false);
				trans.setEnabled(true);
			}
			else{
				rot.setEnabled(true);
				trans.setEnabled(false);
			}*/
		trans.addActionListener(new MyButtonTransListener(trans,rot,jt));
		rot.addActionListener(new MyButtonRotListener(trans,rot,jt));
		point.addActionListener(new MyButtonPointsListener(jt,this));
		segment.addActionListener(new MyButtonSegmentsListener(jt,this));
		face.addActionListener(new MyButtonFacesListener(jt,this));
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

	public void enableBoutons() {		
		reset.setEnabled(false);
		point.setEnabled(false);
		segment.setEnabled(false);
		face.setEnabled(false);	
		rot.setEnabled(false);
		trans.setEnabled(false);
		
	}
	
	public void MajButtons(){
		if(((FModelisation)j.getSelectedComponent()).isRot()){
			this.rot.setEnabled(false);
			this.trans.setEnabled(true);
		}
		else{
			this.rot.setEnabled(true);
			this.trans.setEnabled(false);
		}
			
		if(((FModelisation)j.getSelectedComponent()).getOpt() == 1){
			this.face.setEnabled(false);
			this.segment.setEnabled(true);
			this.point.setEnabled(true);
		}
		else if(((FModelisation)j.getSelectedComponent()).getOpt() == 2){
			this.face.setEnabled(true);
			this.segment.setEnabled(false);
			this.point.setEnabled(true);
		}
		else {
			this.face.setEnabled(true);
			this.segment.setEnabled(true);
			this.point.setEnabled(false);
		}
		this.reset.setEnabled(true);
		
		
		
	}
}
