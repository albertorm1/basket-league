package es.alrodmue.model.exceptions;

public class MatchInvalidPlayerAmountException extends MatchInvalidDataException {
    public MatchInvalidPlayerAmountException() {
        super("No hay jugadores suficientes para jugar el partido");
    }
}
