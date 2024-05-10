package es.alrodmue.model.fouls;

import es.alrodmue.model.exceptions.FoulInvalidDataException;
import es.alrodmue.model.exceptions.FoulInvalidMatchException;
import es.alrodmue.model.exceptions.FoulInvalidPlayerException;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.players.Player;

/**
 * Clase abstracta para los distintos tipos de faltas existentes en el baloncesto
 * @author Alberto Rodriguez Muelas
 */
public abstract class Foul {
    
    protected final Player player;
    protected final Match match;

    /**
     * Constructor, que crea la falta en función a un jugador y un partido dados.
     * 
     */
    public Foul(Player player, Match match) throws FoulInvalidDataException {
        if(!isPlayerValid(player)) throw new FoulInvalidPlayerException();
        if(!isMatchValid(match)) throw new FoulInvalidMatchException();

        this.player = player;
        this.match = match;
    }

    /**
     * Método que comprueba si un jugador dado es válido o no.
     * @param player Jugador a comprobar
     * @returns Valor booleano indicando si el jugador es váido (true) o no (false). 
     */
    public static boolean isPlayerValid(Player player) {
        if (player == null) return false;
        return true;
    }

    /**
     * Método que comprueba si un partido dado es válido o no.
     * @param match Partido a comprobar
     * @returns Valor booleano indicando si el partido es válido (true) o no (false)
     */
    public static boolean isMatchValid(Match match) {
        if (match == null) return false;
        return true;
    }

    /**
     * Método para obtener el jugador que ha cometido la falta
     * @returns Jugador que ha cometido la falta.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Méetodo para obtener el partido en el que se ha cometido la falta
     * @returns Partido en el que se ha cometido la falta.
     */
    public Match getMatch() {
        return this.match;
    }

    /**
     * Método abstracto para obtener el tipo de falta cometida.
     * @returns Cadena de texto con el tipo de falta cometida.
     */
    public abstract String getType();
}
