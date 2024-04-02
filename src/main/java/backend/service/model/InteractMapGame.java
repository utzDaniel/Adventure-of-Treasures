package backend.service.model;

import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class InteractMapGame {

    private final Map<ICoordinate, Door> doors;
    private final Map<ICoordinate, Item> itens;
    private final Map<ICoordinate, NPC> npcs;

    public InteractMapGame(Map<ICoordinate, Door> doors, Map<ICoordinate, Item> itens, Map<ICoordinate, NPC> npcs) {
        this.doors = doors;
        this.itens = itens;
        this.npcs = npcs;
    }

    public boolean isInteract(ICoordinate coordinate) {
        return this.doors.containsKey(coordinate) || this.itens.containsKey(coordinate) || this.npcs.containsKey(coordinate);
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
    }

    public void addItem(Item item) {
        this.itens.put(item.getCoordinate(), item);
    }

    public List<Item> getItens() {
        return this.itens.values().stream().toList();
    }

    public Optional<NPC> getNPC(ICoordinate coordinate) {
        return Optional.ofNullable(this.npcs.get(coordinate));
    }

    public void clear() {
        this.itens.clear();
    }

    @Override
    public String toString() {
        return """
                {
                    "doors": %s,
                    "itens": %s,
                    "npcs": "%s"
                }
                """.formatted(this.doors.values(), this.itens.values(), this.npcs.values());
    }
}
