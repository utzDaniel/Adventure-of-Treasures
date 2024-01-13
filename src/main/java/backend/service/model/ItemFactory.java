package backend.service.model;

import backend.repository.interfaces.*;
import backend.repository.singleton.*;
import backend.service.interfaces.ICoordinate;

import java.util.List;
import java.util.Optional;

public final class ItemFactory {

    ItemFactory() {
    }

    public static Item create(IItemEntity itemEntity) {
        return new Item(itemEntity.id(),
                itemEntity.name(),
                itemEntity.description(),
                itemEntity.weight(),
                itemEntity.image(),
                getSpecializationComposite(itemEntity.id()),
                ICoordinate.getInstance(itemEntity.positionX(), itemEntity.positionY()));
    }

    private static SpecializationComposite getSpecializationComposite(int idItem) {
        var composite = new SpecializationComposite();

        var comb = getCombinable(idItem);
        if (!comb.isEmpty()) {
            composite.add(new Combinable(comb.get(0).idItemNew(), comb.size()));
        }

        var equipable = getEquipable(idItem);
        equipable.ifPresent(e -> composite.add(new Equipable(e.upCapacity())));

        var usable = getUsable(idItem);
        usable.ifPresent(e -> composite.add(new Usable(e.idMap(), ICoordinate.getInstance(e.positionX(), e.positionY()), e.enabled())));

        var mission = getMission(idItem);
        mission.ifPresent(e -> composite.add(new Mission()));

        var eventMap = getEventMap(idItem);
        eventMap.ifPresent(e -> composite.add(new EventMap(e.idMap(), e.idDoor())));

        var eventInv = getEventInventory(idItem);
        eventInv.ifPresent(e -> composite.add(new EventInventory(e.idItemNew())));

        return composite;
    }

    private static List<ICombinableEntity> getCombinable(int idItem) {
        return CombinableRepository.getInstance().getByIdItemOriCombinable(idItem);
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
}
