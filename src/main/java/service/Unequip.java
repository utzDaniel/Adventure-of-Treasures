package service;

import exception.ItemEquipableException;
import model.interfaces.IEquipable;
import model.builder.item.Item;
import model.enums.ItemsEquipable;

import java.util.Arrays;

public final class Unequip<T extends Item> { //TODO T extends IEquipable

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
        var item = Arrays.stream(ItemsEquipable.values())
                .filter(equipable -> equipable.getLabel().equals(this.item.getName()))
                .findFirst().orElseThrow(() -> new ItemEquipableException("Item equipavél não encontrado"));
        item.unequip();
    }
}
