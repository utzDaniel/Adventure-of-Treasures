package backend.service.component.equip;

import backend.controller.enums.TypeMessage;
import backend.service.enums.ItemsEquipable;
import backend.service.enums.TypeItem;
import backend.service.interfaces.IEquipable;
import backend.service.model.Inventory;
import backend.service.model.Item;

import java.util.Arrays;
import java.util.Objects;

public class ServiceEquipItem {

    private final Inventory inventory;

    public ServiceEquipItem(Inventory inventory) {
        this.inventory = inventory;
    }

    public TypeMessage run(Integer idItem) {

        Item item = this.inventory.getItens().stream()
                .filter(item1 -> item1.getId() == idItem)
                .findFirst().get();

        var typeMessage = TypeMessage.ITEM_NOT_FOUND;

        var spec = item.getSpecialization(TypeItem.EQUIPABLE);

        if (spec.isEmpty()) return typeMessage;

        IEquipable equipable = (IEquipable) spec.get();

        var equipable1 = getItemEquipable(item.getId());
        if (Objects.isNull(equipable1)) return typeMessage;

        if (this.inventory.isAtivo(item)) {
            typeMessage = equipable1.unequip();
            if (typeMessage.isSucess()){
                this.inventory.removeItemAtivo(item);
                equipable.setEquip(false);
            }
        } else {
            typeMessage = equipable1.equip();
            if (typeMessage.isSucess()){
                this.inventory.addItemAtivo(item);
                equipable.setEquip(true);
            }
        }

        return typeMessage;
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private ItemsEquipable getItemEquipable(int idItem) {
        return Arrays.stream(ItemsEquipable.values())
                .filter(equipable -> equipable.getId() == idItem)
                .findFirst().orElse(null);
    }

}
