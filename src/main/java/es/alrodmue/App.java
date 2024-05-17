package es.alrodmue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import es.alrodmue.controller.PlayerController;
import es.alrodmue.model.Team;
import es.alrodmue.model.factories.PlayerFactory;
import es.alrodmue.model.players.Player;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage modalStage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void showModal(String fxml, String title) throws IOException {
        modalStage = new Stage();
        Scene scene = new Scene(loadFXML(fxml));
        modalStage.setScene(scene);
        modalStage.setTitle(title);
        modalStage.setResizable(false);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.show();
    }

    public static void closeModal() {
        modalStage.close();
        modalStage = null;
    }

    public static void main(String[] args) throws Exception {
        // Test data
        String[] names = {"Juan Perez", "Pedro Martinez", "Luis Lopez", "Jose Ramirez", "Javier Gomez", "Alberto Ortiz", "Daniel Hernandez", "Carlos Morales", "Francisco Ruiz", "Miguel Torres", "Andres Fernandez", "Rafael Sanchez", "Antonio Rodriguez", "Diego Gonzalez", "Victor Jimenez"}; 
        int[] heights = {182, 156, 162, 204, 167, 166, 244, 250, 201, 203, 247, 250, 250, 233, 250};
        int[] skills = {5, 4, 5, 4, 3, 3, 4, 5, 2, 1, 2, 3, 2, 1, 2};
        Player player;

        for (int i = 0; i < 15; i++) {
            player = PlayerFactory.getInstance().create(names[i], heights[i], skills[i]);
            Team.getInstance().addPlayer(player);
        }
        launch();
    }

}