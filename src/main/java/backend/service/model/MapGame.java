package backend.service.model;

import backend.repository.interfaces.IMapGameEntity;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class MapGame {
    private final IMapGameEntity entity;
    private String image;
    private final NPC npc;
    private final Area area;
    private final Map<ICoordinate, Door> doors;
    private final Map<ICoordinate, Item> itens;
    private final List<Exit> exits;

    public MapGame(IMapGameEntity entity, Map<ICoordinate, Door> doors, Map<ICoordinate, Item> itens, List<Exit> exits, NPC npc) {
        this.entity = entity;
        this.image = entity.image();
        this.doors = doors;
        this.itens = itens;
        this.exits = exits;
        this.npc = npc;
        this.area = new Area(entity.limits());
        itens.keySet().forEach(this.area::block);
    }

    public int getId() {
        return this.entity.id();
    }

    public String getName() {
        return this.entity.name();
    }

    public String getSong() {
        return this.entity.song();
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

    public Optional<Door> getDoorByMap(int idMapGame) {
        return this.doors.values().stream()
                .filter(o -> o.isMap(idMapGame))
                .findFirst();
    }

    public Optional<Door> getDoor(int idDoor) {
        return this.doors.values().stream()
                .filter(v -> v.getId() == idDoor)
                .findFirst();
    }

    public Item getItem(ICoordinate coordinate) {
        return this.itens.get(coordinate);
    }

    public void removeItem(Item item) {
        this.itens.remove(item.getCoordinate());
        this.area.unlock(item.getCoordinate());
    }

    public void addItem(Item item) {
        this.itens.put(item.getCoordinate(), item);
        this.area.block(item.getCoordinate());
    }

    public List<Item> getItens() {
        return this.itens.values().stream().toList();
    }

    public List<Door> getDoors() {
        return this.doors.values().stream().toList();
    }


    public Optional<Integer> getExit(Move move) {
        return this.exits.stream()
                .filter(exit -> exit.direction().name().equals(move.name()))
                .map(Exit::idMap)
                .findFirst();
    }

    public boolean invalidCoordinate(ICoordinate coordinate) {
        return Area.isLimit(coordinate) ||
                !this.getArea().isAccessible(coordinate);
    }

    public int areaSize() {
        return this.entity.limits().length * this.entity.limits()[0].length;
    }

    public Optional<NPC> getNPC() {
        return Optional.of(this.npc);
    }

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "name": "%s",
                    "image": "%s",
                    "area": %s,
                    "doors": %s,
                    "itens": %s,
                    "song": "%s",
                    "exits": "%s"
                }
                """.formatted(this.entity.id(), this.entity.name(), this.image, this.area, this.doors.values(),
                this.itens.values(), this.entity.song(), this.exits);
    }
}
