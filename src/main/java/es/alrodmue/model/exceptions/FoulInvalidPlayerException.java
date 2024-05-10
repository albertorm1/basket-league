package es.alrodmue.model.exceptions;

public class FoulInvalidPlayerException extends FoulInvalidDataException {
    public FoulInvalidPlayerException() {
        super("El jugador proporcionado no es válido.");
    }
}
