package backend.service.mapper;

import backend.service.dto.ItemDTO;
import backend.service.interfaces.ICoordinate;
import backend.service.interfaces.IEffect;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemEquipable;

import java.util.function.Function;

public class ItemDTOMapper implements Function<Item, ItemDTO> {


    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(item.getName(),
                item.getDescription(),
                item.getIcon().toString(),
                item.getWeight(),
                ICoordinate.getInstance(item.getLocation().y() * 10, item.getLocation().x() * 10),
                getEffect(item),
                item.getClass().getName(),
                equipped(item)
        );
    }

    private boolean equipped(Item item) {
        return item instanceof ItemEquipable item1 && item1.isEquipped();
    }

    private String getEffect(Item item) {
        return item instanceof IEffect item1 ? item1.getEffect() : null;
    }
}
