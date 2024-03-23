package backend.service.model;

import backend.repository.interfaces.*;
import backend.repository.singleton.*;
import backend.service.event.EventDoor;
import backend.service.event.EventInventory;
import backend.service.event.EventItem;
import backend.service.event.EventMap;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IFactory;
import backend.service.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ItemFactory implements IFactory<Item> {

    @Override
    public Optional<Item> create(int id) {
        return getEntity(id).map(this::create);
    }

    @Override
    public Item create(IEntity entity) {
        if(entity instanceof IItemEntity itemEntity){
            return new Item(itemEntity, getSpecializationComposite(entity.id()), getObservers(entity.id()));
        }
        return null;
    }

    private SpecializationComposite getSpecializationComposite(int id) {
        var composite = new SpecializationComposite();

        var comb = getCombinable(id);
        if (!comb.isEmpty()) {
            composite.add(new Combinable(comb.get(0).combination(), comb.size()));
        }

        getEquipable(id).ifPresent(e -> composite.add(new Equipable(e.upCapacity())));

        getUsable(id).ifPresent(e -> composite.add(new Usable(e.idMap(), ICoordinate.getInstance(e.positionX(), e.positionY()), e.enabled())));

        getMission(id).ifPresent(e -> composite.add(new Mission()));

        return composite;
    }

    private List<IObserver> getObservers(int id) {
        var observers = new ArrayList<IObserver>();
        getEventMap(id).ifPresent(e -> observers.add(new EventMap(e)));

        getEventInventory(id).ifPresent(e -> observers.add(new EventInventory(e)));

        getEventDoor(id).ifPresent(e -> observers.add(new EventDoor(e)));

        getEventItem(id).ifPresent(e -> observers.add(new EventItem(e)));

        return observers;
    }

    private Optional<IItemEntity> getEntity(int id) {
        return ItemRepository.getInstance().getById(id);
    }

    private List<ICombinableEntity> getCombinable(int id) {
        return CombinableRepository.getInstance().getByIdItem(id);
    }

    private Optional<IEquipableEntity> getEquipable(int id) {
        return EquipableRepository.getInstance().getByIdItem(id);
    }

    private Optional<IUsableEntity> getUsable(int id) {
        return UsableRepository.getInstance().getByIdItem(id);
    }

    private Optional<IMissionEntity> getMission(int id) {
        return MissionRepository.getInstance().getByIdItem(id);
    }

    private Optional<IEventMapEntity> getEventMap(int id) {
        return EventMapRepository.getInstance().getByIdItem(id);
    }

    private Optional<IEventInventoryEntity> getEventInventory(int id) {
        return EventInventoryRepository.getInstance().getByIdItem(id);
    }

    private Optional<IEventDoorEntity> getEventDoor(int id) {
        return EventIDoorRepository.getInstance().getByIdItem(id);
    }

    private Optional<IEventItemEntity> getEventItem(int id) {
        return EventItemRepository.getInstance().getByIdItem(id);
    }
}
