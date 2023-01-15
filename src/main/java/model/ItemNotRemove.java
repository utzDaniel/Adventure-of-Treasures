package model;

import javax.swing.*;
import java.util.List;

public final class ItemNotRemove extends Item {

    //model.Item tem o atributo remove como sempre true

    //apenas o item mapa usa esse metodo
    private MapGame mapGame;

    public ItemNotRemove(String name, String description, MapGame mapGame, int weight, int positionItemX, int positionItemY, ImageIcon imagemIcon) {
        super(name, description, weight, positionItemX, positionItemY, imagemIcon);
        this.mapGame = mapGame;
    }

    //apenas o item mapa usa esse metodo
    public MapGame getMapGame() {
        return mapGame;
    }

    //apenas o item mapa usa esse metodo
    public void setMapGame(MapGame mapGame) {
        this.mapGame = mapGame;
    }

    @Override
    public boolean action(Item item) {
        return false;
    }

    @Override
    public boolean action(List<Item> itens) {
        return action(itens.get(0));
    }
}
