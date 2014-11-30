package main_package;

import affichages.Fenetre3D;
import exceptions.SegmentException;

public class Main {

	public static void main(String[] args) throws SegmentException {
		Fenetre3D f = new Fenetre3D("tie.gts");
		f.setSize(1000, 800);
		f.setLocationRelativeTo(null);
		f.setResizable(true);
		f.setVisible(true);
	}
}
