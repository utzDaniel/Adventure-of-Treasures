package backend.service.model;

import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.DoorRepository;
import backend.repository.singleton.ExitRepository;
import backend.repository.singleton.ItemMapRepository;
import backend.repository.singleton.ItemRepository;
import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class MapGameFactory {

    private MapGameFactory() {
    }

    public static MapGame create(IMapGameEntity entity) {
        return new MapGame(
                entity,
                getDoors(entity),
                getItens(entity),
                getExits(entity));
    }

    private static Map<ICoordinate, Item> getItens(IMapGameEntity mapGameEntity) {
        return ItemMapRepository.getInstance().getByIdMap(mapGameEntity.id())
                .stream()
                .map(v -> ItemRepository.getInstance().getById(v.idItem()).orElse(null))
                .filter(Objects::nonNull)
                .map(ItemFactory::create)
                .collect(Collectors.toMap(Item::getCoordinate, item1 -> item1));
    }

    private static List<Exit> getExits(IMapGameEntity mapGameEntity) {
        return ExitRepository.getInstance()
                .getByIdMapOri(mapGameEntity.id())
                .stream()
                .map(ExitFactory::create)
                .toList();
    }

    private static Map<ICoordinate, Door> getDoors(IMapGameEntity mapGameEntity) {
        return DoorRepository.getInstance()
                .getByIdMapOri(mapGameEntity.id())
                .stream()
                .map(DoorFactory::create)
                .collect(Collectors.toMap(Door::getCoordinate, door1 -> door1));
    }

}
