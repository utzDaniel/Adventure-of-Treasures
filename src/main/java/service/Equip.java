package service;

import exception.ItemEquipableException;
import model.interfaces.IEquipable;
import model.builder.item.Item;
import model.enums.ItemsEquipable;
import model.Player;

public final class Equip <T extends Item> {

    private final Player player;
    private final T item;

    public Equip(T item) {
        this.player = Player.getInstance();
        this.item = item;
    }
    public boolean run() {
        checkItemIEquipable();
        equipItem();
        return true;
    }

    private void checkItemIEquipable() {
        if(!(this.item instanceof IEquipable))
        throw new ItemEquipableException("Item não é equipavél");
    }

    //item equipavel com room e outro sem, será que deve criar uma nova classe?
    private void equipItem() {
        for (ItemsEquipable equipable : ItemsEquipable.values()) {
            if (equipable.getLabel().equals(this.item.getName())) {
                equipable.equip();
                return;
            }
        }
        throw new ItemEquipableException("Item equipavél não encontrado");
    }
}
