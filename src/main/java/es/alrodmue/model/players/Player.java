// Clase abstracta para los jugadores
// Autor: Alberto Rodriguez Muelas

package es.alrodmue.model.players;

import es.alrodmue.model.exceptions.PlayerInvalidDataException;
import es.alrodmue.model.exceptions.PlayerInvalidHeightException;
import es.alrodmue.model.exceptions.PlayerInvalidNameException;
import es.alrodmue.model.exceptions.PlayerInvalidSkillException;
import es.alrodmue.model.fouls.Foul;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase abstracta para controlar los datos de los jugadores
 * @author Alberto Rodriguez Muelas
 */

public abstract class Player {
    private static int nextNumber = 1;

    protected String name;
    protected int number;
    protected int height;
    protected int skill;
    protected int points;
    protected ObservableList<Foul> fouls;

    /**
     * Constructor de la clase, que crea un nuevo jugador a partir de su nombre, altura y habilidad, siempre que estos sean válidos.
     * El resto de atributos los asigna de forma automática.
     * 
     * @param name
     * Nombre completo del jugador.
     * 
     * @param height
     * Altura del jugador en centímetros.
     * 
     * @param skill
     * Nivel de habilidad del jugador del 1 al 5.
     * 
     * @throws PlayerInvalidDataException 
     * Excepción que indica que alguno de los parámetros introducidos no es válido. Incluye como mensaje
     * un mensaje de error entendible para el usuario.
     */

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

    /**
     * Constructor que se usará para cargar los datos de un archivo cuando se cargue la clase. No genera ningún valor automáticamente.
     * @param name Nombre del jugador.
     * @param number Número de dorsal del jugador.
     * @param height Altura del jugador.
     * @param skill Habilidad del jugador.
     * @param points Puntos totales del jugador.
     * @param fouls Array de faltas del jugador.
     * @throws PlayerInvalidDataException Excepción que indica que alguno de los parámetros introducidos no es válido. Incluye como mensaje un mensaje de error entendible para el usuario.
     */
    public Player (String name, int number, int height, int skill, int points, Foul[] fouls) throws PlayerInvalidDataException {
        if (!isNameValid(name)) throw new PlayerInvalidNameException();
        if (!isHeightValid(height)) throw new PlayerInvalidHeightException();
        if (!isSkillValid(skill)) throw new PlayerInvalidHeightException();

        this.name = name;
        this.number = number;
        this.height = height;
        this.skill = skill;
        this.points = points;
        this.fouls = FXCollections.observableArrayList();

        if (nextNumber <= number) nextNumber = number + 1;
    }

    
    /**
     * Método para obtener el nombre completo del jugador.
     * @return Nombre completo del jugador.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método para obtener el núemro de dorsal del jugador
     * @return Número de dorsal del jugador.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Método para obtener la altura del jugador.
     * @return Altura del jugador en centímetros.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Método para obtener el nivel de habilidad del jugador, determinado por un número del 1 al 5.
     * @return Nivel de habilidad del jugador.
     */
    public int getSkill() {
        return this.skill;
    }

    /**
     * Método para obtener el total de puntos que ha marcado el jugador durante la temporada.
     * @return Número de puntos marcados por el jugador.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Método para obtener todas las faltas que ha cometido el jugador durante la temporada.
     * @return Lista observable que contiene las faltas cometidas por el jugador.
     */ 
    public ObservableList<Foul> getFouls() {
        return this.fouls;
    }

    /**
     * Método que evalúa si un nombre es válido o no en base a los siguientes requisitos:
     * - No puede estar vacío.
     * - Debe tener al menos 2 palabras.
     * - Cada palabra debe empezar por mayúsculas.
     * - No puede haber mayúsculas en caracteres que no sean el inicio de las palabras.
     * - No puede contener números ni carácteres especiales.
     * 
     * @param name Nombre del jugador a evaluar.
     * @return Valor booleano que indica si el nombre es válido (true) o no (false).
     */
    public static boolean isNameValid(String name) {
        if (name == null) return false;
        if (name.isBlank()) return false;
        if (!name.matches("^([A-Z][a-z]* )+[A-Z][a-z]*$")) return false;
        return true;
    }

