package backend.service.model;

import backend.repository.interfaces.IEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.service.enums.Direction;
import backend.service.infra.CacheService;
import backend.service.interfaces.IBackup;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IImage;
import backend.service.memento.MapGameMemento;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MapGame implements IImage, IEntity, IBackup<MapGameMemento> {
    private final IMapGameEntity entity;
    private String image;
    private final Area area;
    private final InteractMapGame interact;
    private final Map<Direction, IExitEntity> exits;

    public MapGame(IMapGameEntity entity, Map<Direction, IExitEntity> exits, InteractMapGame interact) {
        this.entity = entity;
        this.image = entity.image();
        this.exits = exits;
        this.interact = interact;
        this.area = new Area(entity.limits());
        this.interact.getItems().stream()
                .map(Item::getCoordinate)
                .forEach(this.area::block);
    }

    @Override
    public int id() {
        return this.entity.id();
    }

    public String getName() {
        return this.entity.name();
    }

    public String getSong() {
        return this.entity.song();
    }

    @Override
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {//Apenas a class ActivateMapGame usar o publico
        this.image = image;
    }

    public boolean isInteract(ICoordinate coordinate) {
        return this.interact.isInteract(coordinate);
    }

    public Optional<Door> getDoor(ICoordinate coordinate) {
        return this.interact.getDoor(coordinate);
    }

    public Optional<Door> getDoorByMap(int idMapGame) {
        return this.interact.getDoorByMap(idMapGame);
    }

    public Optional<Door> getDoor(int idDoor) {
        return this.interact.getDoor(idDoor);
    }

    public Item getItem(ICoordinate coordinate) {
        return this.interact.getItem(coordinate);
    }

    public void removeItem(Item item) {
        this.interact.removeItem(item);
        this.area.unlock(item.getCoordinate());
    }

    public void addItem(Item item) {
        this.interact.addItem(item);
        this.area.block(item.getCoordinate());
    }

    public List<Item> getItems() {
        return this.interact.getItems();
    }

    public Optional<NPC> getNPC(ICoordinate coordinate) {
        return this.interact.getNPC(coordinate);
    }

    public Optional<Integer> getExit(Direction direction) {
        return Objects.isNull(this.exits.get(direction)) ? Optional.empty() : Optional.of(this.exits.get(direction).idMapExt());
    }

    public Area getArea() {
        return this.area;
    }

    public boolean isNextScenery(ICoordinate coordinate) {
        return this.area.outside(coordinate);
    }

    public ICoordinate nextScenery(ICoordinate coordinate) {
        return this.area.next(coordinate);
    }

    public boolean invalidCoordinate(ICoordinate coordinate) {
        return this.area.outside(coordinate) || this.getArea().blocked(coordinate);
    }

    public int areaSize() {
        return this.entity.limits().length * this.entity.limits()[0].length;
    }

    @Override
    public String toString() {
        return """
                {
                    "id": %d,
                    "name": "%s",
                    "image": "%s",
                    "song": "%s",
                    "exits": "%s",
                    "area": %s,
                    %s
                }
                """.formatted(this.entity.id(), this.entity.name(), this.image, this.entity.song(), this.exits,
                this.area, this.interact.toString());
    }

    @Override
    public MapGameMemento save() {
        return new MapGameMemento(this.entity.id(), this.image,
                this.interact.getItems().stream().map(Item::getId).collect(Collectors.toSet()));
    }

    @Override
    public void restore(MapGameMemento memento) {
        this.image = memento.image();
        this.area.clear();
        this.interact.clear();
        memento.idItems()
                .stream()
                .map(id -> CacheService.getItem(id).orElse(null))
                .filter(Objects::nonNull)
                .forEach(this::addItem);
    }
}
