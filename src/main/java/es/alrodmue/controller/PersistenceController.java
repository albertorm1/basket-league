package es.alrodmue.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import es.alrodmue.model.Team;
import es.alrodmue.model.factories.PlayerFactory;
import es.alrodmue.model.fouls.Foul;
import es.alrodmue.model.matches.Match;
import es.alrodmue.model.players.Player;

/**
 * Clase para gestionar la persistencia de datos mediante archivos.
 * @author Alberto Rodriguez Muelas
 */
public class PersistenceController {
    
    private static PersistenceController instance = null;

    private File file;
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<Match> matchList = new ArrayList<Match>();
    private ArrayList<Foul> foulList = new ArrayList<Foul>(); 

    /**
     * Constructor privado
     */
    private PersistenceController(String fileName) {
        this.file= new File(fileName);
    }

    /**
     * Método estático para obtener la instancia única de la clase, y generarla si no existe.
     * @returns Instancia única de la clase.
     */
    public static PersistenceController getInstance() {
        if (instance == null) instance = new PersistenceController("data.txt");
        return instance;
    }

    /**
     * Método que carga en el sistema todos los datos.
     * @throws Exception Excepción inesperada.
     */
    public void loadData() throws Exception {
        String rawData;
        String[] data;

        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                rawData = sc.nextLine();
                data = rawData.split("\t");

                if (rawData.isBlank()) continue;
                if (data[0] == null) continue;

                switch (data[0]) {
                    case "P":
                        this.loadPlayer(data);
                        break;
                }
            }
        }
    }

    
    /**
     * Método para cargar a un jugador.
     * @param data Array con los datos del jugador.
     * @throws Exception Excepción inesperada.
     */
    private void loadPlayer(String[] data) throws Exception {
        String name = data[1];
        int number = Integer.parseInt(data[2]);
        int height = Integer.parseInt(data[3]);
        int skill = Integer.parseInt(data[4]);
        int points = Integer.parseInt(data[5]);
        boolean inTeam = data[6].equals("true");

        Player player = PlayerFactory.getInstance().load(name, number, height, skill, points);

        this.playerList.add(player);
        if (inTeam) Team.getInstance().addPlayer(player);
    }
}
