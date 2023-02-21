package model;

public final class Door {

    private final String mapGame;
    private final Coordinate coordinate;
    private boolean open;

    public Door(String mapGame, Coordinate coordinate, boolean open) {
        this.mapGame = mapGame;
        this.coordinate = coordinate;
        this.open = open;
    }

    public String getMapGame() {
        return this.mapGame;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isDoor(Coordinate coordinate) {
        return this.coordinate.equals(coordinate);
    }

    public boolean isMap(String map) {
        return this.mapGame.equals(map);
    }

    @Override
    public String toString() {
        return "Door{" +
                "mapGame='" + this.mapGame +
                ", coordinate=" + this.coordinate +
                ", open=" + this.open +
                '}';
    }
}
