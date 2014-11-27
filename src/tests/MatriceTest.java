package tests;
import static org.junit.Assert.assertEquals;
import maths_package.Matrice;

import org.junit.Test;

import exceptions.MatriceNotCorrespondingException;
import exceptions.VectorException;

public class MatriceTest {

	@Test
	public void testToString() {
		Matrice res = new Matrice(3,3);
		assertEquals(res.toString()," 0.0 0.0 0.0\n 0.0 0.0 0.0\n 0.0 0.0 0.0\n");
	}
	
	@Test
	public void testAdd() throws MatriceNotCorrespondingException{
		Matrice m1 = new Matrice(new double[][] {{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}});
		Matrice m2 = new Matrice(new double[][] {{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}});
		assertEquals(Matrice.add(m1, m2).toString(), " 2.0 2.0 2.0\n 2.0 2.0 2.0\n 2.0 2.0 2.0\n");
		}
	
	@Test
	public void testMultiplier() throws MatriceNotCorrespondingException{
		Matrice m1 = new Matrice(new double[][]{{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}});
		Matrice m2 = new Matrice(new double[][] {{1.0,2.0,3.0},{1.0,2.0,3.0},{1.0,2.0,3.0}});
		assertEquals(Matrice.multiplier(m1, m2).toString(), " 3.0 6.0 9.0\n 3.0 6.0 9.0\n 3.0 6.0 9.0\n");	
		
	}
	
	@Test
	public void testMultiplierParReel() throws MatriceNotCorrespondingException {
		Matrice m1 = new Matrice(new double[][] {{1.0,1.0,1.0},{1.0,1.0,1.0},{1.0,1.0,1.0}});
		assertEquals(Matrice.multiplierParReel(m1,3).toString(), " 3.0 3.0 3.0\n 3.0 3.0 3.0\n 3.0 3.0 3.0\n");
	}
	
	@Test
	public void testTranslationMatrice() throws VectorException {
		Matrice m1= new Matrice(new double[][] {{1.0},{2.0},{3.0}});
		assertEquals(Matrice.getTranslation(m1).toString()," 1.0 0.0 0.0 1.0\n 0.0 1.0 0.0 2.0\n 0.0 0.0 1.0 3.0\n 0.0 0.0 0.0 1.0\n");
	}
}
