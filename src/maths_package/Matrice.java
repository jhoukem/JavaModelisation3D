package maths_package;

import exceptions.MatriceNotCorrespondingException;

public class Matrice {

	private int nColonnes;
	private int nLignes;
	private double[][] elem;
	
	public Matrice(int c, int l) {
		this.nColonnes = c;
		this.nLignes = l;
		elem = new double[nColonnes][nLignes];
		for (int i = 0; i < nColonnes; i++) {
			for (int j = 0; j < nLignes; j++) {
				elem[nColonnes - 1][nLignes - 1] = 0.0;
			}
		}
	}

	//renvoie la largeur d'une matrice
	public int getnColonnes() {
		return nColonnes;
	}

	//renvoie la hauteur d'une matrice
	public int getnLignes() {
		return nLignes;
	}

	//renvoie les valeurs de la matrice
	public double[][] getElem() {
		return elem;
	}

	//permet d'instancier une matrice avec un tableau de double
	public void setElem(double[][] elem) throws MatriceNotCorrespondingException {
		if(elem[0].length != this.nColonnes && elem[1].length != this.nLignes)
			throw new MatriceNotCorrespondingException();
		this.elem = elem;
	}

	//permet d'insérer une valeur dans uen case de la matrice 
	public void putElem(int c, int l, double x) {
		this.elem[c][l] = x;
	}

	//affiche les donnees de la matrice
	public String toString() {

		String res;
		res = "";
		for (int i = 0; i < nColonnes; i++) {
			for (int j = 0; j < nLignes; j++) {
				res += " " + elem[i][j];
			}
			res += "\n";
		}

		return res;
	}

	// permet de multiplier 2 matrices
	public static Matrice multiplier(Matrice m1, Matrice m2)
			throws MatriceNotCorrespondingException {
		if (m1.nLignes != m2.nColonnes)
			throw new MatriceNotCorrespondingException();
		Matrice res = new Matrice(m1.nColonnes, m2.nLignes);
		double somme;
		for (int i = 0; i < m1.nColonnes; i++) {
			for (int j = 0; j < m2.nLignes; j++) {
				somme = 0.0;
				for (int k = 0; k < m1.nLignes; k++) {
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
		if ((m1.nColonnes != m2.nColonnes) || (m1.nLignes != m2.nLignes))
			throw new MatriceNotCorrespondingException();
		Matrice res = new Matrice(m1.nColonnes, m1.nLignes);
		for (int i = 0; i < m1.nColonnes; i++) {
			for (int j = 0; j < m1.nLignes; j++) {
				res.elem[i][j] += m1.elem[i][j] + m2.elem[i][j];
			}
		}
		return res;
	}

	// permet de multiplier une matrice par un réel
	public static Matrice multiplierParReel(Matrice m1, int x) {
		Matrice res = new Matrice(m1.nColonnes, m1.nLignes);
		for (int i = 0; i < m1.nColonnes; i++) {
			for (int j = 0; j < m1.nLignes; j++) {
				res.elem[i][j] = m1.elem[i][j] * x;
			}
		}
		return res;
	}
}