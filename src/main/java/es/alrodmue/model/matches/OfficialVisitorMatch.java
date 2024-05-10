package es.alrodmue.model.matches;

import java.time.LocalDate;

import es.alrodmue.model.exceptions.MatchInvalidDataException;

/**
 * Clase para partidos oficiales jugando como equipo visitante.
 * @author Alberto Rodriguez Muelas
 */
public class OfficialVisitorMatch extends Match {
    
    /**
     * Constructor del partido, que asigna automáticamente los puntos a cada equipo tras calcularlos de forma aleatoria, y genera las faltas cometidas.
     * @param date Fecha del partido
     * @throws MatchInvalidDataException Excepción que indica si algún dato del partido no es válido. El mensaje de la excepción contiene un mensaje
     * de error entendible por el usuario.
     */
    public OfficialVisitorMatch(LocalDate date) throws MatchInvalidDataException {
        super(date);
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
        return "Oficial (visitante)";
    }
}
