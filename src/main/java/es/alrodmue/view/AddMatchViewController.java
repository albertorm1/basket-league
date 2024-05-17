package es.alrodmue.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.alrodmue.App;
import es.alrodmue.controller.MatchController;
import es.alrodmue.model.matches.MatchType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

/**
 * Controlador de la vista de añadir jugador.
 * @author Alberto Rodriguez Muelas
 */
public class AddMatchViewController implements Initializable {
    
    // Obtención de objetos
    @FXML private ChoiceBox<MatchType> matchType;
    @FXML private Button acceptButton, cancelButton;

    /**
     * Inicialización de la vista.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        acceptButton.disableProperty().bind(matchType.getSelectionModel().selectedItemProperty().isNull());
        matchType.setItems(FXCollections.observableArrayList(MatchType.values()));
    }

    /**
     * Método que se ejecutará cuando se pulsa el botón de cancelar.
     */
    @FXML
    private void onCancelButtonClick() {
        App.closeModal();
    }

    /**
     * Método que se ejecutará cuando se pulsa el botón de aceptar.
     */
    @FXML
    private void onAcceptButtonClick() {
        MatchController controller = MatchController.getInstance();
        controller.playMatch(matchType.getValue());
    }
}
