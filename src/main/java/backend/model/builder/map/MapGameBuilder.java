package backend.model.builder.map;

import backend.model.Door;
import backend.model.builder.item.Item;
import backend.model.interfaces.IBuilderMapGame;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;

import javax.swing.*;
import java.util.Map;
import java.util.Objects;

abstract class MapGameBuilder implements IBuilderMapGame {

    protected MapGame mapGame;
    protected Map<ICoordinate, Door> doors;
    protected Map<ICoordinate, Item> itens;

    @Override
    public IBuilderMapGame name(String name) {
        this.mapGame.setName(name);
        return this;
    }

    @Override
    public IBuilderMapGame image(String filename) {
        this.mapGame.setIcon(new ImageIcon(filename));
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
        Item item = RepositoryFactory.getRepositoryItem().get(name);
        this.itens.put(item.getLocation(), item);
        return this;
    }

    @Override
    public IBuilderMapGame song(String filename) {
        this.mapGame.setSong(filename);
        return this;
    }


    @Override
    public MapGame build() {
        this.mapGame.setDoors(doors);
        this.mapGame.setItens(itens);
        return this.mapGame;
    }
}
