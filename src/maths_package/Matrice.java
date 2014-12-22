package maths_package;

import exceptions.MatriceNotCorrespondingException;
import exceptions.VectorException;

public class Matrice {

	private int nLignes;
	private int nColonnes;
	private double[][] elem;

	//initilaise une matrice a 0
	public Matrice(int l, int c) {
		this.nLignes = l;
		this.nColonnes = c;
		elem = new double[nLignes][nColonnes];
		for (int i = 0; i < nLignes; i++) {
			for (int j = 0; j < nColonnes; j++) {
				elem[nLignes - 1][nColonnes - 1] = 0.0;
			}
		}
	}
	//initialise une matrice aux valeurs du tableau 
	public Matrice(double[][] tab){
		this.elem=tab;
		this.nLignes=tab.length;
		this.nColonnes=tab[0].length;
	}

	//renvoie la largeur d'une matrice
	public int getnLignes() {
		return nLignes;
	}

	//renvoie la hauteur d'une matrice
	public int getnColonnes() {
		return nColonnes;
	}

	//renvoie les valeurs de la matrice
	public double[][] getElem() {
		return elem;
	}

	//permet d'instancier une matrice avec un tableau de double
	public void setElem(double[][] elem) throws MatriceNotCorrespondingException {
		if(elem.length != this.nLignes && elem[0].length != this.nColonnes)
			throw new MatriceNotCorrespondingException();
		this.elem = elem;
	}

	//permet d'ins�rer une valeur dans une case de la matrice 
	public void setElem(int l, int c, double x) {
		this.elem[l][c] = x;
	}

	//permet de r�cup�rer la valeur d'uen case de la matrice
	public double getElem(int l, int c){
		return this.elem[l][c];
	}

	//affiche les donnees de la matrice
	public String toString() {

		String res="";
		for (int i = 0; i < nLignes; i++) {
			for (int j = 0; j < nColonnes; j++) {
				res += " " + elem[i][j];
			}
			res += "\n";
		}

		return res;
	}

	// permet de multiplier 2 matrices
	public static Matrice multiplier(Matrice m1, Matrice m2)
			throws MatriceNotCorrespondingException {
		if (m1.getnColonnes() != m2.getnLignes())
			throw new MatriceNotCorrespondingException();
		Matrice res = new Matrice(m1.nLignes, m2.nColonnes);
		double somme;
		for (int i = 0; i < m1.nLignes; i++) {
			for (int j = 0; j < m2.nColonnes; j++) {
				somme = 0.0;
				for (int k = 0; k < m1.nColonnes; k++) {
					somme += m1.elem[i][k] * m2.elem[k][j];
				}
				res.elem[i][j] = somme;
			}
		}
		return res;
	}

	// Permet d'additionner 2 matrices
	public static Matrice add(Matrice m1, Matrice m2)
			throws MatriceNotCorrespondingException {
		if ((m1.nLignes != m2.nLignes) || (m1.nColonnes != m2.nColonnes))
			throw new MatriceNotCorrespondingException();
		Matrice res = new Matrice(m1.nLignes, m1.nColonnes);
		for (int i = 0; i < m1.nLignes; i++) {
			for (int j = 0; j < m1.nColonnes; j++) {
				res.elem[i][j] += m1.elem[i][j] + m2.elem[i][j];
			}
		}
		return res;
	}

	// permet de multiplier une matrice par un r�el
	public static Matrice multiplierParReel(Matrice m1, int x) {
		Matrice res = new Matrice(m1.nLignes, m1.nColonnes);
		for (int i = 0; i < m1.nLignes; i++) {
			for (int j = 0; j < m1.nColonnes; j++) {
				res.elem[i][j] = m1.elem[i][j] * x;
			}
		}
		return res;
	}

	//permet d'obtenir une matrice de translation a partir d'un vecteur
	public static Matrice getTranslation(Matrice m1) throws VectorException {
		if ((m1.nLignes != 3) || (m1.nColonnes != 1))
			throw new VectorException();
		Matrice res = new Matrice(4,4);
		for (int i = 0; i < res.getnLignes(); i++) {
			for (int j = 0; j < res.getnColonnes(); j++) {
				if(i==j)
					res.setElem(i, j, 1.0);
			}
		}
		res.setElem(0,res.nColonnes-1,m1.getElem(0, 0));
		res.setElem(1,res.nColonnes-1,m1.getElem(1, 0));
		res.setElem(2,res.nColonnes-1,m1.getElem(2, 0));
		return res;
	}

	//permet d'obtenir une matrice de rotation � partir d'un angle autour des l'axe des x
	public static Matrice getRotationX(double r){
		Matrice res = new Matrice(4,4);
		res.setElem(0, 0, 1.0);
		res.setElem(1, 1, Math.cos(r));
		res.setElem(1, 2, -Math.sin(r));
		res.setElem(2, 1, Math.sin(r));
		res.setElem(2, 2, Math.cos(r));
		res.setElem(3, 3, 1.0);
		return res;
	}

	//permet d'obtenir une matrice de rotation � partir d'un angle autour des l'axe des y
	public static Matrice getRotationY(double r){
		Matrice res = new Matrice(4,4);
		res.setElem(1, 1, 1.0);
		res.setElem(0, 0, Math.cos(r));
		res.setElem(2, 0, -(Math.sin(r)));
		res.setElem(0, 2, Math.sin(r));
		res.setElem(2, 2, Math.cos(r));
		res.setElem(3, 3, 1.0);
		return res;
	}

	//permet d'obtenir une matrice de rotation � partir d'un angle autour des l'axe des z
	public static Matrice getRotationZ(double r){
		Matrice res = new Matrice(4,4);
		res.setElem(2, 2, 1.0);
		res.setElem(0, 0, Math.cos(r));
		res.setElem(0, 1, -Math.sin(r));
		res.setElem(1, 0, Math.sin(r));
		res.setElem(1, 1, Math.cos(r));
		res.setElem(3, 3, 1.0);
		return res;
	}
	
	//permet d'obtenir le produit vectoriel de 2 vecteurs
	public static Matrice getProduitVectoriel(Matrice m1, Matrice m2) throws VectorException{
		if(m1.nLignes != 3 || m1.nColonnes != 1 || m2.nLignes != 3 || m1.nColonnes != 1)
			throw new VectorException();
		Matrice res = new Matrice(3,1);
		res.setElem(0, 0, m1.getElem(1, 0) * m2.getElem(2, 0) - m1.getElem(2, 0) * m2.getElem(1, 0));
		res.setElem(1, 0, m1.getElem(2, 0) * m2.getElem(0, 0) - m1.getElem(0, 0) * m2.getElem(2, 0));
		res.setElem(2, 0, m1.getElem(0, 0) * m2.getElem(1, 0) - m1.getElem(1, 0) * m2.getElem(0, 0));
		return res;
	}
	
	public static double getDet(Matrice m1) throws MatriceNotCorrespondingException{
		if(m1.nLignes!=3 || m1.nColonnes!=3)
			throw new MatriceNotCorrespondingException();
		return m1.getElem(0, 0)*(m1.getElem(2, 2)*m1.getElem(1, 1)-m1.getElem(2, 1)*m1.getElem(1, 2))-m1.getElem(1, 0)*(m1.getElem(2, 2)*m1.getElem(0, 1)-m1.getElem(2, 1)*m1.getElem(0, 2))+m1.getElem(2, 0)*(m1.getElem(1, 2)*m1.getElem(0, 1)-m1.getElem(1, 1)*m1.getElem(0, 2));
		
	}
}