package ca.qc.cgmatane.informatique.devoirintegrite;


import java.io.IOException;

import javafx.application.Application;
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

    public static void main(String[] args) {
        launch(args);
    }
}
