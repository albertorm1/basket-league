package es.alrodmue.model.fouls;

import es.alrodmue.model.exceptions.FoulInvalidDataException;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.players.Player;

/**
 * Clase para faltas personales.
 * @author Alberto Rodriguez Muelas
 */
public class PersonalFoul extends Foul {
    
    /**
     * Constructor, que crea la falta en función a un jugador y un partido.
     * @param player Jugador que comete la falta.
     * @param match Partido en el que se comete la falta.
     * @throws FoulInvalidDataException Excepción que se produce si alguno de los parámetros no son válidos. Contiene un mensaje de error
     * entendible por el usuario.
     */
    public PersonalFoul(Player player, Match match) throws FoulInvalidDataException {
        super(player, match);
    }

    /**
     * Método que indica el tipo de la falta.
     * @returns String con el tipo de la falta.
     */
    @Override
    public String getType() {
        return "Falta personal";
    }

}
