package affichages;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

public class Progression {
	 
    JProgressBar progress;
    int rappidite;
    JFrame cadre;
 
    public void go() {
       // rappidite = rappid;
    	progress = new JProgressBar(0, 100);
        progress.setIndeterminate(true);
        // Cr�ation de l'interface
        cadre = new JFrame("Chargement");
        JPanel panneau = new JPanel();
        
        panneau.add("Center", progress);
        cadre.add(BorderLayout.CENTER, panneau);
        cadre.setSize(230, 53);
        //cadre.pack();
        cadre.setLocationRelativeTo(null);
        cadre.setVisible(true);
        cadre.setResizable(false);
        cadre.setAlwaysOnTop(true);
        cadre.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE );
 
    }
 
    public void end(){
        cadre.dispose();
    }
    
}