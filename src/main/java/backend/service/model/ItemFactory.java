package backend.service.model;

import backend.repository.interfaces.*;
import backend.repository.singleton.*;
import backend.service.event.EventDoor;
import backend.service.event.EventInventory;
import backend.service.event.EventItem;
import backend.service.event.EventMap;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class ItemFactory {

    ItemFactory() {
    }

    public static Item create(IItemEntity itemEntity) {
        return new Item(itemEntity,
                getSpecializationComposite(itemEntity.id()),
                getObservers(itemEntity.id()));
    }

    private static SpecializationComposite getSpecializationComposite(int idItem) {
        var composite = new SpecializationComposite();

        var comb = getCombinable(idItem);
        if (!comb.isEmpty()) {
            composite.add(new Combinable(comb.get(0).combination(), comb.size()));
        }

        getEquipable(idItem)
                .ifPresent(e -> composite.add(new Equipable(e.upCapacity())));

        getUsable(idItem)
                .ifPresent(e -> composite.add(new Usable(e.idMap(), ICoordinate.getInstance(e.positionX(), e.positionY()), e.enabled())));

        getMission(idItem)
                .ifPresent(e -> composite.add(new Mission()));

        return composite;
    }

    private static List<IObserver> getObservers(int idItem) {
        var observers = new ArrayList<IObserver>();
        getEventMap(idItem)
                .ifPresent(e -> observers.add(new EventMap(e)));

        getEventInventory(idItem)
                .ifPresent(e -> observers.add(new EventInventory(e)));

        getEventDoor(idItem)
                .ifPresent(e -> observers.add(new EventDoor(e)));

        getEventItem(idItem)
                .ifPresent(e -> observers.add(new EventItem(e)));

        return observers;
    }

    private static List<ICombinableEntity> getCombinable(int idItem) {
        return CombinableRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IEquipableEntity> getEquipable(int idItem) {
        return EquipableRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IUsableEntity> getUsable(int idItem) {
        return UsableRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IMissionEntity> getMission(int idItem) {
        return MissionRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IEventMapEntity> getEventMap(int idItem) {
        return EventMapRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IEventInventoryEntity> getEventInventory(int idItem) {
        return EventInventoryRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IEventDoorEntity> getEventDoor(int idItem) {
        return EventIDoorRepository.getInstance().getByIdItem(idItem);
    }

    private static Optional<IEventItemEntity> getEventItem(int idItem) {
        return EventItemRepository.getInstance().getByIdItem(idItem);
    }
}
