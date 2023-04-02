package backend.model;

import rules.interfaces.ICoordinate;

public final class Door {

    private final String mapGame;
    private final ICoordinate coordinate;
    private boolean open;

    public Door(String mapGame, ICoordinate coordinate, boolean open) {
        this.mapGame = mapGame;
        this.coordinate = coordinate;
        this.open = open;
    }

    public String getMapGame() {
        return this.mapGame;
    }

    public ICoordinate getCoordinate() {
        return this.coordinate;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }

    public boolean isDoor(ICoordinate coordinate) {
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
