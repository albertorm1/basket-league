package es.alrodmue.model.matches;

/**
 * Enumerado para el tipo de partido.
 * @author Alberto Rodriguez Muelas
 */
public enum MatchType {
    EXHIBITION("Exhibición"),
    OFFICIAL_LOCAL("Oficial (local)"),
    OFFICIAL_VISITOR("Oficial (visitante)");

    private final String label;

    private MatchType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
