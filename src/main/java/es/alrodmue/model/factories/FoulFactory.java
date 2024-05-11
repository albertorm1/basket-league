package es.alrodmue.model.factories;

import es.alrodmue.model.exceptions.FoulInvalidDataException;
import es.alrodmue.model.fouls.FlagrantFoul;
import es.alrodmue.model.fouls.Foul;
import es.alrodmue.model.fouls.PersonalFoul;
import es.alrodmue.model.fouls.TechnicalFoul;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.players.Player;

/**
 * Clase factoría para las faltas. Implementa el patrón de diseño singleton.
 * @author Alberto Rodriguez Muelas
 */
public class FoulFactory {
    
    private static FoulFactory instance = null;

    /**
     * Constructor privado de la factoría.
     */
    private FoulFactory() {}

    /**
     * Método para obtener la instancia de la factoría, o crearla si no existe.
     * @returns Instancia única de la factoría.
     */
    public static FoulFactory getInstance() {
        if (instance == null) instance = new FoulFactory();
        return instance;
    }

    /**
     * Método encargado de decidir que tipo de falta se crea, así como de crear la misma.
     * @returns Falta generada.
     * @throws FoulInvalidDataException Excepción que indica que algún dato de la falta no es válido. Contiene como mensaje un mensaje de error legible para el usuario.
     */
    public Foul create(Player player, Match match) throws FoulInvalidDataException {
        int random = (int) (Math.random() * 3) + 1;
        Foul foul;

        switch(random) {
            case 1:
                foul = new PersonalFoul(player, match);
                break;
            case 2:
                foul = new FlagrantFoul(player, match);
                break;
            default:
                foul = new TechnicalFoul(player, match);
                break;
        }

        return foul;
    }
}
