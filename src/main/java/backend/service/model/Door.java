package backend.service.model;

import backend.service.interfaces.ICoordinate;

public final class Door {

    private final int id;
    private final int idMapGame;
    private final ICoordinate coordinate;
    private boolean open;

    public Door(int id, int idMapGame, ICoordinate coordinate, boolean open) {
        this.id = id;
        this.idMapGame = idMapGame;
        this.coordinate = coordinate;
        this.open = open;
    }

    public int getId() {
        return this.id;
    }

    public int getIdMapGame() {
        return this.idMapGame;
    }

    public ICoordinate getCoordinate() {
        return this.coordinate;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return this.open;
    }

    public boolean isDoor(ICoordinate coordinate) {
        return this.coordinate.equals(coordinate);
    }

    public boolean isMap(int idMapGame) {
        return this.idMapGame == idMapGame;
    }

    @Override
    public String toString() {
        return """
                {
                    "id": "%d",
                    "idMapGame": "%d",
                    "coordinate": %s,
                    "open": %b
                }
                """.formatted(this.id, this.idMapGame, this.coordinate.toString(), this.open);
    }
}
