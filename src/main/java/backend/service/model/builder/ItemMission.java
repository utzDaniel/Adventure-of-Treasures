package backend.service.model.builder;

import backend.service.interfaces.ICombinable;
import backend.service.interfaces.IMission;

import java.util.List;

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
    public boolean action() {
        return false;
    }

    @Override
    public boolean action(List<ICombinable> itens) {
        return action();
    }

    @Override
    public String getEffect() {
        return null;
    }

    @Override
    public String toString() {
        return "ItemMission{" +
                "mapGame='" + this.mapGame + "} " + super.toString();
    }
}
