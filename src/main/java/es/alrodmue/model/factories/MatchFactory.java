package es.alrodmue.model.factories;

import java.time.LocalDate;

import es.alrodmue.model.exceptions.MatchInvalidDataException;
import es.alrodmue.model.matches.ExhibitionMatch;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.matches.OfficialLocalMatch;
import es.alrodmue.model.matches.OfficialVisitorMatch;

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
     */
    public Match create(String type, LocalDate date) throws MatchInvalidDataException{
        Match match;

        switch (type) {
            case "Oficial (Local)":
                match = new OfficialLocalMatch(date);
                break;
            case "Oficial (Visitante)":
                match = new OfficialVisitorMatch(date);
                break;
            default:
                match = new ExhibitionMatch(date);
                break;
        }

        return match;
    }
    
}
