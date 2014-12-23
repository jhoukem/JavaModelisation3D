
package affichages;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.List;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import listener_package.MyListSelectionListener;
import sqlite.GtsBase;
import sqlite.SelectGts;
import exceptions.SegmentException;


public class Librairie extends JPanel {

	private static final long serialVersionUID = 1L;
	String tab[] ; 
	JTree tree;	
	JLabel t;
	GtsBase maBase;
	JScrollPane jsp;
	JTabbedPaneWithCloseIcons jt;
	//DefaultMutableTreeNode bdd ;

	public Librairie( JTabbedPaneWithCloseIcons j){	
		this.jt=j;
		init();	// permet d'obtenir la liste de base et d'instancier les composants	
	}

	public void init(){
		maBase = new GtsBase();
		this.setToolTipText("La Librairie permet de choisir un fichier à visualiser");
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(new Color(36,66,124));
		t = new JLabel("Librairie");
		t.setForeground(Color.white);
		this.add(t);
		tree = new JTree(buildTree());
		this.jsp = new JScrollPane(tree); 
		this.add(jsp);
	}	

	private  TreeNode buildTree() {//ajoute tout les composants presents dans la bdd a la racine
		try {
			maBase.open();
			tab = maBase.getList(maBase.executeQry("select * from FichiersGts"));
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		finally{
			maBase.close();	
		}		

		DefaultMutableTreeNode bdd = new DefaultMutableTreeNode("Base de donnees");
		for(int i = 0; i < tab.length; i++)
			bdd.add(new DefaultMutableTreeNode(tab[i]));
		return bdd;
	}

	public void majTree(){	
			((DefaultTreeModel) tree.getModel()).setRoot(buildTree());//on change le TreeNode root
			((DefaultTreeModel) tree.getModel()).reload();		//on reaffiche
	}


	public JTabbedPaneWithCloseIcons getJt() {
		return jt;
	}

}
