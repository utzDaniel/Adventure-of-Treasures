package backend.service.model.builder;

import backend.repository.interfaces.IItemEntity;
import backend.repository.singleton.CombinableRepository;
import backend.repository.singleton.EquipableRepository;
import backend.repository.singleton.MissionRepository;
import backend.repository.singleton.UsableRepository;
import backend.service.enums.TypeItem;
import backend.service.interfaces.ICoordinate;

import java.util.ArrayList;
import java.util.List;

public final class ItemFactory {

    private ItemFactory(){}

    public static Item create(IItemEntity itemEntity) {
        return ItemBuilder.builder()
                .id(itemEntity.id())
                .name(itemEntity.name())
                .description(itemEntity.description())
                .weight(itemEntity.weight())
                .coordinate(ICoordinate.getInstance(itemEntity.positionX(), itemEntity.positionY()))
                .image(itemEntity.image())
                .type(getListTypeItem(itemEntity))
                .visible(itemEntity.visible())
                .build();
    }

    private static List<TypeItem> getListTypeItem(IItemEntity itemEntity) {
        var list = new ArrayList<TypeItem>();
        if (isCombinable(itemEntity))
            list.add(TypeItem.COMBINABLE);
        if (isEquipable(itemEntity))
            list.add(TypeItem.EQUIPABLE);
        if (isUsable(itemEntity))
            list.add(TypeItem.USABLE);
        if (isMission(itemEntity))
            list.add(TypeItem.MISSION);
        return list;
    }

    private static boolean isCombinable(IItemEntity itemEntity) {
        return !CombinableRepository.getInstance()
                .getByIdItemOri(itemEntity.id())
                .isEmpty();
    }

    private static boolean isEquipable(IItemEntity itemEntity) {
        return EquipableRepository.getInstance()
                .getByIdItem(itemEntity.id())
                .isPresent();
    }

    private static boolean isUsable(IItemEntity itemEntity) {
        return UsableRepository.getInstance()
                .getByIdItem(itemEntity.id())
                .isPresent();
    }

    private static boolean isMission(IItemEntity itemEntity) {
        return MissionRepository.getInstance()
                .getByIdItem(itemEntity.id())
                .isPresent();
    }

}
