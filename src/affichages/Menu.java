package affichages;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.multi.MultiButtonUI;

import exceptions.MatriceNotCorrespondingException;
import exceptions.SegmentException;
import exceptions.VectorException;
import listener_package.MyButtonRotListener;
import listener_package.MyButtonTransListener;

public class Menu extends JPanel{

	private static final long serialVersionUID = 1L;

	JButton open;
	JButton rot;
	JButton trans;
	JButton reset;
	FModelisation fM ;
	public Menu(FModelisation f){
		this.fM=f;
	this.setLayout(new FlowLayout(FlowLayout.LEFT, 4, 4));
	this.setBackground(Color.LIGHT_GRAY);

	ImageIcon img = new ImageIcon("rotation.png");
	ImageIcon resultat = new ImageIcon(img.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
	open = new JButton("Ouvrir");
	
	rot = new JButton("Rotation",resultat);
	trans = new JButton("Translation");
	reset = new JButton("Reset");
	//rot.disable();
	rot.setEnabled(false);
	reset.addActionListener(new ActionListener() {
		FModelisation fM = Menu.this.fM;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
				try {
					fM.setGts(new GtsReader(fM.getFichier()));
				} catch (SegmentException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				fM.setZoom(10);
				fM.setInfos(fM.getGts().getInfos());
				fM.setNumsgmts(fM.getGts().getNumsgmts());
				fM.setNumfces(fM.getGts().getNumfces());
				fM.setPts(fM.getGts().getPoints());
				fM.setSgmts(fM.getGts().getSegments());
				fM.setFces(fM.getGts().getFaces());
				try {
					fM.setTranslation(fM.getVectorCenter());
				} catch (VectorException | MatriceNotCorrespondingException
						| SegmentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				fM.repaint();
			
				fM.setxSize(fM.getWidth());	
				fM.setySize(fM.getHeight());	
		
			
		}
	});
	trans.addActionListener(new MyButtonTransListener(trans,rot,f));
	rot.addActionListener(new MyButtonRotListener(trans,rot,f));
	
	
	
	this.add(rot);
	this.add(trans);
	this.add(reset);
	

	}
}
