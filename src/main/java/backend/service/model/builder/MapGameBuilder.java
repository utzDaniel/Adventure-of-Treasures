package backend.service.model.builder;

import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IBuilderMapGame;
import backend.service.model.Door;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

abstract class MapGameBuilder implements IBuilderMapGame {

    protected MapGame mapGame;
    protected Map<ICoordinate, Door> doors;
    protected Map<ICoordinate, Item> itens;

    @Override
    public IBuilderMapGame id(int id) {
        this.mapGame.setId(id);
        return this;
    }
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
    public IBuilderMapGame limits(byte[][] limits) {
        this.mapGame.setLimits(limits);
        return this;
    }

    @Override
    public IBuilderMapGame doors(List<Door> doors) {
        if (Objects.nonNull(doors) && doors.size() > 0)
            this.doors.putAll(getMapDoors(doors));
        return this;
    }

    @Override
    public IBuilderMapGame itens(List<Item> itens) {
        if (Objects.nonNull(itens) && itens.size() > 0)
            this.itens.putAll(getMapItem(itens));
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

    Map<? extends ICoordinate, Item> getMapItem(List<Item> itens) {
        return itens.stream()
                .collect(Collectors.toMap(Item::getLocation, item1 -> item1));
    }

    private Map<? extends ICoordinate, Door> getMapDoors(List<Door> doors) {
        return doors.stream()
                .collect(Collectors.toMap(Door::getCoordinate, door1 -> door1));
    }
}
