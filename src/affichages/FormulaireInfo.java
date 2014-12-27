package affichages;


public class FormulaireInfo {
	private String titre, description, motClef, couleur;
	
	public FormulaireInfo(){}
	public FormulaireInfo(String titre, String description, String motClef, String couleur){
		this.titre = titre;
		this.description = description;
		this.motClef = motClef;
		this.couleur = couleur;
	}
	
	public String toString(){
		String str;
		if(this.titre != null && this.description != null && this.motClef != null && this.couleur != null){
			str = "Titre: " + this.titre + "\n";
			str +="Description: " + this.description + "\n";
			str +="MotClef: "+ this.motClef + "\n";
			str +="Couleur: "+ this.couleur + "\n";
		}else{
			str = "Il manque des informations !";
			
		}
		return str;
	}
	
	public String getTitre(){
		return titre;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getMotClef(){
		return motClef;
	}
	
	public String getCouleur(){
		return couleur;
	}
}