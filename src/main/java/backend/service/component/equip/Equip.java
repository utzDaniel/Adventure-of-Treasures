package backend.service.component.equip;

import backend.service.enums.ItemsEquipable;
import backend.service.interfaces.IEquipable;

import java.util.Arrays;

public final class Equip {

    // TODO apagar depois, quando refatorar o item
    private final IEquipable item;

    public Equip(IEquipable item) {
        this.item = item;
    }

    public boolean run() {
        return equipItem();
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean equipItem() {
        var item = Arrays.stream(ItemsEquipable.values())
                .filter(equipable -> equipable.getLabel().equals(this.item.getName())).findFirst()
                .orElse(null);
        return item.equip().isSucess();
    }
}
