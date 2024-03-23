package backend.service.infra;

import backend.service.interfaces.ICache;
import backend.service.model.*;

import java.util.Optional;

public final class CacheService {
    public static final ICache<Item> itemICache = new Cache<>(new ItemFactory());
    public static final ICache<Door> doorICache = new Cache<>(new DoorFactory());
    public static final ICache<MapGame> mapGameICache = new Cache<>(new MapGameFactory());

    private CacheService(){}

    public static Optional<Item> getItem(int id) {
        return itemICache.get(id);
    }

    public static void add(Item model) {
        itemICache.add(model);
    }

    public static Optional<Door> getDoor(int id) {
        return doorICache.get(id);
    }

    public static void add(Door model) {
        doorICache.add(model);
    }

    public static Optional<MapGame> getMapGame(int id) {
        return mapGameICache.get(id);
    }

    public static void add(MapGame model) {
        mapGameICache.add(model);
    }

}
