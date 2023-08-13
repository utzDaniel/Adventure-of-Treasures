package backend.service.model;

import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class MapGame {
    private final int id;
    private final String name;
    private final String song;
    private String image;
    private final Area area;
    private final Map<ICoordinate, Door> doors;
    private final Map<ICoordinate, Item> itens;
    private final List<Exit> exits;

    MapGame(int id, String name, String song, String image, Area area, Map<ICoordinate, Door> doors, Map<ICoordinate, Item> itens, List<Exit> exits) {
        this.id = id;
        this.name = name;
        this.song = song;
        this.image = image;
        this.area = area;
        this.doors = doors;
        this.itens = itens;
        this.exits = exits;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {//Apenas a class ActivateMapGame usar o publico
        this.image = image;
    }

    public Area getArea() {
        return this.area;
    }

    public Optional<Door> getDoor(ICoordinate coordinate) {
        return this.doors.values().stream()
                .filter(o -> o.isDoor(coordinate))
                .findFirst();
    }

    public Optional<Door> getDoor(int idMapGame) {
        return this.doors.values().stream()
                .filter(o -> o.isMap(idMapGame))
                .findFirst();
    }

    public Item getItem(ICoordinate coordinate) {
        return this.itens.get(coordinate);
    }

    public void removeItem(Item item) {
        this.itens.remove(item.getCoordinate());
        //TODO ao pegar um item em um local que não deveria ter como o player passar, está podendeo passar. EX: pegar o item papel, na mesa
        this.area.unlock(item.getCoordinate());
    }

    public void addItem(Item item) {
        this.itens.put(item.getCoordinate(), item);
        this.area.block(item.getCoordinate());
    }

    public List<Item> getItens() {
        return this.itens.values().stream().toList();
    }

    public String getSong() {
        return this.song;
    }

    public Optional<Integer> getExit(Move move) {
        return exits.stream()
                .filter(exit -> exit.direction().name().equals(move.name()))
                .map(Exit::idMap)
                .findFirst();
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
                    "song": "%s",
                    "exits": "%s"
                }
                """.formatted(this.name, this.image, this.area, this.doors.values(),
                this.itens.values(), this.song, this.exits);
    }
}
