package es.alrodmue.model.exceptions;

public class TeamPlayerPositionFullException extends TeamInvalidDataException {
    public TeamPlayerPositionFullException() {
        super("No hay ninguna posición disponible para este jugador.");
    }
}
