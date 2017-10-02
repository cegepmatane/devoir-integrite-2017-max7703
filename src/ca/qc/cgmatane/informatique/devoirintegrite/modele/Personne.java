package ca.qc.cgmatane.informatique.devoirintegrite.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Personne {
    //Declaration
    protected IntegerProperty id_personne;
    protected StringProperty nom;
    protected StringProperty prenom;
    protected IntegerProperty nombre_de_todo_fait;

	//Constructeur
    public Personne() {
        this.id_personne = new SimpleIntegerProperty();
        this.nom = new SimpleStringProperty();
        this.prenom = new SimpleStringProperty();
        this.nombre_de_todo_fait = new SimpleIntegerProperty();
    }
    
    //id_personne
    public int getPersonneId() {
        return id_personne.get();
    }

    public void setPersonneId(int id_personne){
        this.id_personne.set(id_personne);
    }

    public IntegerProperty personneIdProperty(){
        return id_personne;
    }
    
    //nom
    public String getPersonneNom() {
        return nom.get();
    }

    public void setPersonneNom(String nom){
        this.nom.set(nom);
    }

    public StringProperty personneNomProperty(){
        return nom;
    }
    
    //prenom
    public String getPersonnePrenom() {
        return prenom.get();
    }

    public void setPersonnePrenom(String prenom){
        this.nom.set(prenom);
    }

    public StringProperty personnePrenomProperty(){
        return prenom;
    }
    
    //nombre_de_todo_fait
    public int getPersonneNombreTodoFait() {
        return nombre_de_todo_fait.get();
    }

    public void setPersonneNombreTodoFait(int nombre_de_todo_fait){
        this.nombre_de_todo_fait.set(nombre_de_todo_fait);
    }

    public IntegerProperty personneNombreTodoFaitProperty(){
        return nombre_de_todo_fait;
    }
}
