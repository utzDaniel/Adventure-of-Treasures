package backend.service.mapper;

import backend.service.dto.ItemDTO;
import backend.service.interfaces.ICoordinate;
import backend.service.model.builder.Item;

import java.util.function.Function;

public class ItemDTOMapper implements Function<Item, ItemDTO> {


    @Override
    public ItemDTO apply(Item item) {
        return new ItemDTO(item.getName(),
                item.getDescription(),
                item.getIcon().toString(),
                item.getWeight(),
                ICoordinate.getInstance(item.getLocation().y() * 10, item.getLocation().x() * 10),
                item.getClass().getName(),
                equipped(item)
        );
    }

    private boolean equipped(Item item) {
        return false;
        //return item instanceof ItemEquipable item1 && item1.isEquipped();
    }
}
