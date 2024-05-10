package es.alrodmue.model.matches;

import java.time.LocalDate;

import es.alrodmue.model.exceptions.MatchInvalidDataException;
import es.alrodmue.model.exceptions.MatchInvalidDateException;

/**
 * Clase abstracta para los distintos tipos de partidos posibles.
 * @author Alberto Rodriguez Muelas.
 */
public abstract class Match {
    
    protected final LocalDate date;
    protected final int ownPoints;
    protected final int rivalPoints;

    /**
     * Constructor del partido, que asigna automáticamente los puntos a cada equipo tras calcularlos de forma aleatoria, y genera las faltas cometidas.
     * @param date Fecha del partido
     * @throws MatchInvalidDataException Excepción que indica si algún dato del partido no es válido. El mensaje de la excepción contiene un mensaje
     * de error entendible por el usuario.
     */
    public Match(LocalDate date) throws MatchInvalidDataException {
        int points1, points2, aux;
        if (!isDateValid(date)) throw new MatchInvalidDateException();

        this.date = date;

        points1 = calculatePoints();
        points2 = calculatePoints();
        if (points2 > points1) {
            aux = points1;
            points1 = points2;
            points2 = aux;
        }
        
        if (isOwnTeamWinner()) {
            this.ownPoints = points1;
            this.rivalPoints = points2;
        } else {
            this.ownPoints = points2;
            this.rivalPoints = points1;
        }
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
     * Método abstracto que calcula quien es el ganador.
     * @returns Valor booleano que indica si el ganador es el equipo propio (true) o el rival (false)
     */
    protected abstract boolean isOwnTeamWinner();

    /** 
     * Método abstracto que devuelve el tipo de partido. 
     * @returns String con el tipo de partido.
     */
    public abstract String getType();
}
