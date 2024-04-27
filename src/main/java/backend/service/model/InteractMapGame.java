package backend.service.model;

import backend.controller.interfaces.IInteract;
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

    public Optional<IInteract> get(ICoordinate coordinate) {
        if (this.npcs.containsKey(coordinate)) {
            return Optional.of(this.npcs.get(coordinate));
        }
        if (this.doors.containsKey(coordinate)) {
            return Optional.of(this.doors.get(coordinate));
        }
        if (this.items.containsKey(coordinate)) {
            return Optional.of(this.items.get(coordinate));
        }
        return Optional.empty();
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
