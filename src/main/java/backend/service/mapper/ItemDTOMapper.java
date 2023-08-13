package backend.service.mapper;

import backend.controller.interfaces.IItemDTO;
import backend.service.dto.ItemDTO;
import backend.service.enums.TypeItem;
import backend.service.model.Item;

import java.util.function.Function;

public class ItemDTOMapper implements Function<Item, IItemDTO> {
    @Override
    public IItemDTO apply(Item item) {
        return new ItemDTO(item.getId(),
                item.getName(),
                item.getWeight(),
                item.getDescription(),
                item.getImage(),
                combinable(item)
        );
    }

    private boolean combinable(Item item) {
        return item.isType(TypeItem.COMBINABLE);
    }
}
