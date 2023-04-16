package rules.service;

import rules.exception.ItemEquipableException;
import backend.model.interfaces.IEquipable;
import rules.enums.ItemsEquipable;

import java.util.Arrays;

public final class Unequip {

    private final IEquipable item;

    public Unequip(IEquipable item) {
        this.item = item;
    }

    public boolean run() {
        return unequipItem();
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private boolean unequipItem() {
        var item = Arrays.stream(ItemsEquipable.values())
                .filter(equipable -> equipable.getLabel().equals(this.item.getName()))
                .findFirst().orElseThrow(() -> new ItemEquipableException("Item equipavél não encontrado"));
        return item.unequip();
    }
}