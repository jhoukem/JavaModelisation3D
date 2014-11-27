package exceptions;

@SuppressWarnings("serial")
public class VectorException extends Exception {

	public VectorException(){
		super("Le paramètre n'est pas un vecteur !");
	}
}
