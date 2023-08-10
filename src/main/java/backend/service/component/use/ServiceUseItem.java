package backend.service.component.use;

import backend.controller.enums.TypeMessage;
import backend.repository.singleton.ItemRepository;
import backend.repository.singleton.UsableRepository;
import backend.service.component.RemoveItem;
import backend.service.enums.ItemsUsable;
import backend.service.enums.TypeItem;
import backend.service.model.Inventory;
import backend.service.model.Item;
import backend.service.model.ItemFactory;

import java.util.Arrays;
import java.util.Objects;

public final class ServiceUseItem {

    private final Inventory inventory;

    public ServiceUseItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(String name, int idMap) {

        Item item = this.inventory.getItens().stream()
                .filter(item1 -> item1.getName().equals(name))
                .findFirst().get();

        var typeMessage = TypeMessage.ITEM_NOT_FOUND;
        if (item.isType(TypeItem.USABLE)) {

            var listUsable = UsableRepository.getInstance().getAll();
            var usable = listUsable.stream().filter(v -> v.idItem() == item.getId()).findFirst().orElse(null);

            if (usable.idMap() != idMap)
                return TypeMessage.USABLE_NOT_MAP;

            var usable1 = getItemUsable(item.getName());
            if (Objects.isNull(usable1))
                return TypeMessage.ITEM_NOT_FOUND;

            typeMessage = usable1.use();
            if (!typeMessage.isSucess()) return typeMessage;


            var newItem = ItemFactory.create(ItemRepository.getInstance().getById(1));//chave
            if (Objects.isNull(newItem))
                return TypeMessage.ITEM_NOT_FOUND;

            this.inventory.addItem(newItem);

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
