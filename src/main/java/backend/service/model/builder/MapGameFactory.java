package backend.service.model.builder;

import backend.repository.factory.RepositoryFactory;
import backend.repository.interfaces.IMapGameEntity;
import backend.service.interfaces.IBuilderMapGame;
import backend.service.model.Door;
import backend.service.model.DoorFactory;
import backend.service.model.Exit;
import backend.service.model.ExitFactory;

import java.util.List;

public final class MapGameFactory {
    private IMapGameEntity mapGameEntity;

    public MapGame create(IMapGameEntity mapGameEntity) {
        this.mapGameEntity = mapGameEntity;
        return type();
    }

    private MapGame type() {
        return isScenery() ? createScenery() : createRoom();
    }

    private boolean isScenery() {
        return !getExits().isEmpty();
    }

    private IBuilderMapGame inicial(IBuilderMapGame mapGame) {
        return mapGame.name(mapGameEntity.name())
                .image(mapGameEntity.imagemIcon())
                .doors(getDoors())
                .itens(getItens())
                .song(mapGameEntity.song())
                .limits(mapGameEntity.limits());
    }

    private MapGame createRoom() {
        return inicial(RoomBuilder
                .builder())
                .build();
    }

    private MapGame createScenery() {
        return inicial(SceneryBuilder
                .builder()
                .exits(getExits()))
                .build();
    }

    private List<Item> getItens() {
        return RepositoryFactory.getRepositoryItem().getAll()
                .stream().filter(item -> isValid(item.idMapGame()) && item.idMapGame() == mapGameEntity.id())
                .map(itemEntity -> new ItemFactory().create(itemEntity))
                .toList();
    }

    private List<Exit> getExits() {
        return RepositoryFactory.getRepositoryExit().getAll()
                .stream().filter(exit -> isValid(exit.idMapGame()) && exit.idMapGame() == mapGameEntity.id())
                .map(exitEntity -> new ExitFactory().create(exitEntity))
                .toList();
    }

    private List<Door> getDoors() {
        return RepositoryFactory.getRepositoryDoor().getAll()
                .stream().filter(door -> isValid(door.idMapGame()) && door.idMapGame() == mapGameEntity.id())
                .map(doorEntity -> new DoorFactory().create(doorEntity))
                .toList();
    }

    private boolean isValid(int key) {
        return key != -1;
    }
}
