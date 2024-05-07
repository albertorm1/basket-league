package es.alrodmue.model.exceptions;

public class PlayerInvalidSkillException extends PlayerInvalidDataException {
    public PlayerInvalidSkillException() {
        super("El nivel de habilidad introducido no es válido.");
    }
}
