package backend.service.model;

import backend.service.interfaces.ICoordinate;

public final class Door {

    private final int idMapGame;
    private final ICoordinate coordinate;
    private boolean open;

    public Door(int idMapGame, ICoordinate coordinate, boolean open) {
        this.idMapGame = idMapGame;
        this.coordinate = coordinate;
        this.open = open;
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
        return open;
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
                    "idMapGame": "%d",
                    "coordinate": %s,
                    "open": %b
                }
                """.formatted(this.idMapGame, this.coordinate.toString(), this.open);
    }
}
