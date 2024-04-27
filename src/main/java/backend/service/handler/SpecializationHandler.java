package backend.service.handler;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.model.Item;

import java.util.Optional;

public final class SpecializationHandler extends Handler<Item> {

    private final TypeItem typeItem;

    public SpecializationHandler(TypeItem typeItem) {
        this.typeItem = typeItem;
    }

    @Override
    protected Optional<TypeMessage> hook(Item item) {
        var spec = item.getSpecialization(this.typeItem);

        return switch (this.typeItem) {
            case MISSION -> spec.isPresent() ? Optional.of(TypeMessage.REMOVE_ITEM_ERROR) : Optional.empty();
            case USABLE -> spec.isEmpty() ? Optional.of(TypeMessage.ITEM_ERROR_USABLE) : Optional.empty();
            case EQUIPPABLE -> spec.isEmpty() ? Optional.of(TypeMessage.ITEM_ERROR_EQUIPPABLE) : Optional.empty();
            default -> Optional.empty();
        };

    }

}
