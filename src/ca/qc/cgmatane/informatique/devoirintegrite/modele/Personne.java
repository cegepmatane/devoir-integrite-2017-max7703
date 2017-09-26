package ca.qc.cgmatane.informatique.devoirintegrite.modele;

public class Personne {
	protected int id_personnes;
	protected String nom;
	protected String prenom;
	protected int nombre_de_todo_fait;
	
	public int getId_personnes() {
		return id_personnes;
	}
	public void setId_personnes(int id_personnes) {
		this.id_personnes = id_personnes;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getNombre_de_todo_fait() {
		return nombre_de_todo_fait;
	}
	public void setNombre_de_todo_fait(int nombre_de_todo_fait) {
		this.nombre_de_todo_fait = nombre_de_todo_fait;
	}
	
	
}
