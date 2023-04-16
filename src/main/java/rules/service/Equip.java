package rules.service;

import rules.exception.ItemEquipableException;
import rules.enums.ItemsEquipable;
import backend.model.interfaces.IEquipable;

import java.util.Arrays;

public final class Equip {

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
                .orElseThrow(() -> new ItemEquipableException("Item equipavél não encontrado"));
        return item.equip();
    }
}