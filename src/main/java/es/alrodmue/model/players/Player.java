// Clase abstracta para los jugadores
// Autor: Alberto Rodriguez Muelas

package es.alrodmue.model.players;

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

    public Player(String name, int height, int skill) {
        // TODO: checks

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
