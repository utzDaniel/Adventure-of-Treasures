package model;

public enum Direction {
    NORTE("norte"),SUL("sul"), OESTE("oeste"),LESTE("leste");

    private final String label;

    public String getLabel() {
        return label;
    }

    Direction(String label) {
        this.label = label;
    }
}
