package backend.service.model.builder;

import backend.repository.interfaces.IItemMapEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.DoorRepository;
import backend.repository.singleton.ExitRepository;
import backend.repository.singleton.ItemMapRepository;
import backend.repository.singleton.ItemRepository;
import backend.service.interfaces.IBuilderMapGame;
import backend.service.model.Door;
import backend.service.model.DoorFactory;
import backend.service.model.Exit;
import backend.service.model.ExitFactory;

import java.util.ArrayList;
import java.util.List;

public final class MapGameFactory {
    public MapGame create(IMapGameEntity mapGameEntity) {
        return isScenery(mapGameEntity) ? createScenery(mapGameEntity) : createRoom(mapGameEntity);
    }

    private boolean isScenery(IMapGameEntity mapGameEntity) {
        return !getExits(mapGameEntity).isEmpty();
    }

    private MapGame createRoom(IMapGameEntity mapGameEntity) {
        RoomBuilder builder = RoomBuilder.builder();
        return buildMapGame(builder, mapGameEntity);
    }

    private MapGame createScenery(IMapGameEntity mapGameEntity) {
        SceneryBuilder builder = SceneryBuilder.builder();
        builder.exits(getExits(mapGameEntity));
        return buildMapGame(builder, mapGameEntity);
    }

    private MapGame buildMapGame(IBuilderMapGame builder, IMapGameEntity mapGameEntity) {
        return builder.id(mapGameEntity.id())
                .name(mapGameEntity.name())
                .image(mapGameEntity.image())
                .doors(getDoors(mapGameEntity))
                .itens(getItens(mapGameEntity))
                .song(mapGameEntity.song())
                .limits(mapGameEntity.limits())
                .build();
    }

    private List<Item> getItens(IMapGameEntity mapGameEntity) {
        var idMap = mapGameEntity.id();
        return ItemMapRepository.getInstance().getByIdMap(idMap)
                .stream()
                .map(v -> ItemRepository.getInstance().getById(v.idItem()))
                .map(ItemFactory::create)
                .toList();
    }

    private List<Exit> getExits(IMapGameEntity mapGameEntity) {
        return ExitRepository.getInstance()
                .getByIdMapOri(mapGameEntity.id())
                .stream()
                .map(ExitFactory::create)
                .toList();
    }

    private List<Door> getDoors(IMapGameEntity mapGameEntity) {
        return DoorRepository.getInstance()
                .getByIdMapOri(mapGameEntity.id())
                .stream()
                .map(DoorFactory::create)
                .toList();
    }

}
