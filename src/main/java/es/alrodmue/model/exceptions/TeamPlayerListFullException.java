package es.alrodmue.model.exceptions;

public class TeamPlayerListFullException extends TeamInvalidDataException {
    public TeamPlayerListFullException() {
        super("El equipo ya tiene 15 jugadores. No se pueden añadir más.");
    }    
}
