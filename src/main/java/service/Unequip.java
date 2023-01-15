package service;

import exception.ItemEquipableException;
import model.interfaces.IEquipable;
import model.Item;
import model.enums.ItemsEquipable;
import model.Player;

public final class Unequip<T extends Item> {

    private final T item;

    public Unequip(T item) {
        this.item = item;
    }

    public boolean run() {
        checkItemIEquipable();
        unequipItem();
        return true;
    }

    public void checkItemIEquipable() {
        if (!(this.item instanceof IEquipable))
            throw new ItemEquipableException("Item não é equipavél");
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private void unequipItem() {
        for (ItemsEquipable equipable : ItemsEquipable.values()) {
            if (equipable.getLabel().equals(this.item.getName())) {
                equipable.unequip();
                return;
            }
        }
        throw new ItemEquipableException("Item equipavél não encontrado");
    }
}
