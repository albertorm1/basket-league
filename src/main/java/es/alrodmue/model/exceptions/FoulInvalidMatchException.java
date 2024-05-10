package es.alrodmue.model.exceptions;

public class FoulInvalidMatchException extends FoulInvalidDataException {
    public FoulInvalidMatchException() {
        super("El partido proporcionado no es válido.");
    }
}
