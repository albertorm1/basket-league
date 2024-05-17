package es.alrodmue.controller;

import java.time.LocalDate;
import java.util.Map;

import es.alrodmue.model.Team;
import es.alrodmue.model.exceptions.MatchInvalidDataException;
import es.alrodmue.model.factories.MatchFactory;
import es.alrodmue.model.fouls.Foul;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.matches.MatchType;
import es.alrodmue.model.players.Player;
import es.alrodmue.model.players.PlayerType;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Controlador para los partidos. Implementa el patrón de diseño singleton.
 * @author Alberto Rodriguez Muelas
 */
public class MatchController {
    
    private static MatchController instance = null;

    /**
     * Constructor privado para el controlador.
     */
    private MatchController() {}

    /**
     * Método para obtener la instancia del controlador, o crearla si no existe.
     * @returns Instancia única del controlador.
     */
    public static MatchController getInstance() {
        if (instance == null) instance = new MatchController();
        return instance;
    }

    /**
     * Método para jugar un partido.
     * @param type Tipo de partido.
     */
    public void playMatch(MatchType type) {
        MatchFactory factory = MatchFactory.getInstance();
        Team team = Team.getInstance();

        PlayerType[] playerTypes = {PlayerType.POINT_GUARD, PlayerType.SHOOTING_GUARD, PlayerType.SMALL_FORWARD, PlayerType.POWER_FORWARD, PlayerType.CENTER};
        Player[] playersOfType;
        int rand;

        Player[] playerList = new Player[5];
        Match match;

        // Obtiene un jugador de cada tipo.
        for (int i = 0; i < playerList.length; i++) {
            playersOfType = team.getPlayerList(playerTypes[i]);
            if (playersOfType.length == 0) {
                this.showError("No hay suficientes jugadores en el equipo para jugar");
                return;
            }
            
            rand = (int) (Math.random() * playersOfType.length);
            playerList[i] = playersOfType[rand];
        }

        // Crea el partido
        try {
            match = factory.create(type, LocalDate.now(), playerList);
            
            // Asigna las faltas a los jugadores
            for (Foul foul: match.getFouls()) {
                foul.getPlayer().addFoul(foul);
            }

            // Añade los puntos a cada jugador
            for (Map.Entry<Player, Integer> pointEntry : match.getPlayerPoints().entrySet()) {
                pointEntry.getKey().addPoints(pointEntry.getValue());
            }

            // Añade el partido a la lista y muestra los detalles del partido
            team.addMatch(match);
            this.showInfo("Resumen del partido", match.getDetails());

        } catch (MatchInvalidDataException e) {
            this.showError(e.getMessage());
        } catch (Exception e) {
            this.showError("Error inesperado.");
        }
    }

    /**
     * Método para mostrar los detalles de un partido.
     * @param match Partido cuya información se desea mostrar.
     */
    public void showMatchDetails(Match match) {
        this.showInfo("Resumen del partido", match.getDetails());
    }

    /**
     * Método para mostrar los detalles del último partido.
     */
    public void showMatchDetails() {
        Team team = Team.getInstance();
        ObservableList<Match> matchList = team.getMatchList(); 
        Match match = matchList.get(matchList.size() - 1);
        this.showMatchDetails(match);
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
