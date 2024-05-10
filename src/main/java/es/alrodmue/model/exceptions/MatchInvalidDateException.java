package es.alrodmue.model.exceptions;

public class MatchInvalidDateException extends MatchInvalidDataException {
    public MatchInvalidDateException() {
        super("La fecha introducida no es válida.");
    }
}
