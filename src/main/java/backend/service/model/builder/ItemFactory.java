package backend.service.model.builder;

import backend.repository.interfaces.IItemEntity;
import backend.service.interfaces.ICoordinate;

public final class ItemFactory {

    public Item create(IItemEntity itemEntity) {
        return ItemBuilder.builder()
                .id(itemEntity.id())
                .name(itemEntity.name())
                .description(itemEntity.description())
                .weight(itemEntity.weight())
                .coordinate(ICoordinate.getInstance(itemEntity.positionX(), itemEntity.positionY()))
                .image(itemEntity.imagemIcon())
                .type(itemEntity.type())
                .visible(itemEntity.visible())
                .build();
    }
}
