package backend.service.model;

import backend.controller.interfaces.IInteract;
import backend.repository.interfaces.IDecorationEntity;
import backend.repository.interfaces.IExitEntity;
import backend.repository.interfaces.IMapGameEntity;
import backend.repository.singleton.DecorationRepository;
import backend.service.enums.Direction;
import backend.service.infra.CacheService;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IImage;
import backend.service.interfaces.IMemento;
import backend.service.memento.MapGameMemento;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class MapGame implements IImage, IMemento<MapGameMemento> {
    private final IMapGameEntity entity;
    private final Area area;
    private final InteractMapGame interact;
    private final Map<Direction, IExitEntity> exits;
    private IDecorationEntity decoration;

    public MapGame(IMapGameEntity entity, Map<Direction, IExitEntity> exits, InteractMapGame interact) {
        this.entity = entity;
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
        return this.entity.image();
    }

    public boolean isInteract(ICoordinate coordinate, Direction direction) {
        var newCoordinate = ICoordinate.getInstance(coordinate);
        newCoordinate.move(direction.getMove());
        return this.interact.isInteract(newCoordinate);
    }

    public Optional<IInteract> getInteract(ICoordinate coordinate, Direction direction) {
        var newCoordinate = ICoordinate.getInstance(coordinate);
        newCoordinate.move(direction.getMove());
        return this.interact.get(newCoordinate);
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

    public void setDecoration(IDecorationEntity decoration) {
        this.decoration = decoration;
    }

    public Optional<IDecorationEntity> getDecoration() {
        return Optional.ofNullable(this.decoration);
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
                """.formatted(this.entity.id(), this.entity.name(), this.entity.image(), this.entity.song(), this.exits,
                this.area, this.interact.toString());
    }

    @Override
    public MapGameMemento save() {
        var idDecoration = Objects.isNull(this.decoration) ? -1 : this.decoration.id();
        return new MapGameMemento(this.entity.id(), idDecoration,
                this.interact.getItems().stream().map(Item::getId).collect(Collectors.toSet()));
    }

    @Override
    public void restore(MapGameMemento memento) {
        this.decoration = DecorationRepository.getInstance().getById(memento.idDecoration()).orElse(null);
        this.area.clear();
        this.interact.clear();
        memento.idItems()
                .stream()
                .map(id -> CacheService.getItem(id).orElse(null))
                .filter(Objects::nonNull)
                .forEach(this::addItem);
    }
}
