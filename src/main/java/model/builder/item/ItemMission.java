package model.builder.item;

import model.MapGame;

import javax.swing.*;
import java.util.List;

public final class ItemMission extends Item {

    private String mapGame;

    protected ItemMission() { }

    public String getMapGame() {
        return mapGame;
    }

    public void setMapGame(String name) {
        this.mapGame = name;
    }

    @Override
    public boolean action(Item item) {
        return false;
    }

    @Override
    public boolean action(List<Item> itens) {
        return action(itens.get(0));
    }

    @Override
    public String toString() {
        return "ItemMission{" +
                "mapGame='" + mapGame + '\'' +
                "} " + super.toString();
    }
}
