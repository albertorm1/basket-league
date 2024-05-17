package es.alrodmue.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import es.alrodmue.App;
import es.alrodmue.controller.MatchController;
import es.alrodmue.controller.PlayerController;
import es.alrodmue.model.Team;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.players.Player;
import es.alrodmue.model.players.PlayerType;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la vista principal
 * @author Alberto Rodriguez Muelas
 */
public class MainViewController implements Initializable {
    
    // Obtener objetos
    @FXML private TableView<Player> playerTable;
    @FXML private TableColumn<Player, String> playerName;
    @FXML private TableColumn<Player, PlayerType> playerPosition;
    @FXML private TableColumn<Player, Integer> playerNumber;
    @FXML private TableView<Match> matchTable;
    @FXML private TableColumn<Match, LocalDate> matchDate;
    @FXML private TableColumn<Match, String> matchType, matchWinner;
    @FXML private ChoiceBox<PlayerType> playerTypeSelect;
    @FXML private Button addPlayerButton, viewPlayerButton, deletePlayerButton, playerTypeButton, playMatchButton, viewMatchButton, lastMatchButton;

    // Propiedades
    private Team team = Team.getInstance();
    private ObservableList<Player> playerList = team.getPlayerList();
    private ObservableList<Match> matchList = team.getMatchList();
    private PlayerType[] playerTypes = {PlayerType.POINT_GUARD, PlayerType.SHOOTING_GUARD, PlayerType.SMALL_FORWARD, PlayerType.POWER_FORWARD, PlayerType.CENTER};

    /**
     * Método que se ejecuta automáticamente al cargar la vista.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        // Inicializar tabla de jugadores
        playerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerPosition.setCellValueFactory(new PropertyValueFactory<>("type"));
        playerNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        playerTable.setItems(this.playerList);

        // Inicializar tabla de partidos
        matchDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        matchType.setCellValueFactory(new PropertyValueFactory<>("type"));
        matchWinner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        matchTable.setItems(this.matchList);

        // Inicializar selector de tipo
        playerTypeSelect.setItems(FXCollections.observableArrayList(playerTypes));

        // Objects activation
        playerTypeSelect.disableProperty().bind(Bindings.isEmpty(this.playerList));
        playerTypeButton.disableProperty().bind(Bindings.or(Bindings.isEmpty(this.playerList), playerTypeSelect.getSelectionModel().selectedItemProperty().isNull()));
        lastMatchButton.disableProperty().bind(Bindings.isEmpty(this.matchList));

        viewPlayerButton.disableProperty().bind(playerTable.getSelectionModel().selectedItemProperty().isNull());
        deletePlayerButton.disableProperty().bind(playerTable.getSelectionModel().selectedItemProperty().isNull());
        viewMatchButton.disableProperty().bind(matchTable.getSelectionModel().selectedItemProperty().isNull());

        addPlayerButton.disableProperty().bind(Bindings.lessThanOrEqual(15, Bindings.size(playerList)));
    }

    /**
     * Método que se ejecuta cuando se pulsa en el botón de añadir jugador.
     * @throws IOException
     */
    @FXML
    private void onAddPlayerButtonClick() throws IOException {
        App.showModal("addPlayer", "Crear jugador");
    }

    /**
     * Método que se ejecuta cuando se pulsa en el botón de ver jugador.
     * @throws IOException
     */
    @FXML
    private void onViewPlayerButtonClick() throws IOException {
        PlayerController.getInstance().showPlayerDetails(playerTable.getSelectionModel().getSelectedItem());
    }

    /**
     * Método que se ejecuta cuando se pulsa en el botón de eliminar jugador.
     * @throws IOException
     */
    @FXML
    private void onDeletePlayerButtonClick() throws IOException {
        PlayerController.getInstance().removePlayer(playerTable.getSelectionModel().getSelectedItem());
    }

    /**
     * Método que se ejecuta cuando se pulsa el botón de ver por tipo.
     * @throws IOException
     */
    @FXML
    private void onPlayerTypeButtonClick() throws IOException {
        PlayerController.getInstance().showPlayerDetails(playerTypeSelect.getValue());
    }

    /**
     * Método que se ejecuta cuando se pulsa el botón de jugar partido.
     * @throws IOException
     */
    @FXML
    private void onPlayMatchButtonClick() throws IOException {
        App.showModal("addMatch", "Jugar partido");
    }

    /**
     * Método que se ejecuta cuando se pulsa el botón de ver detalles de un partido.
     * @throws IOException
     */
    @FXML
    private void onViewMatchButtonClick() throws IOException {
        MatchController.getInstance().showMatchDetails(matchTable.getSelectionModel().getSelectedItem());
    }

    /**
     * Método que se ejecuta cuando se pulsa el botón de ver los detalles del último partido.
     * @throws IOException
     */
    @FXML
    private void onLastMatchButtonClick() throws IOException {
        MatchController.getInstance().showMatchDetails();
    }

}
