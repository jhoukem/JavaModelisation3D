package tests;
import static org.junit.Assert.assertEquals;
import maths_package.Matrice;

import org.junit.Test;

import exceptions.MatriceNotCorrespondingException;

public class MatriceTest {

	@Test
	public void testToString() {
		Matrice res = new Matrice(3,3);
		assertEquals(res.toString()," 0.0 0.0 0.0\n 0.0 0.0 0.0\n 0.0 0.0 0.0\n");
	}
	
	@Test
	public void testAdd() throws MatriceNotCorrespondingException{
		Matrice m1 = new Matrice(3,3);
		Matrice m2 = new Matrice(3,3);
		Matrice m3 = new Matrice(2,2);
		double[][] tab = {{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}};
		double[][] tab1 = {{1.0,1.0},{1.0,1.0}};
		m1.setElem(tab);
		m2.setElem(tab);
		m3.setElem(tab1);
		assertEquals(Matrice.add(m1, m2).toString(), " 2.0 2.0 2.0\n 2.0 2.0 2.0\n 2.0 2.0 2.0\n");
		}
	
	@Test
	public void testMultiplier() throws MatriceNotCorrespondingException{
		Matrice m1 = new Matrice(3,3);
		Matrice m2 = new Matrice(3,3);
		double[][] tab = {{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}};
		double[][] tab1 = {{1.0,2.0,3.0},{1.0,2.0,3.0},{1.0,2.0,3.0}};
		m1.setElem(tab);
		m2.setElem(tab1);
		assertEquals(Matrice.multiplier(m1, m2).toString(), " 3.0 6.0 9.0\n 3.0 6.0 9.0\n 3.0 6.0 9.0\n");	
		
	}
	
	@Test
	public void testMultiplierParReel() throws MatriceNotCorrespondingException {
		Matrice m1 = new Matrice(3,3);
		double[][] tab = {{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}};
		m1.setElem(tab);
		assertEquals(Matrice.multiplierParReel(m1,3).toString(), " 3.0 3.0 3.0\n 3.0 3.0 3.0\n 3.0 3.0 3.0\n");
	}

}
