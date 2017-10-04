package ca.qc.cgmatane.informatique.devoirintegrite;


import java.io.IOException;
import java.sql.SQLException;

import ca.qc.cgmatane.informatique.devoirintegrite.accesseur.PersonneDAO;
import ca.qc.cgmatane.informatique.devoirintegrite.accesseur.TodoDAO;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Personne;
import ca.qc.cgmatane.informatique.devoirintegrite.modele.Todo;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DevoirIntegrite extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("Devoir Integrité");

        initialisationLayout();
    }

    public void initialisationLayout() throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DevoirIntegrite.class.getResource("vue/nouveaulayout.fxml"));
        AnchorPane todoVuePrincipal = (AnchorPane) loader.load();

        Scene scene = new Scene(todoVuePrincipal, 900, 510); 
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        launch(args);
        
        //TEST donnée liée
        ObservableList<Personne> listePersonne = PersonneDAO.listerToutesLesPersonnes();
        ObservableList<Todo> listeTodo = TodoDAO.listerTousLesTodos();
        for(Personne pers : listePersonne)
        {
        	System.out.println("\nPersonne : " + pers.getPersonneNom());
            for(Todo test : listeTodo)
            {
            	if(test.getIdPersonne() == pers.getPersonneId())
            	{
            		System.out.println("Todo: " + test.getTitre());
            	}
            }
        }
        	

    }
    
}
