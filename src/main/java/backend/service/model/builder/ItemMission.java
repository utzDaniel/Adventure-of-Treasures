package backend.service.model.builder;

import backend.service.interfaces.IMission;

public final class ItemMission extends Item implements IMission {

    private String mapGame;

    ItemMission() {
    }

    @Override
    public String getMapGame() {
        return this.mapGame;
    }

    public void setMapGame(String name) {
        this.mapGame = name;
    }

    @Override
    public String toString() {
        return """
                Item: %s
                {
                    "mapGame": "%s"
                }
                """.formatted(super.toString(), this.mapGame);
    }
}
