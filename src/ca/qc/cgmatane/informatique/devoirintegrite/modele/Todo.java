package ca.qc.cgmatane.informatique.devoirintegrite.modele;

import javafx.beans.property.*;

public class Todo {
    //Declaration
	protected IntegerProperty id_todo;
	protected StringProperty titre;
	protected StringProperty description;
	protected IntegerProperty id_personne;
	protected StringProperty date;
	protected StringProperty fini;

    //Constructeur
    public Todo() {
        this.id_todo = new SimpleIntegerProperty();
        this.titre = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.id_personne = new SimpleIntegerProperty();
        this.date = new SimpleStringProperty();
        this.fini = new SimpleStringProperty();
    }

    //id_todo
    public int getTodoId() {
        return id_todo.get();
    }

    public void setTodoId(int id_todo){
        this.id_todo.set(id_todo);
    }

    public IntegerProperty todoIdProperty(){
        return id_todo;
    }

    //titre
    public String getTitre () {
        return titre.get();
    }

    public void setTitre(String titre){
        this.titre.set(titre);
    }

    public StringProperty titreProperty() {
        return titre;
    }

    //description
    public String getDescription () {
        return description.get();
    }

    public void setDescription(String description){
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    //id_personne
    public int getIdPersonne () {
        return id_personne.get();
    }

    public void setIdPersonne (int id_personne){
        this.id_personne.set(id_personne);
    }

    public IntegerProperty idPersonneProperty() {
        return id_personne;
    }

    //date
    public String getDate () {
        return date.get();
    }

    public void setDate (String date){
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    //fini
    public String getFini(){
        return fini.get();
    }

    public void setFini(String fini){
        this.fini.set(fini);
    }

    public StringProperty finiProperty() {
        return fini;
    }
}
