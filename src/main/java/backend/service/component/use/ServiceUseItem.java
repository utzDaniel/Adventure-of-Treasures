package backend.service.component.use;

import backend.controller.enums.TypeMessage;
import backend.service.component.RemoveItem;
import backend.service.enums.ItemsUsable;
import backend.service.interfaces.IUsable;
import backend.service.model.Inventory;
import backend.service.model.builder.Item;

import java.util.Arrays;
import java.util.Objects;

public final class ServiceUseItem {

    private final Inventory inventory;

    public ServiceUseItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(String name, String map) {

        Item item = this.inventory.getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name))
                .findFirst().get();

        var typeMessage = TypeMessage.ITEM_NOT_FOUND;
        if (item instanceof IUsable usable) {

            if (!(usable.getLocalUse().equals(map)))
                return TypeMessage.USABLE_NOT_MAP;

            var usable1 = getItemUsable(usable.getName());
            if (Objects.isNull(usable1))
                return TypeMessage.ITEM_NOT_FOUND;

            typeMessage = usable1.use();
            if (!typeMessage.isSucess()) return typeMessage;

            var typeMessage1 = new RemoveItem(this.inventory, item).run();
            if (!typeMessage1.isSucess()) return typeMessage1;
        }

        return typeMessage;
    }

    private ItemsUsable getItemUsable(String name) {
        return Arrays.stream(ItemsUsable.values())
                .filter(itemsUsable -> itemsUsable.getLabel().equals(name))
                .findFirst().orElse(null);

    }
}
