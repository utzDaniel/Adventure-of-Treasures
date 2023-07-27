package backend.service.component.equip;

import backend.controller.enums.TypeMessage;
import backend.service.enums.ItemsEquipable;
import backend.service.interfaces.IEquipable;
import backend.service.model.Inventory;
import backend.service.model.builder.Item;

import java.util.Arrays;
import java.util.Objects;

public class ServiceEquipItem {

    private final Inventory inventory;

    public ServiceEquipItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(String name) {

        Item item = this.inventory.getItemVisible().stream()
                .filter(item1 -> item1.getName().equals(name))
                .findFirst().get();

        var typeMessage = TypeMessage.ITEM_NOT_FOUND;
        if (item instanceof IEquipable equipable) {

            var equipable1 = getItemEquipable(equipable.getName());
            if (Objects.isNull(equipable1)) return typeMessage;

            if (equipable.isEquipped()) {
                typeMessage = equipable1.unequip();
                if (typeMessage.isSucess()) equipable.setEquipped(false);
            } else {
                typeMessage = equipable1.equip();
                if (typeMessage.isSucess()) equipable.setEquipped(true);
            }
        }
        return typeMessage;
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private ItemsEquipable getItemEquipable(String name) {
        return Arrays.stream(ItemsEquipable.values())
                .filter(equipable -> equipable.getLabel().equals(name))
                .findFirst().orElse(null);
    }

}
