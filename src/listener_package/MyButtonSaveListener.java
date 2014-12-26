package listener_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import affichages.Librairie;

public class MyButtonSaveListener implements ActionListener {

		Librairie lib;
	public MyButtonSaveListener(Librairie l) {
		this.lib = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		chooser.setApproveButtonText("Importer");
		chooser.setApproveButtonToolTipText("Importe l'élément sélectionné dans la base de données");
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal != JFileChooser.CANCEL_OPTION) {	
			System.out.println();
		}

	}
	
}
