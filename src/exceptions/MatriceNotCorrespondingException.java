package exceptions;

@SuppressWarnings("serial")
public class MatriceNotCorrespondingException extends Exception{

	public MatriceNotCorrespondingException() {
		super("Les tailles des matrices ne correspondent pas !");
	}
}
