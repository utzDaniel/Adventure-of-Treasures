package model;

import java.awt.*;

public final class Door {
    private final String mapGame;
    private final Coordinate coordinate;

    private boolean open;

    public Door(String mapGame, Coordinate coordinate, boolean open){
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

    public boolean isDoor(Point point) {
        return this.coordinate.getX()*10 == point.x && this.coordinate.getY()*10 == point.y;
    }

    public boolean isMap(String map) {
        return this.mapGame.equals(map);
    }

    @Override
    public String toString() {
        return "Door{" +
                "mapGame='" + mapGame + '\'' +
                ", coordinate=" + coordinate +
                ", open=" + open +
                '}';
    }
}
