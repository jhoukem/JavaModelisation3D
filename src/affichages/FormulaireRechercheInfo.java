package affichages;


public class FormulaireRechercheInfo {
	private String motClef, nbrFace, nbrSegment, nbrPoint, selection;
	
	public String getSelection() {
		return selection;
	}
	public FormulaireRechercheInfo(){}
	public FormulaireRechercheInfo(String motClef, String nbrFace, String nbrSegment, String nbrPoint,String s){
		this.nbrFace = nbrFace;
		this.nbrSegment = nbrSegment;
		this.motClef = motClef;
		this.nbrPoint = nbrPoint;
		this.selection = s;
	}
	
	/*public String toString(){
		String str;
		if(this.motClef != null ){
			str = "Recherche : " + this.titre + "\n";
			str +="Description: " + this.description + "\n";
			str +="MotClef: "+ this.motClef + "\n";
			str +="Couleur: "+ this.couleur + "\n";
		}else{
			str = "Il manque des informations !";
			
		}
		return str;
	}
	*/
	public String getMotClef(){
		return motClef;
	}
	
	public String getNbrFace(){
		return nbrFace;
	}
	
	public String getNbrSegment(){
		return nbrSegment;
	}
	
	public String getNbrPoint(){
		return nbrPoint;
	}
	
}