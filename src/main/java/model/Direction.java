package model;

public enum Direction {
    NORTE("norte",38),SUL("sul",40),
    OESTE("oeste",37),LESTE("leste",39);

    private final String label;

    private final int keyCode;

    public String getLabel() {
        return label;
    }

    public int getKeyCode() {
        return keyCode;
    }

    Direction(String label, int keyCode) {
        this.label = label;
        this.keyCode = keyCode;
    }
}
