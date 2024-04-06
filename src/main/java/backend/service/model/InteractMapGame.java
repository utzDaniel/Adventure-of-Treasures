package backend.service.model;

import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class InteractMapGame {

    private final Map<ICoordinate, Door> doors;
    private final Map<ICoordinate, Item> items;
    private final Map<ICoordinate, NPC> npcs;

    public InteractMapGame(Map<ICoordinate, Door> doors, Map<ICoordinate, Item> items, Map<ICoordinate, NPC> npcs) {
        this.doors = doors;
        this.items = items;
        this.npcs = npcs;
    }

    public boolean isInteract(ICoordinate coordinate) {
        return this.doors.containsKey(coordinate) || this.items.containsKey(coordinate) || this.npcs.containsKey(coordinate);
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
                .filter(v -> v.id() == idDoor)
                .findFirst();
    }

    public Item getItem(ICoordinate coordinate) {
        return this.items.get(coordinate);
    }

    public void removeItem(Item item) {
        this.items.remove(item.getCoordinate());
    }

    public void addItem(Item item) {
        this.items.put(item.getCoordinate(), item);
    }

    public List<Item> getItems() {
        return this.items.values().stream().toList();
    }

    public Optional<NPC> getNPC(ICoordinate coordinate) {
        return Optional.ofNullable(this.npcs.get(coordinate));
    }

    public void clear() {
        this.items.clear();
    }

    @Override
    public String toString() {
        return """
                {
                    "doors": %s,
                    "items": %s,
                    "npcs": "%s"
                }
                """.formatted(this.doors.values(), this.items.values(), this.npcs.values());
    }
}
