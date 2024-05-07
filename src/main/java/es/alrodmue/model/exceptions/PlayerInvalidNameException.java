package es.alrodmue.model.exceptions;

public class PlayerInvalidNameException extends PlayerInvalidDataException {
    public PlayerInvalidNameException() {
        super("El nombre introducido no es válido.");
    }
}
