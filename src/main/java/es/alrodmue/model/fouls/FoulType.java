package es.alrodmue.model.fouls;

public enum FoulType {
    FLAGRANT("Falta flagrante"),
    PERSONAL("Falta personal"),
    TECHNICAL("Falta técnica");

    private final String label;
    private FoulType(String label) { this.label = label; }
    @Override public String toString() { return this.label; }
}
