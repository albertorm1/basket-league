package es.alrodmue.model.factories;

import es.alrodmue.model.exceptions.PlayerInvalidDataException;
import es.alrodmue.model.players.CenterPlayer;
import es.alrodmue.model.players.Player;
import es.alrodmue.model.players.PointGuardPlayer;
import es.alrodmue.model.players.PowerForwardPlayer;
import es.alrodmue.model.players.ShootingGuardPlayer;
import es.alrodmue.model.players.SmallForwardPlayer;

/**
 * Clase factoría para la creación de jugadores. Utiliza el patrón de diseño Singleton.
 * @author Alberto Rodriguez Muelas
 */
public class PlayerFactory {
    
    private static PlayerFactory instance = null;

    /**
     * Constructor privado de la factoría.
     */
    private PlayerFactory() {}

    /**
     * Método para crear u obtener la instancia de la factoría.
     * @returns Instancia única de la factoría.
     */
    public static PlayerFactory getInstance() {
        if (instance == null) instance = new PlayerFactory();
        return instance;
    }

    /**
     * Método que determina que tipo de jugador debe crear y crea el mismo.
     * @param name Nombre completo del jugador.
     * @param height Altura del jugador en centímetros.
     * @param skill Nivel de habilidad del jugador, expresado del 1 al 5.
     * @returns Jugador generado.
     * @throws PlayerInvalidDataException Excepción que se producirá si alguno de los datos del jugador no es válido. Contiene como mensaje un mensaje de error entendible por los usuarios.
     */
    public Player create(String name, int height, int skill) throws PlayerInvalidDataException {
        int heightPoints = (int) Math.round((height - 150) / 20); // Calcula puntos de altura entre el 1 y el 5.
        int points = heightPoints - skill + 5; // Calcula puntos entre el 0 y el 9 combinando la altura y la habilidad.
        Player player;

        switch (points) {
            // Base
            case 0:
            case 1:
                player = new PointGuardPlayer(name, height, skill);
                break;
            // Escolta
            case 2:
            case 3:
                player = new ShootingGuardPlayer(name, height, skill);
                break;
            // Alero
            case 4:
            case 5:
                player = new SmallForwardPlayer(name, height, skill);
                break;
            // Ala-pivot
            case 6:
            case 7:
                player = new PowerForwardPlayer(name, height, skill);
                break;
            // Pivot
            case 8:
            case 9:
                player = new CenterPlayer(name, height, skill);
                break;
            // No encaja en perfil
            default:
                throw new PlayerInvalidDataException("El jugador no encaja en ningún perfil.");
        }

        return player;
    }
}