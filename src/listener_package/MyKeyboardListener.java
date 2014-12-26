package listener_package;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSplitPane;

import affichages.Descripteur;
import affichages.Librairie;

public class MyKeyboardListener implements KeyListener {
	private boolean isCtrl = false;
	private Librairie lib;
	private JSplitPane jp;
	Descripteur des;
	public MyKeyboardListener(Librairie l, JSplitPane s, Descripteur d){
		this.lib = l;
		this.jp = s;
		this.des = d;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==17)
				isCtrl=true;				
		if(isCtrl){
			if(e.getKeyCode()==76){
				if(!lib.isVisible()){
				lib.setVisible(true);		
				lib.revalidate();
				jp.resetToPreferredSizes();
				}
				else
					lib.setVisible(false);
			}	
			
			else if(e.getKeyCode()==68){
				if(des.isVisible())
					des.setVisible(false);
				else
					des.setVisible(true);
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==17)
			isCtrl=false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
