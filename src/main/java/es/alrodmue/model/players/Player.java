// Clase abstracta para los jugadores
// Autor: Alberto Rodriguez Muelas

package es.alrodmue.model.players;

import es.alrodmue.model.exceptions.PlayerInvalidDataException;
import es.alrodmue.model.exceptions.PlayerInvalidHeightException;
import es.alrodmue.model.exceptions.PlayerInvalidNameException;
import es.alrodmue.model.exceptions.PlayerInvalidSkillException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Player {
    private static int nextNumber = 1;

    protected String name;
    protected int number;
    protected int height;
    protected int skill;
    protected int points;
    protected ObservableList<String> fouls; // TODO: replace T

    public Player(String name, int height, int skill) throws PlayerInvalidDataException {
        if (!isNameValid(name)) throw new PlayerInvalidNameException();
        if (!isHeightValid(height)) throw new PlayerInvalidHeightException();
        if (!isSkillValid(skill)) throw new PlayerInvalidSkillException();

        this.name = name;
        this.number = nextNumber++;
        this.height = height;
        this.skill = skill;
        this.points = 0;
        this.fouls = FXCollections.observableArrayList();
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public int getNumber() {
        return this.number;
    }

    public int getHeight() {
        return this.height;
    }

    public int getSkill() {
        return this.skill;
    }

    public int getPoints() {
        return this.points;
    }

    public ObservableList<String> getFouls() { // TODO: replace T
        return this.fouls;
    }

    // Comprobadores
    public static boolean isNameValid(String name) {
        if (name == null) return false;
        if (name.isBlank()) return false;
        if (!name.matches("^([A-Z][a-z]*)+[A-Z][a-z]*$")) return false;
        return true;
    }

    public static boolean isNumberValid(int number) {
        if (number < 1) return false;
        if (number > 99) return false;
        return true;
    }

    public static boolean isHeightValid(int height) {
        if (height < 150) return false;
        if (height > 250) return false;
        return true;
    }

    public static boolean isSkillValid(int skill) {
        if (skill < 1) return false;
        if (skill > 5) return false;
        return true;
    }

    public static boolean arePointsValid(int points) {
        if (points < 0) return false;
        return true;
    }

    public static boolean isFoulValid(String foul) { // TODO: replace type
        if (foul == null) return false;
        return true;
    }

    // Método para añadir puntos
    public void addPoints(int points) {
        this.points += points;
    }

    // Método para añadir faltas
    // TO-DO

    // Método para obtener los detalles.
    public String getDetail() {
        String details = "";

        details += String.format("Nombre:\n%s\n\n", this.name);
        details += String.format("Numero de dorsal:\n%s\n\n", this.number);
        details += String.format("Posición:\n%s\n\n", this.getType());
        details += String.format("Altura:\n%s cm\n\n", this.height);
        details += String.format("Habilidad:\n%s estrellas\n\n", this.skill);
        details += String.format("Puntos:\n%s puntos\n\n", this.points);

        details += "Faltas:\n";
        // TODO: add fouls

        return details;
    }

    // Remplaza el método toString
    @Override
    public String toString() {
        return this.getName();
    }

    // Método abstracto para obtener el tipo
    public abstract String getType();
}
