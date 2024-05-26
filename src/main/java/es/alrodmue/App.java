package es.alrodmue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import es.alrodmue.controller.PersistenceController;

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
        PersistenceController.getInstance().loadData();
        launch();
    }

}