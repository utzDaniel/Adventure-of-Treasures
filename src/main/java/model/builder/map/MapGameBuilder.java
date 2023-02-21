package model.builder.map;

import model.Coordinate;
import model.Door;
import model.builder.item.Item;
import model.interfaces.IBuilderMapGame;
import repository.RepositoryItem;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;

abstract class MapGameBuilder implements IBuilderMapGame {

    protected MapGame mapGame;
    protected Map<Coordinate, Door> doors;
    protected Map<Coordinate, Item> itens;

    @Override
    public IBuilderMapGame name(String name) {
        this.mapGame.setName(name);
        return this;
    }

    @Override
    public IBuilderMapGame image(String filename) {
        this.mapGame.setImage(new ImageIcon(filename));
        return this;
    }

    @Override
    public IBuilderMapGame limits(int[][] limits) {
        this.mapGame.setLimits(limits);
        return this;
    }

    @Override
    public IBuilderMapGame doors(Door door) {
        if (Objects.nonNull(door))
            this.doors.put(door.getCoordinate(), door);
        return this;
    }

    @Override
    public IBuilderMapGame itens(String name) {
        if (Objects.isNull(name))
            return this;
        Item item = RepositoryItem.getInstance().getItem(name);
        this.itens.put(item.getLocation(), item);
        return this;
    }

    @Override
    public MapGame build() {
        this.mapGame.setDoors(doors);
        this.mapGame.setItens(itens);
        return this.mapGame;
    }
}
