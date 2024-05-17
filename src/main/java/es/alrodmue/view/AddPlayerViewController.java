package es.alrodmue.view;

import java.net.URL;
import java.util.ResourceBundle;

import es.alrodmue.App;
import es.alrodmue.controller.PlayerController;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * Controlador de la vista de añadir jugador.
 * @author Alberto Rodriguez Muelas
 */
public class AddPlayerViewController implements Initializable {
    
    // Obtención de objetos
    @FXML private TextField playerName, playerHeight;
    @FXML private Slider playerSkill;
    @FXML private Button accepButton, cancelButton;

    /**
     * Inicialización de la vista.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        accepButton.disableProperty().bind(Bindings.or(playerName.textProperty().isEmpty(), playerHeight.textProperty().isEmpty()));
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
        PlayerController controller = PlayerController.getInstance();
        String name = playerName.getText();
        String height = playerName.getText();
        int skill = (int) playerSkill.getValue();
        controller.createPlayer(name, height, skill);
    }
}
