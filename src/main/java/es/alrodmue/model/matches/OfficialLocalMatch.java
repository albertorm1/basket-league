package es.alrodmue.model.matches;

import java.time.LocalDate;

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
    public String getType() {
        return "Oficial (local)";
    }
}
