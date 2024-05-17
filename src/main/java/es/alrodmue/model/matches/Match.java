package es.alrodmue.model.matches;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.alrodmue.model.exceptions.MatchInvalidDataException;
import es.alrodmue.model.exceptions.MatchInvalidDateException;
import es.alrodmue.model.exceptions.MatchInvalidPlayerAmountException;
import es.alrodmue.model.factories.FoulFactory;
import es.alrodmue.model.fouls.Foul;
import es.alrodmue.model.players.Player;

/**
 * Clase abstracta para los distintos tipos de partidos posibles.
 * @author Alberto Rodriguez Muelas.
 */
public abstract class Match {
    
    protected final LocalDate date;
    protected final int ownPoints;
    protected final int rivalPoints;
    protected final Player[] playerList;
    protected HashMap<Player, Integer> playerPoints = new HashMap<Player, Integer>();
    protected ArrayList<Foul> fouls = new ArrayList<Foul>();

    /**
     * Constructor del partido, que asigna automáticamente los puntos a cada equipo tras calcularlos de forma aleatoria, y genera las faltas cometidas.
     * @param date Fecha del partido
     * @param players Lista de jugadores que juegan en el partido.
     * @throws MatchInvalidDataException Excepción que indica si algún dato del partido no es válido. El mensaje de la excepción contiene un mensaje.
     * @throws Esception Excepción inesperada.
     * de error entendible por el usuario.
     */
    public Match(LocalDate date, Player[] players) throws MatchInvalidDataException, Exception {
        int points1, points2, aux1, aux2;
        FoulFactory foulFactory = FoulFactory.getInstance();

        // Validaciones
        if (!isDateValid(date)) throw new MatchInvalidDateException();
        if (players.length != 5) throw new MatchInvalidPlayerAmountException(); 


        // Calcula los puntos de cada uno de los equipos, y determina el ganador.
        points1 = calculatePoints();
        points2 = calculatePoints();
        if (points2 > points1) {
            aux1 = points1;
            points1 = points2;
            points2 = aux1;
        }
        
        if (isOwnTeamWinner()) {
            this.ownPoints = points1;
            this.rivalPoints = points2;
        } else {
            this.ownPoints = points2;
            this.rivalPoints = points1;
        }

        // Calcula los puntos para cada jugador
        aux2 = this.ownPoints;
        for (int i = 0; i < players.length; i++) {
            if (i == players.length - 1) {
                aux1 = aux2;
            } else {
                aux1 = (int) (Math.random() * aux2) + 1;
            }

            aux2 -= aux1;
            this.playerPoints.put(players[i], aux1);
        }

        // Genera las faltas para cada jugador (maximo 5 faltas por partido)
        aux1 = (int) (Math.random() * 5) + 1;
        for (int i = 0; i < aux1; i++) {
            aux2 = (int) (Math.random() * players.length);
            this.fouls.add(foulFactory.create(players[i], this));
        }

        // Asigna el resto de parámetros
        this.date = date;
        this.playerList = players;
    }

    /**
     * Método que comprueba si una fecha es válida o no en base a los siguientes requisitos:
     * - La fecha no puede ser superior a la fecha actual.
     * - La fecha no puede ser inferior al 1 de enero del 2000.
     * 
     * @returns Valor booleano indicando si la fecha es válida (true) o no (false).
     */
    public static boolean isDateValid(LocalDate date) {
        if (date == null) return false;
        if (date.isAfter(LocalDate.now())) return false;
        if (date.isBefore(LocalDate.of(2000, 1, 1))) return false;
        return true;
    }

    /**
     * Método que obtiene una puntuación aleatoria:
     * - Entre 35 y 70 ptos. -> 10%
     * - Entre 70 y 100 ptos. -> 80%
     * - Entre 100 y 150 ptos. -> 10%
     * 
     * @returns Número que indica la puntuación obtenida.
     */
    protected static int calculatePoints() {
        int firstRandom, secondRandom;
        firstRandom = (int) Math.round(Math.random() * 100) / 10;
        switch (firstRandom) {
            case 1:
                secondRandom = (int) Math.round(Math.random() * 35) + 35;
                break;
            case 2:
                secondRandom = (int) Math.round(Math.random() * 50) + 100;
                break;
            default:
                secondRandom = (int) Math.round(Math.random() * 30) + 70; 
                break;
        }
        return secondRandom;
    }

    /**
     * Método para obtener la fecha del partido.
     * @returns Fecha del partido.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Método para obtener la puntuación del equipo propio.
     * @returns Puntuación del equipo propio.
     */
    public int getOwnPoints() {
        return this.ownPoints;
    }

    /**
     * Método para obtener la puntuación del equipo rival.
     * @returns Puntuación del equipo rival.
     */
    public int getRivalPoints() {
        return this.rivalPoints;
    }

    /** 
     * Método para obtener el ganador del partido. 
     * @returns Cadena de texto con el ganador del partido.
     */
    public String getWinner() {
        if (this.ownPoints > this.rivalPoints) return "Equipo propio";
        if (this.rivalPoints > this.ownPoints) return "Equipo rival";
        return "Empate";
    }

    /**
     * Método para obtener el listado de jugadores del partido.
     * @return Array con los jugadores del partido.
     */
    public Player[] getPlayers() {
        return this.playerList;
    }

    /**
     * Método para obtener el listado de puntos por jugador del partido.
     * @return HashMap con los puntos de cada jugador.
     */
    public HashMap<Player, Integer> getPlayerPoints() {
        return this.playerPoints;
    }

    /**
     * Método para obtener el listado de faltas cometidas en el partido.
     * @return ArrayList de faltas cometidas en el partido.
     */
    public ArrayList<Foul> getFouls() {
        return this.fouls;
    }

    /**
     * Método para obtener los detalles del partido.
     * @return String con los detalles del partido.
     */
    public String getDetails() {
        String details = "";

        // Detalles generales
        details += String.format("Fecha: %s\n", this.date);
        details += String.format("Tipo: %s\n\n", this.getType());

        // Puntos y ganador
        details += String.format("Puntos propios: %s ptos.\n", this.ownPoints);
        details += String.format("Puntos rival: %s ptos.\n", this.rivalPoints);
        details += String.format("Ganador: %s\n\n", this.getWinner());

        // Jugadores y puntos por jugador
        details += "Jugadores:\n";
        for (Map.Entry<Player, Integer> player : this.playerPoints.entrySet()) {
            details += String.format("%s - %s ptos.\n", player.getKey(), player.getValue());
        }

        // Faltas
        details += "\nFaltas:\n";
        for (Foul foul : this.fouls) {
            String.format("%s - %s\n", foul.getType(), foul.getPlayer());
        }

        return details;
    }

    /**
     * Método abstracto que calcula quien es el ganador.
     * @returns Valor booleano que indica si el ganador es el equipo propio (true) o el rival (false)
     */
    protected abstract boolean isOwnTeamWinner();

    /** 
     * Método abstracto que devuelve el tipo de partido. 
     * @returns String con el tipo de partido.
     */
    public abstract MatchType getType();
}
