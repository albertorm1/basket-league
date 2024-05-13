package es.alrodmue.model;

import es.alrodmue.model.exceptions.TeamInvalidDataException;
import es.alrodmue.model.exceptions.TeamPlayerListFullException;
import es.alrodmue.model.exceptions.TeamPlayerPositionFullException;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.players.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase para el equipo de baaloncesto. Utiliza el patrón de diseño singleton.
 * @author Alberto Rodriguez Muelas
 */
public class Team {
    
    private static Team instance = null;

    private ObservableList<Player> playerList;
    private ObservableList<Match> matchList;

    /**
     * Constructor privado de la clase. Crea las listas de jugadores y partidos.
     */
    private Team() {
        this.playerList = FXCollections.observableArrayList();
        this.matchList = FXCollections.observableArrayList();
    }

    /**
     * Método para obtener la instancia o crear una si no existe.
     * @returns Instancia única del equipo.
     */
    public static Team getInstance() {
        if (instance == null) instance = new Team();
        return instance;
    }

    /**
     * Método para añadir un jugador
     * @param player Jugador a añadir
     * @throws TeamInvalidDataException Excepción que se producirá si el jugador no puede ser añadido al equipo. Contiene como mensaje un mensaje
     * explicativo de los motivos por los que no ha podido ser añadido. Dicho mensaje es entendible para el usuario.
     */
    public void addPlayer(Player player) throws TeamInvalidDataException {
        int samePosition = 0;

        // Cuenta el número de jugadores en la misma posición que el que se desea añadir
        for (Player x : this.playerList) {
            if (x.getType().equals(player.getType())) samePosition ++;
        }

        // Guard clauses
        if (this.playerList.size() >= 15) throw new TeamPlayerListFullException();
        if (samePosition >= 3) throw new TeamPlayerPositionFullException();

        // Añade el jugador
        playerList.add(player);
    }

    /**
     * Método que elimina un jugador de la lista si existe
     * @param player Jugador a eliminar
     */
    public void removePlayer(Player player) {
        playerList.remove(player);
    }

    /**
     * Método para obtener la lista completa de jugadores
     * @returns Lista de jugadores.
     */
    public ObservableList<Player> getPlayerList() {
        return this.playerList;
    }

    /**
     * Método para añadir un partido a la lista.
     * @param match Partido a añadir.
     */
    public void addMatch(Match match) {
        this.matchList.add(match);
    }

    /**
     * Método que obtiene la lista completa de partidos.
     * @returns Lista de partidos.
     */
    public ObservableList<Match> getMatchList() {
        return this.matchList;
    } 
}
