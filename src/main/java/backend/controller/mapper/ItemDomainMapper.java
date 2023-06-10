package backend.controller.mapper;

import backend.controller.domain.ItemDomain;
import backend.repository.interfaces.IItemEntity;
import backend.service.interfaces.*;

import java.util.function.Function;

public final class ItemDomainMapper implements Function<IItemEntity, IItem> {


    @Override
    public IItem apply(IItemEntity item) {
//        return new ItemDomain(
//                item.getName(),
//                item.getDescription(),
//                item.getWeight(),
//                item.getLocation(),
//                item.getIcon(),
//                isRemovable(item),
//                item.isVisible(),
//                getCombine(item),
//                getEffect(item),
//                getMapGame(item),
//                isEquipped(item),
//                getLocalUse(item),
//                item.getClass().getName());
        return null;
    }

    private boolean isRemovable(IItem item) {
        return !(item instanceof IMission);
    }

    private int getCombine(IItem item) {
        return item instanceof ICombinable combinable ? combinable.getCombine() : -1;
    }

    private String getEffect(IItem item) {
        return item instanceof IEffect effect ? effect.getEffect() : "";
    }

    private String getMapGame(IItem item) {
        return item instanceof IMission mission ? mission.getMapGame() : "";
    }

    private boolean isEquipped(IItem item) {
        return item instanceof IEquipable equipable && equipable.isEquipped();
    }

    private String getLocalUse(IItem item) {
        return item instanceof IUsable usable ? usable.getLocalUse() : "";
    }

}
