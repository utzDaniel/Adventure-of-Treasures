package service;

import exception.ItemEquipableException;
import model.IEquipable;
import model.Item;
import model.ItemsEquipable;
import model.Player;

public final class Unequip<T extends Item> {

    private final Player player;
    private final T item;

    public Unequip(Player player, T item) {
        this.player = player;
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
                equipable.equip(this.player);
                return;
            }
        }
        throw new ItemEquipableException("Item equipavél não encontrado");
    }
}
