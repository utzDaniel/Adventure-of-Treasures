package backend.service.infra;

import backend.repository.interfaces.IItemEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.ItemRepository;
import backend.repository.singleton.MapGameRepository;
import backend.service.model.Item;
import backend.service.model.ItemFactory;
import backend.service.model.MapGame;
import backend.service.model.MapGameFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Cache {

    // TODO usar o padrao Flyweight
    private static final Map<Integer, MapGame> mapGame = new HashMap<>();
    private static final Map<Integer, Item> itens = new HashMap<>();

    private Cache() {
    }

    private static void addMapGame(MapGame mapGame) {
        Cache.mapGame.put(mapGame.getId(), mapGame);
        mapGame.getItens().forEach(Cache::addItem);
    }

    private static void addItem(Item item) {
        Cache.itens.put(item.getId(), item);
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

    private static Optional<IItemEntity> getItemEntity(int idItem) {
        return ItemRepository.getInstance().getById(idItem);
    }

    private static Optional<Item> create(IItemEntity entity) {
        return Optional.of(ItemFactory.create(entity));
    }


}
