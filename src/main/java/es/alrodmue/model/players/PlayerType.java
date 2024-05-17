package es.alrodmue.model.players;

/**
 * Enumerado para los tipos de jugadores
 * @author Alberto Rodríguez Muelas
 */
public enum PlayerType {
    POINT_GUARD("Base"),
    SHOOTING_GUARD("Escolta"),
    SMALL_FORWARD("Alero"),
    POWER_FORWARD("Ala-Pívot"),
    CENTER("Pívot");

    private final String label;

    private PlayerType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
