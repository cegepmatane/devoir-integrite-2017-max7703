package ca.qc.cgmatane.informatique.devoirintegrite.controler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.SQLException;

import ca.qc.cgmatane.informatique.devoirintegrite.accesseur.PersonneDAO;
import ca.qc.cgmatane.informatique.devoirintegrite.accesseur.TodoDAO;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Personne;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;

public class TodoControler {

    @FXML
    private TextField idTodoTexte;
    @FXML
    private TextField nouveauTitre;
    @FXML
    private TextField nouvelleDescription;
    @FXML
    private TextField nouvelleIdPersonne;
    @FXML
    private TextField nouvelleDate;
    @FXML
    private TextField titreTexte;
    @FXML
    private TextField descriptionTexte;
    @FXML
    private TextField idPersonneTexte;
    @FXML
    private TextField idPersonneTodoTexte;
    @FXML
    private TextArea personneTodo;
    @FXML
    private TextField dateTexte;
	@SuppressWarnings("rawtypes")
	@FXML
    private TableView todoTable;
    @FXML
    private TableColumn<Todo, Integer>  idColonne;
    @FXML
    private TableColumn<Todo, String>  titreColonne;
    @FXML
    private TableColumn<Todo, String> descriptionColonne;
    @FXML
    private TableColumn<Todo, Integer> idPersonneColonne;
    @FXML
    private TableColumn<Todo, String> dateColonne;
    @FXML
    private TableColumn<Todo, String> finiColonne;
    
    @FXML
    private void initialize () 
    {
        idColonne.setCellValueFactory(cellData -> cellData.getValue().todoIdProperty().asObject());
        titreColonne.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        descriptionColonne.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        idPersonneColonne.setCellValueFactory(cellData -> cellData.getValue().idPersonneProperty().asObject());
        dateColonne.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        finiColonne.setCellValueFactory(cellData -> cellData.getValue().finiProperty());
    }

    @FXML
    private void lireTodo (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
    	Todo todo = TodoDAO.lireTodo(Integer.parseInt(idTodoTexte.getText()));
        ajouterTodoDansListe(todo);
    }

    @FXML
    private void listerTousLesTodos(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	ObservableList<Todo> donnee = TodoDAO.listerTousLesTodos();
        ajouterTodoTable(donnee);
    }

    @SuppressWarnings("unchecked")
	@FXML
    private void ajouterTodoDansListe (Todo todo) throws ClassNotFoundException {
        ObservableList<Todo> donnee = FXCollections.observableArrayList();
        donnee.add(todo);
        todoTable.setItems(donnee);
    }

    @SuppressWarnings("unchecked")
	@FXML
    private void ajouterTodoTable (ObservableList<Todo> donnee) throws ClassNotFoundException {
        todoTable.setItems(donnee);
    }

    @FXML
    private void modifierTodo (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    	TodoDAO.modifierTodo(Integer.parseInt(idTodoTexte.getText()),nouveauTitre.getText(), nouvelleDescription.getText(), nouvelleDate.getText(), Integer.parseInt(nouvelleIdPersonne.getText()));
    }

    @FXML
    private void ajouterTodo (ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
    	TodoDAO.ajouterTodo(titreTexte.getText(),descriptionTexte.getText(), dateTexte.getText(), Integer.parseInt(idPersonneTexte.getText()));
    }

    @FXML
    private void effacerTodo (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        TodoDAO.effacerTodo(Integer.parseInt(idTodoTexte.getText()));
    }
    @FXML
    private void listerPersonneTodo (ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        personneTodo.setText(PersonneDAO.listerPersonneTodo(Integer.parseInt(idPersonneTodoTexte.getText())));
    }
}
