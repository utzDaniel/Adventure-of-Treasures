package backend.service.model;

import backend.repository.interfaces.IEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.*;
import backend.service.enums.Direction;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IFactory;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MapGameFactory implements IFactory<MapGame> {

    @Override
    public Optional<MapGame> create(int id) {
        return getEntity(id).map(this::create);
    }

    @Override
    public MapGame create(IEntity entity) {
        if (entity instanceof IMapGameEntity mapGameEntity) {
            return new MapGame(
                    mapGameEntity,
                    getExits(mapGameEntity),
                    getInteractMapGame(mapGameEntity));
        }
        return null;
    }

    private static Optional<IMapGameEntity> getEntity(int id) {
        return MapGameRepository.getInstance().getById(id);
    }

    private static Map<Direction, IExitEntity> getExits(IMapGameEntity mapGameEntity) {
        return ExitRepository.getInstance()
                .getByIdMapOri(mapGameEntity.id())
                .stream()
                .map(exit -> Map.entry(Direction.valueOf(exit.direction().toUpperCase()), exit))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static InteractMapGame getInteractMapGame(IMapGameEntity mapGameEntity) {
        return new InteractMapGame(getDoors(mapGameEntity), getItems(mapGameEntity), getNPCs(mapGameEntity));
    }

    private static Map<ICoordinate, Item> getItems(IMapGameEntity mapGameEntity) {
        return ItemMapRepository.getInstance().getByIdMap(mapGameEntity.id())
                .stream()
                .map(v -> CacheService.itemICache.get(v.idItem()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Item::getCoordinate, item1 -> item1));
    }

    private static Map<ICoordinate, Door> getDoors(IMapGameEntity mapGameEntity) {
        return DoorRepository.getInstance()
                .getByIdMapOri(mapGameEntity.id())
                .stream()
                .map(v -> CacheService.getDoor(v.id()).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Door::getCoordinate, door1 -> door1));
    }

    private static Map<ICoordinate, NPC> getNPCs(IMapGameEntity mapGameEntity) {
        return NPCRepository.getInstance()
                .getByIdMap(mapGameEntity.id())
                .stream()
                .map(NPC::new)
                .collect(Collectors.toMap(NPC::getCoordinate, npc1 -> npc1));
    }

}
