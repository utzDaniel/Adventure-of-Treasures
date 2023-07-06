package backend.service.mapper;

import backend.service.interfaces.IEffect;
import backend.service.model.Coordinate;
import backend.service.dto.ItemDTO;
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
                new Coordinate(item.getLocation().x(), item.getLocation().y()),
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
