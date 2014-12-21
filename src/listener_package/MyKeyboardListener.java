package listener_package;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSplitPane;

import affichages.Librairie;

public class MyKeyboardListener implements KeyListener {
	private boolean isCtrl = false;
	private boolean isHide = false;
	private Librairie lib;
	private JSplitPane jp;
	public MyKeyboardListener(Librairie l, JSplitPane s){
		this.lib=l;
		this.jp=s;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==17)
				isCtrl=true;				
		if(isCtrl){
			if(e.getKeyCode()==72){
				if(isHide){
				lib.show();		
				lib.revalidate();
				jp.resetToPreferredSizes();
				isHide=false;
				}
				else{
					lib.hide();
					isHide=true;				
				}
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
