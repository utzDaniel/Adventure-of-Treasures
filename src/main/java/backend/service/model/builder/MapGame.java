package backend.service.model.builder;

import backend.service.interfaces.ICoordinate;
import backend.service.model.Area;
import backend.service.model.Door;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MapGame {
    private String name;
    private String song;
    private ImageIcon icon;
    private Area area;
    private Map<ICoordinate, Door> doors;
    private Map<ICoordinate, Item> itens;

    protected MapGame() {
        this.doors = new HashMap<>();
        this.itens = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    public void setIcon(ImageIcon imagemIcon) {//Apenas a class ActivateMapGame usar o publico
        this.icon = imagemIcon;
    }

    public Area getArea() {
        return this.area;
    }

    protected void setLimits(int[][] limits) {
        this.area = new Area(limits);
    }

    public Optional<Door> getDoor(ICoordinate coordinate) {
        return this.doors.values().stream()
                .filter(o -> o.isDoor(coordinate))
                .findFirst();
    }

    public Optional<Door> getDoor(String map) {
        return this.doors.values().stream()
                .filter(o -> o.isMap(map))
                .findFirst();
    }

    protected void setDoors(Map<ICoordinate, Door> doors) {
        this.doors = doors;
    }

    public Item getItem(ICoordinate coordinate) {
        return this.itens.get(coordinate);
    }

    public void removeItem(Item item) {
        this.itens.remove(item.getLocation());
        //TODO ao pegar um item em um local que não deveria ter como o player passar, está podendeo passar. EX: pegar o item papel, na mesa
        this.area.unlock(item.getLocation());
    }

    public void addItem(Item item) {
        this.itens.put(item.getLocation(), item);
        this.area.block(item.getLocation());
    }

    protected void setItens(Map<ICoordinate, Item> itens) {
        this.itens = itens;
        this.itens.keySet().forEach(c -> this.area.block(c));
    }

    public List<Item> getItemVisible() {
        return this.itens.values().stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public List<Item> getItemInvisible() {
        return this.itens.values().stream()
                .filter(Item::isInvisible)
                .collect(Collectors.toList());
    }

    public String getSong() {
        return this.song;
    }

    protected void setSong(String filename) {
        this.song = filename;
    }

    @Override
    public String toString() {
        return """
                {
                    "name": "%s",
                    "icon": "%s",
                    "area": %s,
                    "doors": %s,
                    "itens": %s,
                    "song": "%s"
                }
                """.formatted(this.name, this.icon.toString(), this.area, this.doors.values(),
                this.itens.values(), this.song);
    }
}
