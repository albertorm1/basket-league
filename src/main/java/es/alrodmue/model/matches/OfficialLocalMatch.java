package es.alrodmue.model.matches;

import java.time.LocalDate;
import java.util.HashMap;

import es.alrodmue.model.exceptions.MatchInvalidDataException;
import es.alrodmue.model.players.Player;

/**
 * Clase para partidos oficiales jugando como equipo local.
 * @author Alberto Rodriguez Muelas
 */
public class OfficialLocalMatch extends Match {
    
    /**
     * Constructor del partido, que asigna automáticamente los puntos a cada equipo tras calcularlos de forma aleatoria, y genera las faltas cometidas.
     * @param date Fecha del partido
     * @throws MatchInvalidDataException Excepción que indica si algún dato del partido no es válido. El mensaje de la excepción contiene un mensaje
     * de error entendible por el usuario.
     * @throws Exception Excepción inesperada.
     */
    public OfficialLocalMatch(LocalDate date, Player[] players) throws MatchInvalidDataException, Exception {
        super(date, players);
    }

    /**
     * Constructor usado para importar los datos almacenados.
     * @param number Número de partido.
     * @param date Fehca
     * @param ownPoints Puntos del equipo propio
     * @param rivalPoints Puntos del equipo rival
     * @param points Puntos de cada jugador
     */
    public OfficialLocalMatch(int number, LocalDate date, int ownPoints, int rivalPoints, HashMap<Player, Integer> points) {
        super(number, date, ownPoints, rivalPoints, points);
    }

    /**
     * Método que decide que equipo gana.
     * @returns Valor booleano que indica si gana el equipo propio (true) o el rival (false)
     */
    @Override
    protected boolean isOwnTeamWinner() {
        return Math.random() * 10 < 5;
    }

    /**
     * Método que devuelve el tipo de partido.
     * @returns String con el tipo de partido.
     */
    @Override
    public MatchType getType() {
        return MatchType.OFFICIAL_LOCAL; 
    }
}
