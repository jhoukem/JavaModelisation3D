package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import affichages.Librairie;

public class MyButtonRcheListener implements ActionListener {

	Librairie lib;
	public MyButtonRcheListener(Librairie l) {
		this.lib = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("rche");
	}

}
