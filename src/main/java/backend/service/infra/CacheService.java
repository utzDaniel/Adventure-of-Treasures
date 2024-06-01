package backend.service.infra;

import backend.service.interfaces.ICache;
import backend.service.memento.DoorMemento;
import backend.service.memento.ItemMemento;
import backend.service.memento.MapGameMemento;
import backend.service.model.*;

import java.util.List;
import java.util.Optional;

public final class CacheService {

    public static final ICache<Item, ItemMemento> itemICache = new Cache<>(new ItemFactory());
    public static final ICache<Door, DoorMemento> doorICache = new Cache<>(new DoorFactory());
    public static final ICache<MapGame, MapGameMemento> mapGameICache = new Cache<>(new MapGameFactory());

    private CacheService() {
    }

    public static Optional<Item> getItem(int id) {
        return itemICache.get(id);
    }

    public static Optional<Door> getDoor(int id) {
        return doorICache.get(id);
    }

    public static List<Door> getAllDoor() {
        return doorICache.getAll().stream().toList();
    }

    public static Optional<MapGame> getMapGame(int id) {
        return mapGameICache.get(id);
    }

    public static List<MapGame> getAllMapGame() {
        return mapGameICache.getAll().stream().toList();
    }

    public static void clearAll() {
        mapGameICache.clear();
        doorICache.clear();
        itemICache.clear();
    }

}
