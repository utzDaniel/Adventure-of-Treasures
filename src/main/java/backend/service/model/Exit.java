package backend.service.model;

public final class Exit {
    private String direction;
    private String mapGame;

    private Exit() {
    }

    public Exit(String direction, String mapGame) {
        this.direction = direction;
        this.mapGame = mapGame;
    }

    public String getDirection() {
        return direction;
    }

    public String getMapGame() {
        return mapGame;
    }


}
