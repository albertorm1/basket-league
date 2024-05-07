package es.alrodmue.model.exceptions;

public class PlayerInvalidHeightException extends PlayerInvalidDataException {
    public PlayerInvalidHeightException() {
        super("La altura introducida no es válida.");
    }
}