    /**
     * Método que evalúa si el número de dorsal de un jugador es válido o no en base a los siguientes requisitos:
     * - Debe estar comprendido entre 1 y 99.
     * 
     * @param number Número de dorsal a evaluar.
     * @return Valor booleano que indica si el número de dorsal es válido (true) o no (false).
     */
    public static boolean isNumberValid(int number) {
        if (number < 1) return false;
        if (number > 99) return false;
        return true;
    }

    /**
     * Método que evalúa si la altura de un jugador es válida o no en base a los siguientes requisitos:
     * - Debe estar comprendida entre 150 y 250 centímetros.
     * 
     * @param height Altura del jugador a evaluar, expresada en centímetros.
     * @return Valor booleano que indica si la altura es válida (true) o no (false).
     */
    public static boolean isHeightValid(int height) {
        if (height < 150) return false;
        if (height > 250) return false;
        return true;
    }

    /**
     * Método que evalua si el nivel de habilidad de un jugador es válido o no en base a los siguientes requisitos:
     * - Debe estar comprendido entre 1 y 5.
     * 
     * @param skill Nivel de habilidad a evaluar.
     * @return Valor booleano que indica si el nivel de habilidad es válido (true) o no (false).
     */
    public static boolean isSkillValid(int skill) {
        if (skill < 1) return false;
        if (skill > 5) return false;
        return true;
    }

    /**
     * Método que evalua si el número de puntos a añadir es válido o no en base a los siguientes requisitos:
     * - No pueden ser número negativos.
     * 
     * @param points Número de puntos a evaluar.
     * @return Valor booleano que indica si el núemro de puntos a añadir es válido (true) o no (false).
     */
    public static boolean arePointsValid(int points) {
        if (points < 0) return false;
        return true;
    }

    /**
     * Método que evalúa si una falta es válida o no, en base a los siguientes requisitos:
     * - El valor no puede estar vació.
     * 
     * @param foul Falta a evaluar.
     * @return Valor booleano que indica si la falta es válida (true) o no (false).
     */
    public static boolean isFoulValid(Foul foul) {
        if (foul == null) return false;
        return true;
    }

    /**
     * Método que añade puntos al total de puntos marcados por el jugador.
     * 
     * @param points Número de puntos a añadir.
     */
    public void addPoints(int points) {
        this.points += points;
    }

    /**
     * Método que añade faltas al jugador.
     * @param foul Falta a añadir.
     */
    public void addFoul(Foul foul) {
        this.fouls.add(foul);
    }

    /**
     * Método para obtener una cadena de texto con todos los detalles del jugador.
     * @return Cadena de texto con los detalles del jugador.
     */
    public String getDetail() {
        String details = "";

        details += String.format("Nombre: %s\n", this.name);
        details += String.format("Numero de dorsal: %s\n", this.number);
        details += String.format("Posición: %s\n", this.getType());
        details += String.format("Altura: %s cm\n", this.height);
        details += String.format("Habilidad: %s estrellas\n", this.skill);
        details += String.format("Puntos: %s puntos\n", this.points);

        details += "\nFaltas:\n";
        for (Foul foul : this.fouls) {
            details += String.format("%s - %s\n", foul.getMatch().getDate(), foul.getType());
        }

        return details;
    }

    /**
     * Sobreescritura del método toString, para que este devuelva el nombre completo del jugador.
     * @return Nombre completo del jugador.
     */
    @Override
    public String toString() {
        return this.getName();
    }

    /**
     * Método abstracto, intencionado para obtener una cadena de texto con el tipo del jugador.
     * @return Tipo del jugador.
     */
    public abstract PlayerType getType();
}
