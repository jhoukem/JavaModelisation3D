package exceptions;

@SuppressWarnings("serial")
public class SegmentException extends Exception {

	public SegmentException(){
		super("Les points sont identiques !");
	}
}
