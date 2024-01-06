package backend.service.model;

import backend.repository.interfaces.*;
import backend.repository.singleton.CombinableRepository;
import backend.repository.singleton.EquipableRepository;
import backend.repository.singleton.MissionRepository;
import backend.repository.singleton.UsableRepository;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.ISpecialization;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public final class ItemFactory {

    private ItemFactory() {
    }

    public static Item create(IItemEntity itemEntity) {
        return new Item(itemEntity.id(),
                itemEntity.name(),
                itemEntity.description(),
                itemEntity.weight(),
                itemEntity.image(),
                getSetSpecialization(itemEntity.id()),
                ICoordinate.getInstance(itemEntity.positionX(), itemEntity.positionY()));
    }

    private static Set<ISpecialization> getSetSpecialization(int idItem) {
        var set = new HashSet<ISpecialization>();

        var comb = getCombinable(idItem);
        if (!comb.isEmpty()) {
            set.add(new Combinable(comb.get(0).idItemNew(), comb.size()));
        }

        var equipable = getEquipable(idItem);
        equipable.ifPresent(e -> set.add(new Equipable(e.upCapacity())));

        var usable = getUsable(idItem);
        usable.ifPresent(e -> set.add(new Usable(e.idMap())));

        var mission = getMission(idItem);
        mission.ifPresent(e -> set.add(new Mission()));

        return set;
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
}
