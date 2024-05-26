package es.alrodmue.controller;

import es.alrodmue.App;
import es.alrodmue.model.Team;
import es.alrodmue.model.exceptions.PlayerInvalidDataException;
import es.alrodmue.model.exceptions.TeamInvalidDataException;
import es.alrodmue.model.factories.PlayerFactory;
import es.alrodmue.model.players.Player;
import es.alrodmue.model.players.PlayerType;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * Controlador para los jugadores. Emplea el patrón de diseño singleton.
 * @author Alberto Rodriguez Muelas
 */
public class PlayerController {
    
    private static PlayerController instance = null;

    /**
     * Constructor privado del controlador.
     */
    private PlayerController() {}

    /**
     * Método para obtener instancia única del controlador o crearla si no existe.
     * @returns Instancia única del controlador.
     */
    public static PlayerController getInstance() {
        if (instance == null) instance = new PlayerController();
        return instance;
    }

    /**
     * Método para crear un jugador.
     * @param name Nombre del jugador.
     * @param height Altura del jugador en centímetros.
     * @param skill Nivel de habilidad del jugador del 1 al 5.
     */
    public void createPlayer(String name, String heightString, int skill) {
        PlayerFactory factory = PlayerFactory.getInstance();
        Team team = Team.getInstance();
        int height;
        Player player;

        try {
            height = Integer.parseInt(heightString); 
            player = factory.create(name, height, skill);
            team.addPlayer(player);
            PersistenceController.getInstance().addPlayer(player);
            App.closeModal();
        } catch (PlayerInvalidDataException e) {
            this.showError(e.getMessage());
        } catch (TeamInvalidDataException e) {
            this.showError(e.getMessage());
        } catch (NumberFormatException e) {
            this.showError("Debe introducir un número válido como altura.");   
        } catch (Exception e) {
            this.showError("Error inesperado");
        }
    } 

    /**
     * Método para mostrar los detalles de un jugador.
     * @param player Jugador cuyos detalles se desean mostrar.
     */
    public void showPlayerDetails(Player player) {
        this.showInfo("Información del jugador", player.getDetail());
    }

    /**
     * Método para mostrar los detalles de todos los jugadores de un tipo.
     * @param playerType
     */
    public void showPlayerDetails(PlayerType playerType) {
        Team team = Team.getInstance();
        Player[] playerList = team.getPlayerList(playerType);
        String detail = "";
        for (Player player : playerList) {
            detail += "\n--------------------\n";
            detail += player.getDetail();
            detail += "\n--------------------\n";
        }

        this.showInfo("Jugadores", detail);
    }

    /**
     * Método para eliminar un jugador.
     * @param player Jugador a eliminar. 
     */
    public void removePlayer(Player player){
        try {
            Team team = Team.getInstance();
            team.removePlayer(player);
            PersistenceController.getInstance().removePlayer(player);
            this.showInfo("Jugador eliminado", String.format("El jugador %s ha sido eliminado con éxito", player.getName()));
        } catch (Exception e) {
            this.showError("Error inesperado.");
        }
    }


    /**
     * Método privado para mostrar una alerta de error.
     * @param error Mensaje de error a mostrar.
     */
    private void showError(String error) {
        Alert alert = new Alert(AlertType.ERROR, "Error", ButtonType.CLOSE);
        alert.setHeaderText("");
        alert.setContentText(error);
        alert.show();
    }

    /**
     * Método privado para mostrar una alerta de información.
     * @param title Título de la ventana
     * @param message Mensaje a mostrar.
     */
    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION, title, ButtonType.OK);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.show();
    }
}
