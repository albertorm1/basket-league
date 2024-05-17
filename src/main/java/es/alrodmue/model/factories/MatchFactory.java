package es.alrodmue.model.factories;

import java.time.LocalDate;

import es.alrodmue.model.exceptions.MatchInvalidDataException;
import es.alrodmue.model.matches.ExhibitionMatch;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.matches.MatchType;
import es.alrodmue.model.matches.OfficialLocalMatch;
import es.alrodmue.model.matches.OfficialVisitorMatch;
import es.alrodmue.model.players.Player;

/**
 * Factoría para partidos. Implementa el patrón de diseño singleton.
 * @author Alberto Rodriguez Muelas
 */
public class MatchFactory {

    private static MatchFactory instance = null;

    /**
     * Constructor privado de la factoría. 
     */
    private MatchFactory() {}

    /**
     * Método para obtener la instancia única de la factoría, o crearla si no existe.
     * @returns Instancia única de la factoría.
     */
    public static MatchFactory getInstance() {
        if (instance == null) instance = new MatchFactory();
        return instance;
    }

    /**
     * Método encargado de decidir que tipo de partido se crea, y crear el mismo.
     * @param type String con el tipo de partido a crear.
     * @param date Fecha en la que transcurre el partido.
     * @returns Partido del tipo correspondiente.
     * @throws MatchInvalidDataException Excepción que se produce cuando alguno de los datos del partido no es válido. Contiene un mensaje de error entendible por el usuario.
     * @throws Exception Excepción inesperada.
     */
    public Match create(MatchType type, LocalDate date, Player[] players) throws MatchInvalidDataException, Exception {
        Match match;

        if (type == MatchType.OFFICIAL_LOCAL) {
            match = new OfficialLocalMatch(date, players);
        } else if (type == MatchType.OFFICIAL_VISITOR) {
            match = new OfficialVisitorMatch(date, players);
        } else {
            match = new ExhibitionMatch(date, players);
        }

        return match;
    }
    
}
