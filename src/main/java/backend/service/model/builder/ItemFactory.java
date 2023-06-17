package backend.service.model.builder;

import backend.service.interfaces.ICoordinate;
import backend.repository.interfaces.IItemEntity;
import backend.service.interfaces.IBuilderItem;

import java.util.Objects;

public final class ItemFactory {

    private IItemEntity itemEntity;

    public Item create(IItemEntity itemEntity) {
        this.itemEntity = itemEntity;
        return type();
    }

    private Item type() {
        if (isICombinable())
            return createItemCombinable();
        else if (isIEquipable()) {
            return createItemEquipable();
        } else if (isIMission()) {
            return createItemNotRemove();
        } else if (isIUsable()) {
            return createItemUsable();
        }
        return null;
    }

    private IBuilderItem inicial(IBuilderItem item) {
        return item.name(itemEntity.name())
                .description(itemEntity.description())
                .weight(itemEntity.weight())
                .coordinate(ICoordinate.getInstance(itemEntity.positionX(), itemEntity.positionY()))
                .image(itemEntity.imagemIcon())
                .removable(itemEntity.remove())
                .visible(itemEntity.visible());
    }

    private Item createItemCombinable() {
        return inicial(ItemCombinableBuilder
                .builder()
                .combine(itemEntity.combine())
                .effect(itemEntity.effectsCombine()))
                .build();
    }

    private Item createItemEquipable() {
        return inicial(ItemEquipableBuilder
                .builder()
                .equipped(false)
                .effect(itemEntity.effectsEquipped()))
                .build();
    }

    private Item createItemUsable() {
        return inicial(ItemUsableBuilder
                .builder()
                .localUse(itemEntity.localUse())
                .effect(itemEntity.effectsUse()))
                .build();
    }

    private Item createItemNotRemove() {
        return inicial(ItemMissionBuilder
                .builder()
                .mapGame(itemEntity.mapGame()))
                .build();
    }

    private boolean isIUsable() {
        return Objects.nonNull(itemEntity.localUse());
    }

    private boolean isIMission() {
        return Objects.nonNull(itemEntity.mapGame());
    }

    private boolean isIEquipable() {
        return itemEntity.equipped();
    }

    private boolean isICombinable() {
        return itemEntity.combine() != -1;
    }
}
