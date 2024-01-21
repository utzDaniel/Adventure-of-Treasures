package backend.service.component.combination;

import backend.controller.enums.TypeMessage;
import backend.service.enums.TypeItem;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.List;
import java.util.Objects;

public final class ServiceCombinationItem {
    private final Inventory inventory;

    public ServiceCombinationItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(List<Item> itens) {

        TypeMessage typeMessage = null;

        for (Item item : itens) {
            if (!item.isType(TypeItem.COMBINABLE)) {
                typeMessage = TypeMessage.COMBINE_ERRO_ALL;
                break;
            }
        }

        if (Objects.nonNull(typeMessage)) return typeMessage;

        typeMessage = new Combination(itens, this.inventory).run();

        return typeMessage;
    }

}
