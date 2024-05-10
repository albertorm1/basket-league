package es.alrodmue.model.players;

import es.alrodmue.model.exceptions.PlayerInvalidDataException;

/**
 * Clase para jugadores escolta. 
 * @author Alberto Rodriguez Muelas
 */
public class ShootingGuardPlayer extends Player {
    
    /**
     * Constructor de la clase, que crea un nuevo jugador a partir de su nombre, altura y habilidad, siempre que estos sean válidos.
     * El resto de atributos los asigna de forma automática.
     * 
     * @param name
     * Nombre completo del jugador.
     * 
     * @param height
     * Altura del jugador en centímetros.
     * 
     * @param skill
     * Nivel de habilidad del jugador del 1 al 5.
     * 
     * @throws PlayerInvalidDataException 
     * Excepción que indica que alguno de los parámetros introducidos no es válido. Incluye como mensaje
     * un mensaje de error entendible para el usuario.
     */

    public ShootingGuardPlayer (String name, int height, int skill) throws PlayerInvalidDataException {
        super(name, height, skill);
    }

    /**
     * Clase que devuelve el tipo de jugador.
     * @returns String con el tipo del jugador.
     */
    @Override
    public String getType() {
        return "Escolta";
    }
}