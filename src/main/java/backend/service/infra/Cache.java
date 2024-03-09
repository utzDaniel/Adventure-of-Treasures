package backend.service.infra;

import backend.Game;
import backend.repository.interfaces.IDoorEntity;
import backend.repository.interfaces.IItemEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.DoorRepository;
import backend.repository.singleton.ItemRepository;
import backend.repository.singleton.MapGameRepository;
import backend.service.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Cache {

    // TODO usar o padrao Flyweight
    private static final Map<Integer, MapGame> mapGame = new HashMap<>();
    private static final Map<Integer, Door> doors = new HashMap<>();
    private static final Map<Integer, Item> itens = new HashMap<>();

    private Cache() {
    }

    private static void addMapGame(MapGame mapGame) {
        Cache.mapGame.put(mapGame.getId(), mapGame);
        mapGame.getItens().forEach(Cache::addItem);
        mapGame.getDoors().forEach(Cache::addDoor);
    }

    private static void addItem(Item item) {
        Cache.itens.put(item.getId(), item);
    }
    private static void addDoor(Door door) {
        Cache.doors.put(door.getId(), door);
    }

    public static Optional<MapGame> getMapGame(int idMap) {
        var mapGame1 = Optional.ofNullable(Cache.mapGame.get(idMap));
        if (mapGame1.isEmpty()) {
            var entity = getMapGameEntity(idMap);
            if (entity.isEmpty()) return Optional.empty();
            mapGame1 = create(entity.get());
            mapGame1.ifPresent(Cache::addMapGame);
        }
        return mapGame1;
    }

    public static Optional<Door> getDoor(int idDoor) {
        var door1 = Optional.ofNullable(Cache.doors.get(idDoor));
        if (door1.isEmpty()) {
            var entity = getDoorEntity(idDoor);
            if (entity.isEmpty()) return Optional.empty();
            door1 = create(entity.get());
            door1.ifPresent(Cache::addDoor);
        }
        return door1;
    }

    private static Optional<IDoorEntity> getDoorEntity(int idDoor) {
        return DoorRepository.getInstance().getById(idDoor);
    }

    private static Optional<IMapGameEntity> getMapGameEntity(int idMap) {
        return MapGameRepository.getInstance().getById(idMap);
    }

    private static Optional<MapGame> create(IMapGameEntity entity) {
        return Optional.of(MapGameFactory.create(entity));
    }

    public static Optional<Item> getItem(int idItem) {
        var item1 = Optional.ofNullable(Cache.itens.get(idItem));
        if (item1.isEmpty()) {
            var entity = getItemEntity(idItem);
            if (entity.isEmpty()) return Optional.empty();
            item1 = create(entity.get());
            item1.ifPresent(Cache::addItem);
        }
        return item1;
    }

    public static Inventory getInventory() {
        return Game.player.getInventory();
    }

    private static Optional<IItemEntity> getItemEntity(int idItem) {
        return ItemRepository.getInstance().getById(idItem);
    }

    private static Optional<Item> create(IItemEntity entity) {
        return Optional.of(ItemFactory.create(entity));
    }

    private static Optional<Door> create(IDoorEntity entity) {
        return Optional.of(DoorFactory.create(entity));
    }


}
